// try AJAX because I don't know how to use Fetch (RIP it isn't working for me :'(   ), 

/**
 *  * AJAX Workflow: starts from the client-side, and is as follows:
 * 1. A client event occurs on a webpage (i.e. user clicks a button).
 * 2. JavaScript creates an XMLHttpRequest object.
 * 3. The XMLHttpRequest object makes an asyncornous call to the server. 
 * 4. The server processes the recieved HTTPRequest.
 * 5. The server creates a response and sends data back to the browser. 
 * 6. The browser the processes the returned data using JavaScript.
 * 7. The page content is updated (by JavaScript).
 */

//Create an event listener that will call some function when a button is clicked
document.getElementById("active_request").addEventListener("click", getData);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/uploadFinal?` ;


function getData(){
    console.log("'View Active Request' button was clicked");
    console.log("baseURL: ", baseURL);

    //STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    //STEP 2: Set a call back function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    //STEP 3:  Open the request
    xhttp.open("GET", baseURL, true); // true is the default argument, but it is best to be explicit, this is asynchronous

    //STEP 4: Send the request
    xhttp.send(); //for GET requests, the send function does not have any arguments. 


    function receiveData() {
        /**
         * Different ready states of an XMLHttpRequest Object
         * 
         * 0: Unsent
         * 1: Opened (Not sent yet)
         * 2: Headers Received  (we have now sent)
         * 3. Loading (server does what it needs to process the request)
         * 4: Done (Server has sent back response)
         * 
         */

        // before we parse the response and populate the data, let's empty out
        // what's inside the data section element

        let dataSection = document.getElementById("data");
        dataSection.innerHTML = "";

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
        }
    }
}



function populateData(res, index){
    //This is where we do our DOM manipulation
    let dataSection = document.getElementById("data");

    dataSection.appendChild(document.createElement("hr"));

    // Create a new heading element, populate it, then add it as a child to DOM
    let requestHeading = document.createElement("h3");
    requestHeading.innerHTML = "Pending Request";
    dataSection.appendChild(requestHeading);



    // Create a new table  element, populate it, then add it as a child to DOM
    // make each descriptor 
    let requestId = res[index].requestId;
    console.log("Logging requestId: ", requestId);

    let eventDate = res[index].eventDate;
    console.log("Logging eventDate: ", eventDate);

    let eventType = res[index].eventType;
    console.log("Logging eventType: ", eventType);

    let description = res[index].description;
    console.log("Logging description: ", description);

    let gradingFormat = res[index].gradingFormat;
    console.log("Logging gradingFormat: ", gradingFormat);

    // put each thing above into an array
    let contentsArray = [requestId, eventDate, eventType, description, gradingFormat];
    let statementArray = ["Request ID", "Event Start Date", "Event Type", "Event Description", "Event Grading Format"];

    // iterate thru each item in the array (contentTable) and make it into a list item
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

    dataSection.appendChild(contentTable);

    //add a space
    dataSection.appendChild(document.createElement("br"));


    

    if (res[index].finalGrade != ""){
        console.log("A final grade has already been submitted for request with a requestId:", res[index].requestId);

        //create a button to let user know that a grade has already been submitted for that request
        let gradeSubmitted = document.createElement("button");
        gradeSubmitted.innerHTML = "Grade Already Submitted!";
        gradeSubmitted.setAttribute("class", "Button1");
        dataSection.appendChild(gradeSubmitted);
    } else {
        console.log("No final grade for the request with a requestId:", res[index].requestId);

        //create a form element 
        let submitFinalGrade = document.createElement("form");
        submitFinalGrade.setAttribute("action", "uploadFinal");
        submitFinalGrade.setAttribute("method", "POST");
        dataSection.appendChild(submitFinalGrade);

        // There are 2 cases: the requests with a letter grade and the ones with a score as a grade

        if(res[index].gradingFormat == "percentage"){ // if the gradingFormat is a percentage

            // create a label element to put inside the form element
            let idLabel = document.createElement("label");
            idLabel.setAttribute("for", "request_id");
            idLabel.innerHTML = "This is the associated Request ID: ";
            submitFinalGrade.appendChild(idLabel);

            //create a select element to show request id for this current request: FAKE CHOICE**
            let idSelect = document.createElement("select");
            idSelect.setAttribute("name", "request_id");
            idSelect.setAttribute("id", "request_id");
            submitFinalGrade.appendChild(idSelect);

            //create the fake option as options to insert into the select tag above # needs to be res[n].requestID
            let fakeOption = document.createElement("option");
            fakeOption.setAttribute("value", requestId);
            fakeOption.innerHTML = requestId;
            idSelect.appendChild(fakeOption);

            //add a space
            submitFinalGrade.appendChild(document.createElement("br"));

            
            //create a label element to put inside the form element 
            let percentageLabel = document.createElement("label");
            percentageLabel.setAttribute("for","percentage");
            percentageLabel.innerHTML = "Enter your final score for the event: ";
            submitFinalGrade.appendChild(percentageLabel);

            //create an input element to place inside the label tag
            let percentageInput = document.createElement("input");
            percentageInput.setAttribute("type", "number");
            percentageInput.setAttribute("name", "percentage");
            percentageInput.setAttribute("id", "percentage");
            percentageInput.setAttribute("min", "0");
            percentageInput.setAttribute("max", "100");
            percentageInput.setAttribute("step", "0.1");
            percentageLabel.appendChild(percentageInput);

            //add a space
            submitFinalGrade.appendChild(document.createElement("br"));

            //create the input element, give it attributes, then append to the submitFinalGrade
            let submitInput = document.createElement("input");
            submitInput.setAttribute("type", "submit");
            submitInput.setAttribute("id", "submit_request");
            submitInput.setAttribute("value", "Submit Final Grade");
            submitFinalGrade.appendChild(submitInput);

        }else{ // if the grading format is a letter grade

            // create a label element to put inside the form element
            let idLabel = document.createElement("label");
            idLabel.setAttribute("for", "request_id");
            idLabel.innerHTML = "This is the associated Request ID: ";
            submitFinalGrade.appendChild(idLabel);

            //create a select element to show request id for this current request: FAKE CHOICE**
            let idSelect = document.createElement("select");
            idSelect.setAttribute("name", "request_id");
            idSelect.setAttribute("id", "request_id");
            submitFinalGrade.appendChild(idSelect);

            //create the fake option as options to insert into the select tag above # needs to be res[n].requestID
            let fakeOption = document.createElement("option");
            fakeOption.setAttribute("value", requestId);
            fakeOption.innerHTML = requestId;
            idSelect.appendChild(fakeOption);

            //add a space
            submitFinalGrade.appendChild(document.createElement("br"));


            //create a label element to put inside the form element 
            let letterLabel = document.createElement("label");
            letterLabel.setAttribute("for", "letter");
            letterLabel.innerHTML = "Select the Final Grade you received for this event:";
            submitFinalGrade.appendChild(letterLabel);

            //create a select element to provide the different letter grade options:
            let letterSelect = document.createElement("select");
            letterSelect.setAttribute("name", "letter");
            letterSelect.setAttribute("id", "letter");
            submitFinalGrade.appendChild(letterSelect);
            
            

            //create the different letter grades as options to insert into the select tag above (A, B, C, D, F)
            let optionA = document.createElement("option");
            optionA.setAttribute("value","A");
            optionA.innerHTML = "A";
            letterSelect.appendChild(optionA);

            let optionB = document.createElement("option");
            optionB.setAttribute("value","B");
            optionB.innerHTML = "B";
            letterSelect.appendChild(optionB);

            let optionC = document.createElement("option");
            optionC.setAttribute("value","C");
            optionC.innerHTML = "C";
            letterSelect.appendChild(optionC);

            let optionD = document.createElement("option");
            optionD.setAttribute("value","D");
            optionD.innerHTML = "D";
            letterSelect.appendChild(optionD);

            let optionF = document.createElement("option");
            optionF.setAttribute("value","F");
            optionF.innerHTML = "F";
            letterSelect.appendChild(optionF);

            //add a space
            submitFinalGrade.appendChild(document.createElement("br"));


            //create an input element to submit this form. 
            //or would I make a button, so it dynamically update the same page? test it using colors maybe 
            //nah, just use a form and follow something similar to the request html

            //create the input element, give it attributes, then append to the submitFinalGrade
            let submitInput = document.createElement("input");
            submitInput.setAttribute("type", "submit");
            submitInput.setAttribute("id", "submit_request");
            submitInput.setAttribute("value", "Submit Final Grade");
            submitFinalGrade.appendChild(submitInput);


        }

    }

}

// function submitGrade(){

// }