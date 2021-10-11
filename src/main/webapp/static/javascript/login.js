//Create an event listener that will call some function when a button is clicked
document.getElementById("login_button").addEventListener("click", staffName);

//save the url we plan to use as a variable 
let baseURL = `http://localhost:8080/Project1/login`;

//COME BACK TO MAKE IT SO THAT THIS WORKS FOR ALL STAFF, NOT JUST EMPLOYEE

//let's use the FetchAPI below to place staff name in the html
// async function staffName(){
//     let staffType =  `employee`; // how do we get the JSON info for this?

//     let res = await fetch(baseURL + staffType);
// }

