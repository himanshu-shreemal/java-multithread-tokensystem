function onClickServe(){
		service1();
		service4();
		service5();
		service3();
		service2();
}

getTokenAPICall = () => {
	 // Call the fetch function passing the url of the API as a parameter
	let data = fetch("http://localhost:19090/getTokenToServer")
	.then(response => response.json())
	return data;
}
service1 = () => {
	var theDiv = document.getElementById("service1");
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service2 = () => {
	var theDiv = document.getElementById("service2");
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service3 = () => {
	var theDiv = document.getElementById("service3");
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service4 = () => {
	var theDiv = document.getElementById("service4");
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service5 = () => {
	var theDiv = document.getElementById("service5");
	let a = getTokenAPICall();
	a.then(data => theDiv.innerHTML += ','+ data);
}