/**
 * 
 */

function checkPasswordMatch() {
	var salary = $("#checkBasicSalary").val();

	
	 if(isNaN(salary)){
		 $("#basicSalary").html("Salary should be number");
		 $("#showMessage").prop('disabled', true);
		 }else{
			 $("#basicSalary").html("");
			 $("#showMessage").prop('disabled', false);
		 }
}

$(document).ready(function(){
	
	$("#checkBasicSalary").keyup(checkPasswordMatch);
	
});