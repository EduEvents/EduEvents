package inventories;

import models.Report;
import models.Event;
import java.util.Collection;
import java.util.HashSet;

public class ReportInventory {
	private static ReportInventory inventory = new ReportInventory();
	private Collection<Report> report = new HashSet<>();

	private ReportInventory(){}

	public static ReportInventory getInstance()
	{
		return inventory;
	}

	public void createReport(int reportID, String message, Event event)
	{
		report.add(new Report(reportID, message, event));
	}

	public void closeReport(int reportID)
	{

	}

	/**
	* Returns value of report
	* @return
	*/
	public Collection<Report> getReport() {
		return report;
	}

	/**
	* Sets new value of report
	* @param
	*/
	public void setReport(Collection<Report> report) {
		this.report = report;
	}
}
