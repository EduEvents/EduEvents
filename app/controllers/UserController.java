package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import inventories.*;
import java.util.*;
import play.libs.Json;
import inventories.UserInventory;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.*;
import inventories.UserInventory.*;

public class UserController extends Controller {

	private User model;
	private UserInventory userInventory = UserInventory.getInstance();
	private EventInventory eventInventory = EventInventory.getInstance();
	private TagInventory tagInventory = TagInventory.getInstance();

	//Create example profiles in the constructor
	public UserController() {
		signUp("user", "user", "user@user.user");
		signUp("hot", "memes", "@lul");
		signUp("organiser", "organiser", "organiser@organiser.organiser");
		eventInventory.createEvent(new Event("reddit course", "Somewhere at", "Best reddit course ever", 399.00, 420, getTagFromString("Maths")));
		eventInventory.createEvent(new Event("hella course", "Somewhere at", "Best hella course ever", 399.00, 420, getTagFromString("Language Maths")));
		eventInventory.createEvent(new Event("random course", "Somewhere at", "Best random course ever", 399.00, 420, getTagFromString("Language History")));
		eventInventory.createEvent(new Event("naming course", "Somewhere at", "Best naming course ever", 399.00, 420, getTagFromString("Language Maths")));
		eventInventory.createEvent(new Event("someting course", "Somewhere at", "Best someting course ever", 399.00, 420, getTagFromString("History Maths")));
		eventInventory.createEvent(new Event("goes skrraa course", "Somewhere at", "Best skrraa course ever", 399.00, 420, getTagFromString("History Maths")));
	}

	public HashSet<Tag> getTagFromString(String tags) {
		String[] ar = tags.split(" ");
		System.out.println(ar);
		HashSet<Tag> tagSet = tagInventory.getTags();
		HashSet<User> userInv = userInventory.getUsers();
		HashSet<Tag> tagRes = new HashSet<Tag>();
		for (String s : ar)
			for (Tag t : tagSet)
				if (t.getTagName().equals(s))
					tagRes.add(t);
		return tagRes;
	}

	public Result login(String username, String password) {
		boolean loginSuccessful = false;
		HashSet<User> users = userInventory.getUsers();
		ArrayList meme = new ArrayList();
		User login = new User();

		for (User x : users) {
			System.out.println(x.getUsername() + " " + x.getPassword());
			if (x.getUsername().equals(username) && x.getPassword().equals(password)) {
				loginSuccessful = true;
				model = x;
				break;
			}
		}
		System.out.println(model.getUsername());
		System.out.println(loginSuccessful);
		JsonNode jsonNode = Json.toJson(loginSuccessful);
		return ok(jsonNode).as("application/json");
	}

	private User getUser(String s) {
		HashSet<User> userInv = userInventory.getUsers();
		for (User x : userInv)
			if (x.getUsername().equals(s))
				return x;
		return new User("user", "user", "user@user.user");
	}

	public Result signUp(String username, String password, String email) {
		boolean success = true;
		HashSet<User> users = userInventory.getUsers();
		for (User x : users)
			if (x.getUsername().equals(username) || x.getEmail().equals(email)) {
				success = false;
			}
		userInventory.addUser(new User(username, password, email));
		System.out.println(userInventory.getUsers());
		JsonNode jsonNode = Json.toJson(success);
		return ok(jsonNode).as("application/json");
	}

	public Result search(String term, String tags) {

		String[] ar = tags.split(" ");


		HashSet<Tag> tagSet = tagInventory.getTags();
		HashSet<User> userInv = userInventory.getUsers();
		HashSet<Event> events = eventInventory.getEvents();
		System.out.println(events);
		HashSet<Tag> tagRes = new HashSet<Tag>();

		for (String s : ar)
			for (Tag t : tagSet)
				if (t.getTagName().equals(s))
					tagRes.add(t);


		for (Event g : events)
			System.out.println(g.getName());
		HashSet<Event> filtered = new HashSet<Event>();
		for (Event e : events) {
			if (e.getName().contains(term) || e.getDescription().contains(term) || e.getAddress().contains(term))
				filtered.add(e);
			for (Tag t : e.getTags())
				for (Tag l : tagRes)
					if (t.equals(l))
						filtered.add(e);
		}
		JsonNode jsonNode = Json.toJson(filtered);
		return ok(jsonNode).as("application/json");
	}

	public Result createEvent(String name, String address, String description, double price, int maxTickets, String tags) {
		System.out.println(model.getUsername());
		boolean result = true;
		HashSet<Event> eventInv = eventInventory.getEvents();
		for (Event x : eventInv)
			if (x.getName().equals(name))
				result = false;
		if (result) {
			Event newEvent = new Event(name, address, description, price, maxTickets, getTagFromString(tags));
			eventInventory.createEvent(newEvent);
			model.addCreated(newEvent);
		}
		JsonNode jsonNode = Json.toJson(result);
		return ok(jsonNode).as("application/json");
	}

	public Result getModel() {
		JsonNode jsonNode = Json.toJson(model);
		return ok(jsonNode).as("application/json");
	}

	public Result setModel(String username) {
		HashSet<User> users = userInventory.getUsers();
		for (User x : users)
			if (x.getUsername().equals(username))
				this.model = x;
		return ok().as("application/json");
	}

	public Result bookEvent(int eventID, int noOfTickets, double price) {
		Event result = eventInventory.getEventById(eventID);
		Date date = new Date();
		if (result.getNoOfSold() + noOfTickets > result.getMaxTickets())
			result = null;
		else
			result.bookTicket(noOfTickets);
		model.addTicket(new Ticket(date.toString(), price));
		JsonNode jsonNode = Json.toJson(result);
		return ok(jsonNode).as("application/json");
	}
}
