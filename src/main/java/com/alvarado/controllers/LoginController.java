package com.alvarado.controllers;

import com.alvarado.models.Staff;
import com.alvarado.repositories.StaffRepo;
import com.alvarado.services.StaffServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;

public class LoginController implements FrontController{
    private Logger log = LogManager.getLogger(LoginController.class);
    private StaffServices staffServices = new StaffServices();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("\nLoginController Accessed | ");
        String username = request.getParameter("username"); // where does this come from? The web server
        String password = request.getParameter("password"); // where does this come from? The web server
        System.out.println("Username: " + username + " | " + "Password: " + password);

        //Now check if credentials match in order to send staff to correct homepage

        /*
        * Will I need a switch case here for the 4 different staff types?
        * Or is just one homepage fine?
         */

        if (staffServices.login(username, password)) {
            System.out.println("\nLoginController Class | Staff signed in successfully ");

            String staffLevel = staffServices.staffType(username);
            System.out.println("LoginController | Printing Staff Level: " + staffLevel);

            switch (staffLevel){
                case "employee" : {
                    System.out.println("LoginController | employee switch case activated");
//                    PrintWriter pw =  response.getWriter();
//                    response.sendRedirect("static/employee.html");
//                    pw.close();
                    response.sendRedirect("http://localhost:8080/Project1/static/employee.html");
                    break;
                }
//                case "super" : {
//                    response.sendRedirect("static/super.html");
//                    break;
//                }
//                case "head" : {
//                    response.sendRedirect("static/head.html");
//                    break;
//                }
//                case "superHead" : {
//                    response.sendRedirect("static/superHead.html");
//                    break;
//                }
//                case "benco" : {
//                    response.sendRedirect("static/benco.html");
//                    break;
//                }
            }

        } else{
            System.out.println("LoginController Class | Login Failed");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Login Credentials");
        }
    }
}
