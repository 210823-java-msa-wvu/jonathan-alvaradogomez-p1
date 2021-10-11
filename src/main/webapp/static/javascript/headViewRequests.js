//Create an event listener that will call some function when a button is clicked
document.getElementById("checkInfo").addEventListener("click", getData);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/headViewRequests?` ;

function getData(){
    console.log("'View Submissions' button was clicked");
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
        // before we parse the response and populate the data, let's empty out
        // what's inside the data section element

        let section1 = document.getElementById("section1");
        section1.innerHTML = "";

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
    let section1 = document.getElementById("section1");

    section1.appendChild(document.createElement("hr"));

    // Create a new heading element, populate it, then add it as a child to DOM
    let requestHeading = document.createElement("h3");
    requestHeading.innerHTML = "Pending Request";
    section1.appendChild(requestHeading);

    // Create a new table  element, populate it, then add it as a child to DOM
    // make each descriptor 
    let requestId = res[index].requestId;
    let staffId = res[index].staffId;
    let todayDate = res[index].todayDate;
    let eventDate = res[index].eventDate;
    let time = res[index].time;
    let location = res[index].location;
    let description = res[index].description;
    let cost = res[index].cost;
    let gradingFormat = res[index].gradingFormat;
    let finalGrade = res[index].finalGrade;
    let eventType = res[index].eventType;
    let justification = res[index].justification;

    // put each thing above into an array
    let contentsArray = [requestId, staffId, todayDate, time, eventDate, location, eventType, description, `$ ${cost}`, 
    gradingFormat, finalGrade, justification];

    let statementArray = ["Request ID","Staff ID", "Submission Date", "Submission Time","Event Start Date",
    "Event Location", "Event Type", "Event Description","Event Cost", "Event Grading Format", 
    "Final Grade", "Justification"];

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

    section1.appendChild(contentTable);

    //add a space
    section1.appendChild(document.createElement("br"))

    // Create a new heading element, populate it, then add it as a child to DOM
    // let additionalResponse = document.createElement("h4");
    // additionalResponse.innerHTML = "Additional Response from Employee";
    // section1.appendChild(additionalResponse);



    // create a button
    // let viewResponse = document.createElement("button");
    // viewResponse.setAttribute("id", "viewResponse");
    // viewResponse.innerHTML = "View Employee Response";
    // section1.appendChild(viewResponse);

    //make a new paragraph to append stuff to later.








    // Create a new heading element, populate it, then add it as a child to DOM
    // let moreInfo = document.createElement("h4");
    // moreInfo.innerHTML = "Request Additional Information";
    // section1.appendChild(moreInfo);

    


}
