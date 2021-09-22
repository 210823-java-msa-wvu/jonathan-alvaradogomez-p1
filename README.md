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

### Once an **Employee** signs in, they have the ability to:
  - Fill out a tuition reimbursement form (at least one week before event), with overall limit set to $1000 
  - Submit their final grade or presentation after course completion (passing grade needed for reimbursement)
  - Check a notifications tab
    - Will notify if request has been approved, denied, or is pending
    - Will notify if additional information is requested from a Direct Supervisor, Department Head, or Benefits Coordinator.


### Once a **Direct Supervisor** signs in, they have the ability to:
  - Approve/ deny employee's reimbursement request
    - If denied, Direct Supervisor must provide a reason. 
  - Request additional information from employee before approval

If **Department Supervisor** is also a **Department Head**, then Department Head approval is skipped.

If this task is not complete within 5 days of receival, then request is auto-approved. 


### Once a **Department Head** signs in, they have the ability to:
  - Approve/ deny employee's reimbursement request
    - If denied, Department Head must provide a reason. 
  - Request additional information from employee before approval

If this task is not complete within 5 days of receival, then request is auto-approved. 


### Once a **Benefits Coordinator (BenCo)** signs in, they have the ability to:
  - Approve/ deny employee's reimbursement request
    - If denied, BenCo must provide a reason. 
  - Request additional information from 
    - Employee before approval
    - Direct Supervisor before approval
    - Department Head before approval
  - Alter the reimbursement ammount
    - If larger than $1000, **BenCo** must provide a reason for this 

This approval is not skippable.  

Employee will receive a notification if **BenCo** changes award amount. 
  - In this case, employee has option to cancel their request

If not approved wihtin 5 days of receival, escalation email should be sent to **BenCo's _Direct Supervsior_**. 
