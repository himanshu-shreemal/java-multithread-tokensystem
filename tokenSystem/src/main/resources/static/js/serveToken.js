function onClickServe(){
	var i;
	for (i = 0; i < 20; i++) {
		service1();
		service2();
		service3();
		service4();
		service1();
		service5();
		service3();
		service2();
		service2();
	}
}

apicall1 = () => {
	let data = fetch("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall2 = () => {
	let data = fetch("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall3 = () => {
	let data = fetch("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall4 = () => {
	let data = fetch("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall5 = () => {
	let data = fetch("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
service1 = () => {
	console.log('service1');
	var theDiv = document.getElementById("service1");
	let a = apicall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service2 = () => {
	console.log('service2');
	var theDiv = document.getElementById("service2");
	let a = apicall2();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service3 = () => {
	console.log('service3');
	var theDiv = document.getElementById("service3");
	let a = apicall3();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service4 = () => {
	console.log('service4');
	var theDiv = document.getElementById("service4");
	let a = apicall4();
	a.then(data => theDiv.innerHTML += ','+ data);
}
service5 = () => {
	console.log('service5');
	var theDiv = document.getElementById("service5");
	let a = apicall5();
	a.then(data => theDiv.innerHTML += ','+ data);
}