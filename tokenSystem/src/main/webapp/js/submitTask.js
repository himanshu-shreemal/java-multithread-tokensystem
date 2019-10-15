function onClickStart1(){
	var i;
	for (i = 0; i < 5; i++) {
		service1();
		service4();
		service5();
		service3();
		service2();
	}
}

getTokenAPICall = (tokenNumber,timeTakesToServer) => {
	 // Call the fetch function passing the url of the API as a parameter
	let data = fetch("http://localhost:19090/submitTokenToServer",
			{
	    headers: {
	      'Accept': 'application/json',
	      'Content-Type': 'application/json'
	    },
	    method: "POST",
	    body: JSON.stringify({"tokenNumber": tokenNumber, "timeTakeToServer": timeTakesToServer})
	})
	.then(response => response.json())
	return data;
}
start1 = () => {
	var theDiv = document.getElementById("start1");
	theDiv.innerHTML = "Processing...";
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML ="Success");
}
start2 = () => {
	var theDiv = document.getElementById("start2");
	theDiv.innerHTML = "Processing...";
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML ="Success");
}
start3 = () => {
	var theDiv = document.getElementById("start3");
	theDiv.innerHTML = "Processing...";
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML ="Success");
}
start4 = () => {
	var theDiv = document.getElementById("start4");
	theDiv.innerHTML = "Processing...";
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML ="Success");
}
start5 = () => {
	var theDiv = document.getElementById("start5");
	theDiv.innerHTML = "Processing...";
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML ="Success");
}