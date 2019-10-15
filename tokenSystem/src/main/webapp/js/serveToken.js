function onClickServe(){
		service1();
		service4();
		service5();
		service2();
		service3();
}

serverTokenAPICall1 = () => {
	let data = fetch("http://localhost:19090/getTokenToServer") // Call the fetch function passing the url of the API as a parameter
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
	let a = serverTokenAPICall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service3 = () => {
	var theDiv = document.getElementById("service3");
	let a = serverTokenAPICall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service4 = () => {
	var theDiv = document.getElementById("service4");
	let a = serverTokenAPICall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service5 = () => {
	var theDiv = document.getElementById("service5");
	let a = serverTokenAPICall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}