//Create an event listener that will call some function when a button is clicked
document.getElementById("viewSubmissions").addEventListener("click", getData);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/bencoAppDen?` ;

// let approveDeny = document.createElement("h4");
//     approveDeny.innerHTML = "Approve or Deny this submission";
//     dataSection1.appendChild(approveDeny);

function getData(){
    console.log("'View' button was clicked");
    console.log("baseURL: ", baseURL);

    //STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    //STEP 2: Set a call back function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    //STEP 3:  Open the request
    xhttp.open("GET", baseURL, true); // true is the default argument, but it is best to be explicit, this is asynchronous

    //STEP 4: Send the request
    xhttp.send(); //for GET requests, the send function does not have any arguments. 

    function receiveData(){
        // before we parse the response and populate the data, let's empty out
        // what's inside the data div element

        let dataSection1 = document.getElementById("section1");
        dataSection1.innerHTML = "";


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
    //This is where we do our DOM manipulation
    let dataSection1 = document.getElementById("section1");

    dataSection1.appendChild(document.createElement("hr"));

    let responseHeading = document.createElement("h4");
    responseHeading.innerHTML = "Submission to Consider";
    dataSection1.appendChild(responseHeading);

    // Create a new table  element, populate it, then add it as a child to DOM
    // first, make each descriptor 
    let approvalId = res[index].approvalId;
    let staffId = res[index].staffId;
    let finalGrade = res[index].finalGrade;
    let staffMoreInfo = res[index].staffMoreInfo;
    let bencoDecision = res[index].bencoDecision;
    let bencoReasoning = res[index].bencoReasoning;

    
    // put each thing above into an array
    let contentsArray = [approvalId, staffId, finalGrade, staffMoreInfo, bencoDecision, bencoReasoning];
    let statementArray = ["Request ID", "Employee ID", "Final Grade", "Employee Additional Information", "Your Decision", "Your Reasoning"];

    // console.log(contentsArray);
    // console.log(statementArray);

    // iterate thru each item in the array (contentTable) and make it into a list item
    let contentTable = document.createElement("table");

    for (let i = 0; i<contentsArray.length; i++){
        let tableRow = document.createElement("tr");
        let tableData1 = document.createElement("td");
        let tableData2 = document.createElement("td");

        tableData1.innerHTML = statementArray[i];

        if(typeof(contentsArray[i]) == "boolean" ){
            tableData2.innerHTML = statementConverter(contentsArray[i]);
        }else{
            tableData2.innerHTML = contentsArray[i];
        }

        tableRow.appendChild(tableData1);
        tableRow.appendChild(tableData2);

        contentTable.appendChild(tableRow);


    }

    dataSection1.appendChild(contentTable);

    //add a space
    dataSection1.appendChild(document.createElement("br"));

    let bencoMoreInfo = res[index].bencoMoreInfo;

    let decision = document.createElement("h4");
    decision.innerHTML = "Make a Decision";
    dataSection1.appendChild(decision);

    //create a form element
    let infoForm = document.createElement("form");
    infoForm.setAttribute("action","bencoAppDen");
    infoForm.setAttribute("method", "POST");
    dataSection1.appendChild(infoForm);

    fakeRequestId(approvalId,infoForm);
    fakeEmployeeId(staffId, infoForm);


    //this creates a 'fake' option for the form.
    options(infoForm); //changed the section

    //make a text area so that employee can respond to 
    makeTextArea(infoForm); //changed the section


    
}

function makeTextArea(dataSection) {
    // create a label for the additional information
    let infoLabel = document.createElement("label");
    infoLabel.setAttribute("for", "reason");
    infoLabel.innerHTML = "Please give reasoning for your decision above:";
    dataSection.appendChild(infoLabel);

    //add a space
    dataSection.appendChild(document.createElement("br"));

    //create a textarea tag in order to type out your additional information
    let infoTextarea = document.createElement("textarea");
    infoTextarea.setAttribute("name", "reason");
    infoTextarea.setAttribute("id", "reason");
    infoTextarea.setAttribute("cols", "50");
    infoTextarea.setAttribute("rows", "10");
    dataSection.appendChild(infoTextarea);

    //add a space
    dataSection.appendChild(document.createElement("br"));

    //create the input element, give it attributes, then append to dataSection
    let submitInput = document.createElement("input");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("id", "submit_form");
    submitInput.setAttribute("value", "Update My Decision");
    dataSection.appendChild(submitInput);

    //add a space
    dataSection.appendChild(document.createElement("br"));

}


function options (dataSection) {
    // create a label element to put inside the form element
    let idLabel = document.createElement("label");
    idLabel.setAttribute("for", "answer");
    idLabel.innerHTML = "My decision is to: ";
    dataSection.appendChild(idLabel); 

    //create a select element to show request id for this current request: FAKE CHOICE**
    let idSelect = document.createElement("select");
    idSelect.setAttribute("name", "answer");
    idSelect.setAttribute("id", "answer");
    dataSection.appendChild(idSelect);

    //create the approval option
    let approve = document.createElement("option");
    approve.setAttribute("value", true);
    approve.innerHTML = "Approve";
    idSelect.appendChild(approve);

    //create the deny option
    let deny = document.createElement("option");
    deny.setAttribute("value", false);
    deny.innerHTML = "Deny";
    idSelect.appendChild(deny);

    //add a space
    dataSection.appendChild(document.createElement("br"));

}


function statementConverter(input){
    //use this to change the statement depending on the boolean value
    if (input){ // if the unput value is true
        // if the super/head/benco did ask you to provide more info
        return `Approved`

    }else { // otherwise if the input value is false
        return `Not Approved `

    }


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