//Create an event listener that will call some function when a button is clicked
document.getElementById("check_info").addEventListener("click", getData);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/employeeAddInfo?` ;


function getData(){
    console.log("'Additional Information' button was clicked");
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

        // let dataSection2 = document.getElementById("section2");
        // dataSection2.innerHTML = "";

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

    makeAdditionalReqHeading(dataSection1);


    // Create a new table  element, populate it, then add it as a child to DOM
    // first, make each descriptor 
    let approvalId = res[index].approvalId;
    let superMoreInfo = res[index].superMoreInfo;
    let headMoreInfo = res[index].headMoreInfo;
    let bencoMoreInfo = res[index].bencoMoreInfo;

    // put each thing above into an array
    let contentsArray = [approvalId, superMoreInfo, headMoreInfo, bencoMoreInfo];
    let statementArray = ["Request ID", "Direct Supervisor", "Department Head", "Benefits Coordinator"];

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


    //This is where we do our DOM manipulation for data section 2
    // let dataSection2 = document.getElementById("section2"); //changed section

    // put the staff's additional information response from the database as a variable
    let staffMoreInfo = res[index].staffMoreInfo;


    // BEGIN CODE SECTION ABOUT SHOWING/ UPDAING REPONSES 

    if (superMoreInfo == false && headMoreInfo == false && bencoMoreInfo == false){
        // in this case, no additional info requests are being made


        makeResponseHeading(dataSection1);
        // let responseHeading = document.createElement("h4");
        // responseHeading.innerHTML = "Respond to Requests Below";
        // dataSection1.appendChild(responseHeading);


        let paragraph = document.createElement("p");
        paragraph.innerHTML = `No additional information is being requested by any of the Direct Supervisors, 
                               Department Heads, or Benefits Coordinators at this time.`;
        dataSection1.appendChild(paragraph); //changed the section

    } else if(staffMoreInfo == ""){ 
        // No, the employee has NOT provided a response 
        // bosses are requesting more information 

        makeResponseHeading(dataSection1);
        // let responseHeading = document.createElement("h4");
        // responseHeading.innerHTML = "Respond to Requests Below";
        // dataSection1.appendChild(responseHeading);

        let paragraph = document.createElement("p");
        paragraph.innerHTML = `At least one of the above staff has requested additional information
                            for a Request with ID of ${approvalId}. `;
        dataSection1.appendChild(paragraph); //changed the section

        //create a form element  for later
        let infoForm = document.createElement("form");
        infoForm.setAttribute("action","employeeAddInfo");
        infoForm.setAttribute("method", "POST");
        dataSection1.appendChild(infoForm); //changed the section

        //this creates a 'fake' option for the form.
        fakeOption(approvalId, infoForm); //changed the section

        //make a text area so that employee can respond to 
        makeTextArea(infoForm); //changed the section

    } else if (staffMoreInfo != "") {
        // Yes, the employee HAS provided a response 
        // bosses are requesting more information 

        // let responseHeading = document.createElement("h4");
        // responseHeading.innerHTML = "Respond to Requests Below";
        // dataSection1.appendChild(responseHeading);

        makeResponseHeading(dataSection1);

        let paragraph = document.createElement("p");
        paragraph.innerHTML = `At least one of the above staff has requested additional information
                            for a Request with ID of ${approvalId}. `;
        dataSection1.appendChild(paragraph); //changed the section

        // //add a space
        // dataSection2.appendChild(document.createElement("br"));

        let paragraph2 = document.createElement("p");
        paragraph2.innerHTML = `Current Response:`;
        dataSection1.appendChild(paragraph2); //changed the section

        // //add a space
        // dataSection2.appendChild(document.createElement("br"));

        let paragraph3 = document.createElement("p");
        paragraph3.innerHTML = `"${staffMoreInfo}"`;
        dataSection1.appendChild(paragraph3); //changed the section

        //create a form element  for later
        let infoForm = document.createElement("form");
        infoForm.setAttribute("action","employeeAddInfo");
        infoForm.setAttribute("method", "POST");
        dataSection1.appendChild(infoForm); //changed the section

        //this creates a 'fake' option for the form.
        fakeOption(approvalId, infoForm); //changed the section

        //make a text area so that employee can respond
        makeTextArea(infoForm); //changed the section


    }

    //add a space
    dataSection1.appendChild(document.createElement("br")); //changed the section



}


function makeTextArea(dataSection) {
    // create a label for the additional information
    let infoLabel = document.createElement("label");
    infoLabel.setAttribute("for", "information");
    infoLabel.innerHTML = "Please enter new additional information below, which will replace your current response: ";
    dataSection.appendChild(infoLabel);

    //add a space
    dataSection.appendChild(document.createElement("br"));

    //create a textarea tag in order to type out your additional information
    let infoTextarea = document.createElement("textarea");
    infoTextarea.setAttribute("name", "information");
    infoTextarea.setAttribute("id", "information");
    infoTextarea.setAttribute("cols", "50");
    infoTextarea.setAttribute("rows", "10");
    dataSection.appendChild(infoTextarea);

    //add a space
    dataSection.appendChild(document.createElement("br"));

    //create the input element, give it attributes, then append to dataSection
    let submitInput = document.createElement("input");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("id", "submit_form");
    submitInput.setAttribute("value", "Update My Response");
    dataSection.appendChild(submitInput);

    //add a space
    dataSection.appendChild(document.createElement("br"));

}

function fakeOption (input, dataSection) {
    // create a label element to put inside the form element
    let idLabel = document.createElement("label");
    idLabel.setAttribute("for", "approval_id");
    idLabel.innerHTML = "This is the associated Request ID: ";
    dataSection.appendChild(idLabel); 

    //create a select element to show request id for this current request: FAKE CHOICE**
    let idSelect = document.createElement("select");
    idSelect.setAttribute("name", "approval_id");
    idSelect.setAttribute("id", "approval_id");
    dataSection.appendChild(idSelect);

    //create the fake option as options to insert into the select tag above # needs to be res[n].requestID
    let fakeOption = document.createElement("option");
    fakeOption.setAttribute("value", input);
    fakeOption.innerHTML = input;
    idSelect.appendChild(fakeOption);

    //add a space
    dataSection.appendChild(document.createElement("br"));

}

function makeResponseHeading (dataSection) {
    let responseHeading = document.createElement("h4");
    responseHeading.innerHTML = "Respond to Above Request";
    dataSection.appendChild(responseHeading);
}

function makeAdditionalReqHeading (dataSection) {
    let responseHeading = document.createElement("h4");
    responseHeading.innerHTML = "Additional Information Request";
    dataSection.appendChild(responseHeading);
}



function statementConverter(input){
    //use this to change the statement depending on the boolean value
    if (input){ // if the unput value is true
        // if the super/head/benco did ask you to provide more info
        return `Please provide additional information
                regarding this Reimbursement Request.`

    }else { // otherwise if the input value is false
        return `No additional information has been requested at this time.`

    }


}