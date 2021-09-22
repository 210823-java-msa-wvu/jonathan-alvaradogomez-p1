# jonathan-alvaradogomez-p1
# Tuition Reimbursement Managament System (Tiramisu/ TRMS)

## Overview
Tiramisu (TRMS) is tuition reimbursement management system designed to streamline the reimbursement process for employees who take a variety of courses or certifications (referred to as events). It will come as a full-stack project with a front-end web application and back-end logic in Java, JavaScript, and databases in AWS (through DBeaver).

## User Stories
There are 4 different kinds of users for this website:
  - Employees
  - Direct Supervisors 
  - Department Heads
  - Benefits Coordinators 

Each user has the ability to sign into the website, and once logged in, the website will keep track of what kind of user they are. Employees will have the ability to create a new account if they do not already have one. However, Direct Supervisors, Department Heads, and Benefits Coordinators will already have existing accounts and cannot have new ones created. 

Once an **Employee** signs in, they have the ability to:
  - Fill out a tuition reimbursement form (at least one week before event)
  - Submit their final grade or presentation after course completion (passing grade needed for reimbursement)
  - Check a notifications tab
    - Will notify if request has been approved, denied, or is pending
    - Will notify if additional information is requested from a Direct Supervisor, Department Head, or Benefits Coordinator.

Once a **Direct Supervisor** signs in, they have the ability to:
  - Approve/ deny employee's reimbursement request
  - Can request 
