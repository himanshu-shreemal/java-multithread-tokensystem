function onClickServe(){
	var i;
	for (i = 0; i < 5; i++) {
		service1();
		service2();
		service3();
		service4();
		service1();
		service3();
		service2();
		service2();
	}
}

serverTokenAPICall1 = () => {
	let data = fetch("http://localhost:19090/getTokenToServer?timeTakesToServer=2") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
serverTokenAPICall2 = () => {
	let data = fetch("http://localhost:19090/getTokenToServer?timeTakesToServer=3") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
serverTokenAPICall3 = () => {
	let data = fetch("http://localhost:19090/getTokenToServer?timeTakesToServer=3") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
serverTokenAPICall4 = () => {
	let data = fetch("http://localhost:19090/getTokenToServer?timeTakesToServer=1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
serverTokenAPICall5 = () => {
	let data = fetch("http://localhost:19090/getTokenToServer?timeTakesToServer=0") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
service1 = () => {
	var theDiv = document.getElementById("service1");
	let a = serverTokenAPICall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service2 = () => {
	var theDiv = document.getElementById("service2");
	let a = serverTokenAPICall2();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service3 = () => {
	var theDiv = document.getElementById("service3");
	let a = serverTokenAPICall3();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service4 = () => {
	var theDiv = document.getElementById("service4");
	let a = serverTokenAPICall4();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service5 = () => {
	var theDiv = document.getElementById("service5");
	let a = serverTokenAPICall5();
	a.then(data => theDiv.innerHTML += ','+ data);
}