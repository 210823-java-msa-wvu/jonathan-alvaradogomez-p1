//Create an event listener that will call some function when a button is clicked
document.getElementById("checkBalance").addEventListener("click", getBalance);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/moneyMatters?` ;

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


            for(let i=0; i < r.length; i++){
                console.log("printing i: ", i);
                console.log("printing r[i]: ", r[i]);
                console.log("function populateData(r, i) was called");

                populateBalance(r,i);
                
            }

            // populateBalance(r);

        }
    }
}

function populateBalance(res, index){
    let balanceSection = document.getElementById("balanceSection");

    // make an array to hold the table headings
    let tableHeadings = ["Employee ID","Available", "Pending", "Awarded"];
    let amounts = [res[index].staffId,res[index].available, res[index].pending, res[index].awarded];

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
        amountData.innerHTML = ` ${amounts[i]}`;
        ammountRow.appendChild(amountData);

    }

    //append the table to the balance section
    balanceSection.appendChild(balanceTable);


}

//=========================Ammounts Button Was Clicked===============================================

//Create an event listener that will call some function when a button is clicked
document.getElementById("checkAmounts").addEventListener("click", getData);

function getData(){
    console.log("'Change' button was clicked");
    console.log("baseURL: ", baseURL);

    //STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    //STEP 2: Set a call back function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    //STEP 3:  Open the request
    xhttp.open("GET", baseURL, true); // true is the default argument, but it is best to be explicit, this is asynchronous
    xhttp.setRequestHeader('type','requests');
    //STEP 4: Send the request
    xhttp.send(); //for GET requests, the send function does not have any arguments. 

    function receiveData(){
        // before we parse the response and populate the data, let's empty out
        // what's inside the data div element

        let amountSection = document.getElementById("amountSection");
        amountSection.innerHTML = "";


        // Check if the ready state is 'DONE' (aka '4') and if the HTTP Status is 'ok' (200)
        if (xhttp.readyState == 4 && xhttp.status==200){
            let r = xhttp.responseText;
            // console.log("logging r below:")
            // console.log(r);

            r = JSON.parse(r);
            console.log("r in JSON format below:");
            console.log(r);

            for(let i=0; i < r.length; i++){
                console.log("printing i: ", i);
                console.log("printing r[i]: ", r[i]);
                console.log("function populateData(r, i) was called");

                populateData(r,i);
                
            }

            // populateData(r,2);

        }
    }
}

function populateData(res, index) {
    let dataSection1 = document.getElementById("amountSection");

    dataSection1.appendChild(document.createElement("hr"));

    let requestId = res[index].requestId;
    let staffId = res[index].staffId;
    let cost = res[index].cost;

    // put each thing above into an array
    let contentsArray = [requestId, staffId, cost];
    let statementArray = ["Request ID", "Employee ID", "Cost"];


    let contentTable = document.createElement("table");

    for (let i = 0; i<contentsArray.length; i++){

        let tableRow = document.createElement("tr");
        let tableData1 = document.createElement("td");
        let tableData2 = document.createElement("td");

        tableData1.innerHTML = statementArray[i];
        tableData2.innerHTML = contentsArray[i];

        tableRow.appendChild(tableData1);
        tableRow.appendChild(tableData2);

        contentTable.appendChild(tableRow);



    }

    dataSection1.appendChild(contentTable);

    //add a space
    dataSection1.appendChild(document.createElement("br"));

    //create a form element
    let infoForm = document.createElement("form");
    infoForm.setAttribute("action","moneyMatters");
    infoForm.setAttribute("method", "POST");
    dataSection1.appendChild(infoForm); //changed the section

    fakeRequestId(requestId, infoForm);

    fakeEmployeeId(staffId, infoForm);

    //create a label element to put inside the form element 
    let changeCost = document.createElement("label");
    changeCost.setAttribute("for","changeCost");
    changeCost.innerHTML = "Enter a new amount: ";
    infoForm.appendChild(changeCost);

    //create an input element to place inside the label tag
    let percentageInput = document.createElement("input");
    percentageInput.setAttribute("type", "number");
    percentageInput.setAttribute("name", "changeCost");
    percentageInput.setAttribute("id", "changeCost");
    percentageInput.setAttribute("min", "0");
    percentageInput.setAttribute("max", "100");
    percentageInput.setAttribute("step", "0.01");
    changeCost.appendChild(percentageInput);

    infoForm.appendChild(document.createElement("br"));

    //create the input element, give it attributes, then append to dataSection
    let submitInput = document.createElement("input");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("id", "submit_form");
    submitInput.setAttribute("value", "Change Amount");
    infoForm.appendChild(submitInput);




}

function fakeRequestId (input, dataSection) {
    // create a label element to put inside the form element
    let idLabel = document.createElement("label");
    idLabel.setAttribute("for", "request_id");
    idLabel.innerHTML = "This is the associated Request ID: ";
    dataSection.appendChild(idLabel); 

    //create a select element to show request id for this current request: FAKE CHOICE**
    let idSelect = document.createElement("select");
    idSelect.setAttribute("name", "request_id");
    idSelect.setAttribute("id", "request_id");
    dataSection.appendChild(idSelect);

    //create the fake option as options to insert into the select tag above # needs to be res[n].requestID
    let fakeOption = document.createElement("option");
    fakeOption.setAttribute("value", input);
    fakeOption.innerHTML = input;
    idSelect.appendChild(fakeOption);

    //add a space
    dataSection.appendChild(document.createElement("br"));

}

function fakeEmployeeId (input, dataSection) {
    // create a label element to put inside the form element
    let idLabel = document.createElement("label");
    idLabel.setAttribute("for", "employee_id");
    idLabel.innerHTML = "This is the associated Employee ID: ";
    dataSection.appendChild(idLabel); 

    //create a select element to show request id for this current request: FAKE CHOICE**
    let idSelect = document.createElement("select");
    idSelect.setAttribute("name", "employee_id");
    idSelect.setAttribute("id", "employee_id");
    dataSection.appendChild(idSelect);

    //create the fake option as options to insert into the select tag above # needs to be res[n].requestID
    let fakeOption = document.createElement("option");
    fakeOption.setAttribute("value", input);
    fakeOption.innerHTML = input;
    idSelect.appendChild(fakeOption);

    //add a space
    dataSection.appendChild(document.createElement("br"));

}