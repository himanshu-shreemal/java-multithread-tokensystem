function onClick(){
	var i;
	for (i = 0; i < 10; i++) {
		counter1();
		counter2();
		counter3();
		counter4();
		counter1();
		counter5();
		counter3();
		counter2();
		counter2();
	}
}

apicall1 = () => {
	let data = promise("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall2 = () => {
	let data = promise("http://localhost:19090/test1") // Call the fetch function passing the url of the API as a parameter
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
counter1 = () => {
	console.log('counter1');
	var theDiv = document.getElementById("counter1");
	let a = apicall1();
	a.then(data => theDiv.innerHTML += ','+ data);
}
counter2 = () => {
	console.log('counter2');
	var theDiv = document.getElementById("counter2");
	let a = apicall2();
	a.then(data => theDiv.innerHTML += ','+ data);
}
counter3 = () => {
	console.log('counter3');
	var theDiv = document.getElementById("counter3");
	let a = apicall3();
	a.then(data => theDiv.innerHTML += ','+ data);
}
counter4 = () => {
	console.log('counter4');
	var theDiv = document.getElementById("counter4");
	let a = apicall4();
	a.then(data => theDiv.innerHTML += ','+ data);
}
counter5 = () => {
	console.log('counter5');
	var theDiv = document.getElementById("counter5");
	let a = apicall5();
	a.then(data => theDiv.innerHTML += ','+ data);
}