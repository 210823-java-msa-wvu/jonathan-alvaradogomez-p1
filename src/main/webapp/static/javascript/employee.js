//Create an event listener that will call some function when a button is clicked
document.getElementById("viewProfile").addEventListener("click", getData);

// Save URL into a variable. This URL ~should~ hold information about requests in JSON format
let baseURL  = `http://localhost:8080/Project1/static/employee?` ;

function getData(){
    console.log("'ViewProfile' button was clicked");
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

        let section1 = document.getElementById("section1");
        section1.innerHTML = "";


        // Check if the ready state is 'DONE' (aka '4') and if the HTTP Status is 'ok' (200)
        if (xhttp.readyState == 4 && xhttp.status==200){
            let r = xhttp.responseText;

            r = JSON.parse(r);
            // console.log("r in JSON format below:");
            // console.log(r);

            // for(let i=0; i < r.length; i++){
            //     console.log("printing i: ", i);
            //     console.log("printing r[i]: ", r[i]);
            //     console.log("function populateData(r, i) was called");

            //     populateData(r,i);
                
            // }

            populateData(r);

        } else if (xhttp.readyState == 4 && xhttp.status==500) {

            denied();

        }
    }
}

function populateData(res){

    let section1 = document.getElementById("section1");

    let welcome = document.createElement("h3");
    welcome.innerHTML = ` Hello ${res.firstName} ${res.lastName}!`;
    section1.appendChild(welcome);

    let ol = document.createElement("ol");
    section1.appendChild(ol);

    let question = document.createElement("h4");
    question.innerHTML = "What would you like to do today?";
    ol.appendChild(question);

    let item1 = document.createElement("li");
    item1.innerHTML = `<a href="request.html">Make a new Reimbursement Request</a>`;
    ol.appendChild(item1);

    ol.appendChild(document.createElement("br"));

    let item2 = document.createElement("li");
    item2.innerHTML = `<a href="uploadFinal.html">Submit Final Grade or Final Presentation</a>`;
    ol.appendChild(item2);

    ol.appendChild(document.createElement("br"));


    let item3 = document.createElement("li");
    item3.innerHTML = `<a href="employeeAddInfo.html">Check if Additional Information is Needed</a>`;
    ol.appendChild(item3);

    ol.appendChild(document.createElement("br"));

    let item4 = document.createElement("li");
    item4.innerHTML = `<a href="balanceStatus.html">Review Balance and Approval Statuses</a>`;
    ol.appendChild(item4);

    ol.appendChild(document.createElement("br"));

    let item5 = document.createElement("li");
    item5.innerHTML = `<a href="deleteRequest.html">Delete a Request</a>`;
    ol.appendChild(item5);


}


function denied(){
    let section1 = document.getElementById("section1");

    let denial = document.createElement("p");
    denial.innerHTML = "Your session was invalidated. Please login again to access Tiramisu features.";
    section1.appendChild(denial);
}





//Logout Functionality
document.getElementById("logoutButton").addEventListener("click", logout);

function logout() {
    console.log("Logout button was clicked")
}

