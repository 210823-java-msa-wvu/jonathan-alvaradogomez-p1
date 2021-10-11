package com.alvarado.servlets;

import com.alvarado.controllers.FrontController;
import com.alvarado.controllers.LoginController;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends DefaultServlet {

    private Logger log = LogManager.getLogger(FrontControllerServlet.class);
    private RequestHandler rh = new RequestHandler();
    private LoginController loginController = new LoginController();

    // This method is where we will funnel all of our requests
    // This way, we have one central location and can pass them off to some handler/controller

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        // First, check if trying to access static resources
        String uriNoContext = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("\nFrontController Servlet| Printing uriNoContext: " + uriNoContext);
        log.trace(uriNoContext);

        if(uriNoContext.startsWith("/static")) {
            log.trace("Accessing static resources and trying to forward ...");
            System.out.println("FrontController Servlet| Accessing static resources and trying to forward ...");
            super.doGet(request, response); //what does this do?
            System.out.println("FrontController Servlet| super.doGet (I think this sent, not sure what it does)");

            System.out.println("About to use the Request Handler...");
            rh.handle(request, response);
//            System.out.println("FrontController Servlet trying to access LoginController ...  ");
//            loginController.process(request, response);


        } else {
            // We want to 'get' the correct servlet based on the uri
            log.trace("Not static... Getting the appropriate controller...");
            System.out.println("FrontController Servlet| Not static... Getting the appropriate controller...");
            FrontController fc = rh.handle(request, response);

            //First check to make sure it is not null
            if (fc != null) {
                log.trace("Processing Request...");
                System.out.println("FrontController Servlet| Processing Request...");
                fc.process(request, response);
            } else {
                log.trace("Request Unable to Process...");
                System.out.println("FrontController Servlet| Request Unable to Process...");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }

}
