package com.alvarado.services;

import com.alvarado.models.Staff;
import com.alvarado.repositories.StaffRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StaffServices {

    ObjectMapper om = new ObjectMapper();
    StaffRepo staffRepo = new StaffRepo();

    public boolean login(String username, String password) {
        // we need a username and password to login a staff member.
        // that info is stored on our database
        // the repository layer will take care of that communication

        Staff s = staffRepo.getByUsername(username); //Sole responsibility principle at work

        //check to be sure the Staff object is not null
        if( s != null){
            // now check to make sure it matches
            if (username.equals(s.getUsername()) && password.equals(s.getPassword())){
                return true;
            }

        }
        return false;
    }

    public String staffType(String username) {
        //This will help us identity the type of staff member by their username

        Staff s = staffRepo.getByUsername(username);

        if (s.getEmployee()) {
            return "employee";
        } else if (s.getSuper() && s.getHead()){
            return "superHead";
        } else if (s.getSuper()){
            return "super";
        } else if (s.getHead()) {
            return "head";
        } else if (s.getBenco()){
            return "benco";
        }
        return null; // I hope this does not come back to haunt me later...
    }

    public String staffInfoAsJSON (String username){ // use this to send as JSON first/last name

        try{
            Staff s = staffRepo.getByUsername(username); // get all staff info first
//            Staff staff = new Staff(); // only send staff first and last name
//            staff.setFirstName(s.getFirstName());
//            staff.setLastName(s.getLastName());
            return om.writeValueAsString(s);

        }catch (JsonProcessingException e) {
            System.out.println("***An ERROR occurred in the StaffServices Class regarding JSON***");
            e.printStackTrace();
        }

        return null;
    }


}
