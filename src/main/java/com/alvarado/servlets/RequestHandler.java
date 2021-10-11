package com.alvarado.servlets;

import com.alvarado.controllers.*;
import com.sun.javaws.IconUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* This Request Handler will do 2 things:
*   1. Return to the FrontControllerServlet the appropriate controller to accomplish what the request is asking for
*   2. Save some information to the Session that we will be using later
 */
public class RequestHandler {
    private Logger log = LogManager.getLogger(RequestHandler.class);

    // A map to hold the different controllers that we will be creating
    private Map<String, FrontController> controllerMap;

    {
        controllerMap = new HashMap<String, FrontController>();

        controllerMap.put("login", new LoginController());

    }

    //A method to return the appropriate controller
    public FrontController handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Here is where the logic will go to parse the URI and send back the appropriate controller

        StringBuilder uriString = new StringBuilder(request.getRequestURI()); //uri = /Project1/login
        System.out.println("\nRequestHandler | uriString: " + uriString);

        if(uriString.indexOf("html") != -1){
            request.setAttribute("static", uriString.toString());

            System.out.println("request.getAttribute(\"static\", uriString.toString()):     " + request.getAttribute("static"));
            System.out.println("Case 1: uriString =  " + uriString);
            return controllerMap.get(uriString.toString());
        }

        if (uriString.indexOf("/") != -1){
            String uriNoContext = request.getRequestURI().substring(request.getContextPath().length() + 8);
            System.out.println("RequestHandler | Printing uriNoContext:    " + uriNoContext);

            request.setAttribute("path", uriNoContext); // this sets attribute 'path' to 'login'
            System.out.println("request.getAttribute('path'):    " + request.getAttribute("path"));
//
//            uriString.replace(uriString.indexOf("/"), uriString.length() + 1, ""); //at this point, uriString = 'login
//            System.out.println("Case 2: uri =  " + uriString);
            System.out.println("controllerMap.get(uriNoContext):    "+ controllerMap.get(uriNoContext));

//            for (int i = 0;i<controllerMap.size();i++){
//                System.out.println(controllerMap.keySet(i));
//            }
            return controllerMap.get(uriNoContext);
        }
        System.out.println("Current uriString:  " + uriString);
        return controllerMap.get(uriString.toString());
    }

}
