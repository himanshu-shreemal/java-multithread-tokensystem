function onClick(){
	var i;
	for (i = 0; i < 5; i++) {
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

apicall1 = (isChecked) => {
	let data = fetch("http://localhost:19090/generateToken?isChecked="+isChecked) // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall2 = (isChecked) => {
	let data = fetch("http://localhost:19090/generateToken?isChecked="+isChecked) // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall3 = (isChecked) => {
	let data = fetch("http://localhost:19090/generateToken?isChecked="+isChecked) // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall4 = (isChecked) => {
	let data = fetch("http://localhost:19090/generateToken?isChecked="+isChecked) // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
apicall5 = (isChecked) => {
	let data = fetch("http://localhost:19090/generateToken?isChecked="+isChecked) // Call the fetch function passing the url of the API as a parameter
	.then(response => response.json())
	return data;
}
counter1 = () => {
	var theDiv = document.getElementById("counter1");
	var isChecked = document.getElementById("counter1_chk").checked;
	let a = apicall1(isChecked);
	if(isChecked){
		a.then(data => theDiv.innerHTML += ','+"<span style='color: red;'>"+data+"</span>");
	}else{
		a.then(data => theDiv.innerHTML += ','+ data);
	}
}
counter2 = () => {
	var theDiv = document.getElementById("counter2");
	var isChecked = document.getElementById("counter2_chk").checked;
	let a = apicall2(isChecked);
	if(isChecked){
		a.then(data => theDiv.innerHTML += ','+"<span style='color: red;'>"+data+"</span>");
	}else{
		a.then(data => theDiv.innerHTML += ','+ data);
	}
}
counter3 = () => {
	var theDiv = document.getElementById("counter3");
	var isChecked = document.getElementById("counter3_chk").checked;
	let a = apicall3(isChecked);
	if(isChecked){
		a.then(data => theDiv.innerHTML += ','+"<span style='color: red;'>"+data+"</span>");
	}else{
		a.then(data => theDiv.innerHTML += ','+ data);
	}
}
counter4 = () => {
	var theDiv = document.getElementById("counter4");
	var isChecked = document.getElementById("counter4_chk").checked;
	let a = apicall4(isChecked);
	if(isChecked){
		a.then(data => theDiv.innerHTML += ','+"<span style='color: red;'>"+data+"</span>");
	}else{
		a.then(data => theDiv.innerHTML += ','+ data);
	}
}
counter5 = () => {
	var theDiv = document.getElementById("counter5");
	var isChecked = document.getElementById("counter5_chk").checked;
	let a = apicall5(isChecked);
	if(isChecked){
		a.then(data => theDiv.innerHTML += ','+"<span style='color: red;'>"+data+"</span>");
	}else{
		a.then(data => theDiv.innerHTML += ','+ data);
	}
}