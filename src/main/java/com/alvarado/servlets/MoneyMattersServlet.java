package com.alvarado.servlets;

import com.alvarado.models.Approvals;
import com.alvarado.models.Balance;
import com.alvarado.models.Requests;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.BalanceRepo;
import com.alvarado.repositories.RequestsRepo;
import com.alvarado.repositories.StaffRepo;
import com.alvarado.services.BalanceServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class MoneyMattersServlet extends HttpServlet {

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
        System.out.println("\nMoneyMattersServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("MoneyMattersServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        //Get the staff member's username again
        String username = session.getAttribute("username").toString();
        Staff s = staffRepo.getByUsername(username);

        //This servlet will either access the balance table or the approval table, depending on the request header
        String headerInfo = request.getHeader("type");
        System.out.println("Printing headerInfo:    " + headerInfo);

        if ( headerInfo.equals("balance") ){ //if the request header is "balance", then access the balance table
            System.out.println("Getting balance data ... ");

            List<Balance> b = balanceRepo.getAll();

            System.out.println("Printing balance:    " + b);

            response.getWriter().write(om.writeValueAsString(b));



            // need to get all requests and display them.
        } else if (headerInfo.equals("requests")) { // the request header is "approvals", so access the approval table
            System.out.println("Getting requests data ... ");

            List<Requests> r = requestsRepo.getAll();

            for (int i = 0; i < r.size(); i++){
                System.out.println("Approval list index at i = " + i + ":    " + r.get(i));
            }

            response.getWriter().write(om.writeValueAsString(r));



        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Log when this servlet has been accessed
        System.out.println("\nMoneyMattersServlet received a " + request.getMethod() + " request at " + request.getRequestURI());
        log.trace("MoneyMattersServlet Accessed");

        // Continue HttpSession here
        HttpSession session = request.getSession();
        System.out.println("Session ID:    " + session.getId());
        log.trace("Session Continued with same credentials");

        Integer staffId = Integer.valueOf(request.getParameter("employee_id"));
        Integer requestId = Integer.valueOf(request.getParameter("request_id"));

        Requests r = requestsRepo.getByIDS(staffId, requestId);

        r.setBencoChangedCost(true);

        BigDecimal changeCost = new BigDecimal(String.valueOf(request.getParameter("changeCost")));
        System.out.println("changeCost:   " + changeCost);

        r.setCost(changeCost);

        System.out.println("r:   " + r);

        requestsRepo.update(r);

        Staff staff = staffRepo.getById(staffId);
        balanceServices.updateAvailPend(staff);

        response.sendRedirect("http://localhost:8080/Project1/static/moneyMatters.html");



    }






}
