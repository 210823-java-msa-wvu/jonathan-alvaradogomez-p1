//Create an event listener that will call some function when a button is clicked
document.getElementById("checkBalance").addEventListener("click", getBalance);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/balanceStatus?` ;

function getBalance(){
    console.log("'Balance' button was clicked");
    console.log("baseURL: ", baseURL);

    //STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();
    

    //STEP 2: Set a call back function for the readystatechange event
    xhttp.onreadystatechange = receiveBalance;

    //STEP 3:  Open the request
    xhttp.open("GET", baseURL, true); // true is the default argument, but it is best to be explicit, this is asynchronous
    xhttp.setRequestHeader('type','balance'); // set request header to help me know which server to access while I am in Java

    //STEP 4: Send the request
    xhttp.send(); //for GET requests, the send function does not have any arguments. 

    function receiveBalance(){
        // before we parse the response and populate the data, 
        // let's empty out what's inside the data div element

        let balanceSection = document.getElementById("balanceSection");
        balanceSection.innerHTML = "";


        // Check if the ready state is 'DONE' (aka '4') and if the HTTP Status is 'ok' (200)
        // console.log("logging xhttp.status:  ", xhttp.status);
        if (xhttp.readyState == 4 && xhttp.status==200){
            let r = xhttp.responseText;

            r = JSON.parse(r);
            console.log("r in JSON format below:");
            console.log(r);

            populateBalance(r);

        }
    }
}

function populateBalance(res){
    let balanceSection = document.getElementById("balanceSection");

    // make an array to hold the table headings
    let tableHeadings = ["Available", "Pending", "Awarded"];
    let amounts = [res.available, res.pending, res.awarded];

    // console.log("Logging amounts: ",amounts);

    // Create a new table  element, populate it, then add it as a child to DOM
    let balanceTable = document.createElement("table");

    // //create a row for the headings
    let tr = document.createElement("tr");
    balanceTable.appendChild(tr);

    // //create a row for the ammounts
    let ammountRow = document.createElement("tr");
    balanceTable.appendChild(ammountRow);

    for (let i = 0; i < tableHeadings.length; i++){
        let td = document.createElement("td");
        td.innerHTML = tableHeadings[i];
        tr.appendChild(td);

        let amountData = document.createElement("td");
        amountData.innerHTML = `$ ${amounts[i]}`;
        ammountRow.appendChild(amountData);

    }

    //append the table to the balance section
    balanceSection.appendChild(balanceTable);


}

//=========================Approval Button Was Clicked===============================================

//Create an event listener that will call some function when a button is clicked
document.getElementById("checkApprovals").addEventListener("click", getApprovals);

function getApprovals(){
    console.log("'Approval' button was clicked");
    console.log("baseURL: ", baseURL);

    //STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();
    
    //STEP 2: Set a call back function for the readystatechange event
    xhttp.onreadystatechange = receiveApprovals;

    //STEP 3:  Open the request
    xhttp.open("GET", baseURL, true); // true is the default argument, but it is best to be explicit, this is asynchronous
    xhttp.setRequestHeader('type','approvals'); // set request header to help me know which server to access while I am in Java

    //STEP 4: Send the request
    xhttp.send(); //for GET requests, the send function does not have any arguments. 

    function receiveApprovals(){
        // before we parse the response and populate the data, 
        // let's empty out what's inside the data div element

        let balanceSection = document.getElementById("approvalSection");
        balanceSection.innerHTML = "";


        // Check if the ready state is 'DONE' (aka '4') and if the HTTP Status is 'ok' (200)
        // console.log("logging xhttp.status:  ", xhttp.status);
        if (xhttp.readyState == 4 && xhttp.status==200){
            let r = xhttp.responseText;

            r = JSON.parse(r);
            console.log("r in JSON format below:");
            console.log(r);

            for(let i=0; i < r.length; i++){
                // console.log("printing i: ", i);
                // console.log("printing r[i]: ", r[i]);
                console.log("function populateApprovals(r, i) was called");

                populateApprovals(r,i);
            }

        }
    }
}

function populateApprovals(res,index){
    // console.log("populateApproval called with index: ", index); 

    let approvalSection = document.getElementById("approvalSection");

    approvalSection.appendChild(document.createElement("hr"));


    // first, make each descriptor 
    let requestId = res[index].approvalId;
    let finalGrade = res[index].finalGrade;
    let superDecision = res[index].superDecision;
    let superReasoning = res[index].superReasoning;
    let headDecision = res[index].headDecision;
    let headReasoning = res[index].headReasoning;
    let bencoDecision = res[index].bencoDecision;
    let bencoReasoning = res[index].bencoReasoning;

    makeHeading(approvalSection, requestId);


    let contentsArray = [requestId, finalGrade, superDecision, superReasoning, headDecision, headReasoning, bencoDecision, bencoReasoning];


    // make an array to hold the table headings which will be on the left 
    let statementArray = ["Approval ID", "Final Grade", 
        "Direct Supervisor Decision", "Direct Supervisor Reasoning",
        "Department Head Decision", "Department Head Reasoning", 
        "Benefits Coordinator Decision", "Benefits Coordinator Reasoning"];
    // let amounts = [res.available, res.pending, res.awarded];

    // console.log("Logging amounts: ",amounts);

    // Create a new table  element, populate it, then add it as a child to DOM
    let approvalsTable = document.createElement("table");

    for (let i = 0; i<contentsArray.length; i++){

        let tableRow = document.createElement("tr");
        let tableData1 = document.createElement("td");
        let tableData2 = document.createElement("td");

        tableData1.innerHTML = statementArray[i];
        if(typeof(contentsArray[i]) == "boolean" ){
            tableData2.innerHTML = statementConverter(contentsArray[i]);

        } else if(statementArray[i] == "Final Grade" && contentsArray[i]==""){
            tableData2.innerHTML = "Not Submitted Yet";

        } else{
            tableData2.innerHTML = contentsArray[i];
        }

        tableRow.appendChild(tableData1);
        tableRow.appendChild(tableData2);

        approvalsTable.appendChild(tableRow);

    }


    //append the table to the approvals section
    approvalSection.appendChild(approvalsTable);

    //add a space
    approvalSection.appendChild(document.createElement("br"));

}

function statementConverter(input){
    //use this to change the statement depending on the boolean value
    if (input){ // if the unput value is true
        // if the super/head/benco did ask you to provide more info
        return `Request Approved`

    }else { // otherwise if the input value is false
        return `Request Denied`

    }


}

function makeHeading (dataSection, id) {
    let heading = document.createElement("h4");
    heading.innerHTML = `Approval Status for Request with ID: ${id}`;
    dataSection.appendChild(heading);
}