/**
 * Like a bauuuussssssss!!
 */

$(document).ready(function(){
	console.log('Begin');
	hideEverything();
	$(document).on("click", '#logInButton', function(){$('#logInButton').button('loading'); authenticateLogin();return false;});
	$(document).on("click", '#logOutButton', function(){logOut();return false;});
});



var hideEverything = function(){
	console.log('hideEverything()');
	$('header .logOutArea').hide();
	$('#nav').empty();
	$('#nav').hide();
	$('#tabs').hide();
};

var logOut = function(){
	console.log('logOut');
	$('header .logInArea').show();
	hideEverything();
	
	$('#adminTab').attr('class','tab-pane');
	$('#europeTab').attr('class','tab-pane');
	$('#nAmericaTab').attr('class','tab-pane');
	$('#sAmericaTab').attr('class','tab-pane');
	$('#asiaTab').attr('class','tab-pane');
	
	
}

var authenticateLogin = function(){
	console.log('athenticateLogin');
	
	var UserId = document.getElementById('UserId').value;
	var Password = document.getElementById('Password').value;
	
	if(UserId != '' && Password != ''){
		
		$.ajax({
			type : 'GET',
			url: 'http://localhost:8080/DonalWebApp/rest/Users/'+ UserId+'&'+Password,
			dataType: "json",
			success: renderBody
		})
		console.log('Welcome '+ UserId);
	}
	
	else{
		alert("You must enter a valid UserId and Password to continue");
		
	}
	$('#logInButton').button('reset');
	document.getElementById('UserId').value = '';
	document.getElementById('Password').value = '';
};


var renderBody = function(data){
	console.log('renderBody');
	
	
	document.getElementById('UserId').value = '';
	document.getElementById('Password').value = '';
	
		if(data[0] == "admin"){
			console.log('Welcome Admin');
			$('header .logInArea').hide();
			$('header .logOutArea').show();
			$('body #nav').show();
			$('#tabs').show();
			
			$('#nav').append('<li class ="active" id = "admin"><a href="#adminTab" role="tab" data-toggle="tab">Admin</a></li>');
			$('#nav').append('<li id = "europeTab"><a href="#europeTab" role="tab" data-toggle="tab">Europe Tab</a></li>');
			$('#nav').append('<li id = "nAmericaTab"><a href="#nAmericaTab" role="tab" data-toggle="tab">North America Tab</a></li>');
			$('#nav').append('<li id = "sAmericaTab"><a href="#sAmericaTab" role="tab" data-toggle="tab">South America Tab</a></li>');
			$('#nav').append('<li id = "asiaTab"><a href="#asiaTab" role="tab" data-toggle="tab">Asia Tab</a></li>');
			$('#adminTab').attr('class','tab-pane active');
		}
		else if(data[0]== "user"){
			console.log('Welcome user');
			$('header .logInArea').hide();
			$('header .logOutArea').show();
			$('body #nav').show();
			$('#tabs').show();
			
			$('#nav').append('<li class = "active" id = "europeTab"><a href="#europeTab" role="tab" data-toggle="tab">Europe Tab</a></li>');
			$('#nav').append('<li id = "nAmericaTab"><a href="#nAmericaTab" role="tab" data-toggle="tab">North America Tab</a></li>');
			$('#nav').append('<li id = "sAmericaTab"><a href="#sAmericaTab" role="tab" data-toggle="tab">South America Tab</a></li>');
			$('#nav').append('<li id = "asiaTab"><a href="#asiaTab" role="tab" data-toggle="tab">Asia Tab</a></li>');
			$('#europeTab').attr('class','tab-pane active');
		}
		else if(data[0]== "invalid"){
			alert("User name did not match password. Please try again")
		}
};