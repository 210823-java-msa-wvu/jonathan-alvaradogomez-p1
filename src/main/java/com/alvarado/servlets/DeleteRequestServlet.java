package com.alvarado.servlets;

import com.alvarado.models.Requests;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.BalanceRepo;
import com.alvarado.repositories.RequestsRepo;
import com.alvarado.repositories.StaffRepo;
import com.alvarado.services.BalanceServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteRequestServlet extends HttpServlet {

    private BalanceServices balanceServices = new BalanceServices();
    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private RequestsRepo requestsRepo = new RequestsRepo();
    private BalanceRepo balanceRepo = new BalanceRepo();
    private StaffRepo staffRepo = new StaffRepo();
    private Logger log = LogManager.getLogger(EmployeeServlet.class);
    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nDeleteServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("DeleteServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        List<Requests> r = requestsRepo.getAllById(s.getStaffId());

        for (int i = 0; i < r.size(); i++){
            System.out.println("Approval list index at i = " + i + ":    " + r.get(i));
        }

        response.getWriter().write(om.writeValueAsString(r));





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nDeleteServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("DeleteServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        Requests r = requestsRepo.getByIDS(s.getStaffId(), Integer.valueOf(request.getParameter("request_id")));

        System.out.println("r :   " + r);

        requestsRepo.delete(r);

        response.sendRedirect("http://localhost:8080/Project1/static/deleteRequest.html");


    }



}
