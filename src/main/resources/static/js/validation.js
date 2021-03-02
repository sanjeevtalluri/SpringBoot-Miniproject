function validate(frm){
	let ename=frm.empName.value;
	let salary=frm.sal.value;
	let desig=frm.job.value;
	let flag =true;
	document.getElementById("sname").innerHTML="";
	document.getElementById("ssal").innerHTML="";
	document.getElementById("sjob").innerHTML="";
	frm.vflag.value="yes";
	//alert(frm.validation.value);
	if(ename.length==0||ename==null){
		document.getElementById("sname").innerHTML="employee name is required(js)";
		flag=false;
	}
	else if(ename.length<5 || ename.length>10){
		document.getElementById("sname").innerHTML="employee name must have min 5 and max 10 characters(js)";
		flag=false;
	}
	if(salary==null){
		document.getElementById("ssal").innerHTML="employee salary is required(js)";
		flag=false;
	}
	else if(salary<10000 || salary>1000000){
		document.getElementById("ssal").innerHTML="employee salary must be in between 10000 and 1000000(js)";
		flag=false;
	}
	else if(isNaN(salary)){
		document.getElementById("ssal").innerHTML="employee salary must be a numeric number(js)";
		flag=false;
	}
	if(desig.length==0||desig==null){
		document.getElementById("sjob").innerHTML="employee job is required(js)";
		flag=false;
	}
	else if(desig.length<5 || desig.length>9){
		document.getElementById("sjob").innerHTML="employee job must have min 5 and max 10 characters(js)";
		flag=false;
	}

	return flag;	
}