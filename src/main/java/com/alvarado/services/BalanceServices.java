package com.alvarado.services;

import com.alvarado.models.Approvals;
import com.alvarado.models.Balance;
import com.alvarado.models.Requests;
import com.alvarado.models.Staff;
import com.alvarado.repositories.ApprovalsRepo;
import com.alvarado.repositories.BalanceRepo;
import com.alvarado.repositories.RequestsRepo;
import com.alvarado.repositories.StaffRepo;
import com.fasterxml.jackson.databind.BeanProperty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Objects;

public class BalanceServices {

    private ApprovalsRepo approvalsRepo = new ApprovalsRepo();
    private RequestsRepo requestsRepo = new RequestsRepo();
    private BalanceRepo balanceRepo = new BalanceRepo();

    public void updateAvailPend(Staff staff){

        Balance balance = balanceRepo.getById(staff.getStaffId());
        List<Requests> requests = requestsRepo.getAllById(staff.getStaffId());
        System.out.println("\nPrinting this balance: " + balance);

        // Set my variables
        BigDecimal available = new BigDecimal(1000);
        BigDecimal pending = new BigDecimal(0);

        System.out.println("initial available:  " + available);
        System.out.println("initial pending:    " + pending);

        // iterate through all of the requests and sum those values, making them into the pending values
        for (int i = 0; i < requests.size();i++){
            System.out.println("\nindex i: " + i + ", getting cost =       " + requests.get(i).getCost() );
            System.out.println("index i: " + i + ", getting event type = " + requests.get(i).getEventType() );

            BigDecimal cost = new BigDecimal(String.valueOf(requests.get(i).getCost()));
            String eventType = requests.get(i).getEventType();

            switch (eventType) {
                case "certification":{
                    BigDecimal scale = new BigDecimal(1);
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);
                    pending = pending.add(cost);
                    break;
                }
                case "technical":{
                    BigDecimal scale = new BigDecimal("0.9");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);
                    pending = pending.add(cost);
                    break;
                }
                case "uni_course":{
                    BigDecimal scale = new BigDecimal("0.8");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);
                    pending = pending.add(cost);
                    break;
                }
                case "certification_prep":{
                    BigDecimal scale = new BigDecimal("0.75");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);
                    pending = pending.add(cost);
                    break;
                }
                case "seminar":{
                    BigDecimal scale = new BigDecimal("0.6");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);
                    pending = pending.add(cost);
                    break;
                }
                case "other":{
                    BigDecimal scale = new BigDecimal("0.3");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);
                    pending = pending.add(cost);
                    break;
                }

            }


        }

        available = available.subtract(pending);

        System.out.println("\nfinal available:  " + available);
        System.out.println("final pending:    " + pending);

        balance.setAvailable(available);
        balance.setPending(pending);

        balanceRepo.update(balance);
        System.out.println("Printing updated balance:  " + balance);

    }

    public void updatePendingAwarded(Staff staff, Integer requestId){
//        List<Approvals> approvals = approvalsRepo.getAllById(staff.getStaffId());

        // get this employees balance
        Balance balance = balanceRepo.getById(staff.getStaffId());

        // Set my variables
        BigDecimal pending = balance.getPending();
        BigDecimal awarded = balance.getAwarded();

        System.out.println("initial pending:    " + pending);
        System.out.println("initial awarded:    " + awarded);



        //get the specific request
        Approvals approval = approvalsRepo.getByIDS(staff.getStaffId(), requestId);

        // from that approval object pull whether they were approved or denied
        String finalGrade = approval.getFinalGrade();
        Boolean supervisor = approval.getSuperDecision();
        Boolean head = approval.getHeadDecision();
        Boolean benco = approval.getBencoDecision();

        if(supervisor && head && benco && !Objects.equals(finalGrade, "")){
            System.out.println("All approval conditions have been met");

            Requests requests = requestsRepo.getByIDS(staff.getStaffId(), requestId);

            String eventType = requests.getEventType();
            System.out.println("\nEvent type: " + eventType);

            BigDecimal cost = requests.getCost();
            System.out.println("Cost : " + cost);

            switch (eventType){
                case "certification":{
                    BigDecimal scale = new BigDecimal("1");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);

                    pending = pending.subtract(cost);
                    awarded = awarded.add(cost);


                    break;
                }
                case "technical":{
                    BigDecimal scale = new BigDecimal("0.9");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);

                    pending = pending.subtract(cost);
                    awarded = awarded.add(cost);


                    break;
                }
                case "uni_course":{
                    BigDecimal scale = new BigDecimal("0.8");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);

                    pending = pending.subtract(cost);
                    awarded = awarded.add(cost);


                    break;
                }
                case "certification_prep":{
                    BigDecimal scale = new BigDecimal("0.75");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);

                    pending = pending.subtract(cost);
                    awarded = awarded.add(cost);


                    break;
                }
                case "seminar":{
                    BigDecimal scale = new BigDecimal("0.6");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);

                    pending = pending.subtract(cost);
                    awarded = awarded.add(cost);


                    break;
                }
                case "other":{
                    BigDecimal scale = new BigDecimal("0.3");
                    MathContext m = new MathContext(3);
                    cost = cost.multiply(scale);
                    cost = cost.round(m);

                    System.out.println("cost scaled down:  " + cost);

                    pending = pending.subtract(cost);
                    awarded = awarded.add(cost);


                    break;
                }


            }

            System.out.println("\nfinal pending:  " + pending);
            System.out.println("final awarded:    " + awarded);

            balance.setPending(pending);
            balance.setAwarded(awarded);

            balanceRepo.update(balance);
            System.out.println("Printing updated balance:  " + balance);


        }



    }



}
