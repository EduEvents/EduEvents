
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/EduEvents/conf/routes
// @DATE:Mon Mar 26 15:22:56 BST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseFrontendController FrontendController = new controllers.ReverseFrontendController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserController UserController = new controllers.ReverseUserController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseFrontendController FrontendController = new controllers.javascript.ReverseFrontendController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserController UserController = new controllers.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
  }

}