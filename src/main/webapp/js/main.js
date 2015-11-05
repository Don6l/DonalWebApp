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
	
};

var logOut = function(){
	console.log('logOut');
	$('header .logInArea').show();
	hideEverything();
	
	$('#adminTab').attr('class','tab-pane');
	$('#europeTab').attr('class','tab-pane');
	$('.tab-content').hide();
	document.getElementById('NewUserId').value= '';
	document.getElementById('NewPassword').value= '';
	
};

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
			$('.tab-content').show();
			$(document).on("click", '#addNewUser', function(){addUserToDataBase();return false;});
			console.log("Show tab-content");
			$('#nav').append('<li class ="active" id = "adminTab"><a href="#admin" role="tab" data-toggle="tab">Admin</a></li>');
			$('#nav').append('<li id = "europeTab"><a href="#europe" role="tab" data-toggle="tab">Europe</a></li>');
			$('#nav').append('<li id = "nAmericaTab"><a href="#nAmerica" role="tab" data-toggle="tab">North America</a></li>');
			$('#nav').append('<li id = "sAmericaTab"><a href="#sAmerica" role="tab" data-toggle="tab">South America</a></li>');
			$('#nav').append('<li id = "asiaTab"><a href="#asia" role="tab" data-toggle="tab">Asia</a></li>');
			$('#admin').attr('class','tab-pane active');
		}
		else if(data[0]== "user"){
			console.log('Welcome user');
			$('header .logInArea').hide();
			$('header .logOutArea').show();
			$('body #nav').show();
			$('.tab-content').show();
			
			console.log("Show tab-content");
			$('#nav').append('<li class = "active" id = "europeTab"><a href="#europe" role="tab" data-toggle="tab">Europe</a></li>');
			$('#nav').append('<li id = "nAmericaTab"><a href="#nAmerica" role="tab" data-toggle="tab">North America</a></li>');
			$('#nav').append('<li id = "sAmericaTab"><a href="#sAmerica" role="tab" data-toggle="tab">South America</a></li>');
			$('#nav').append('<li id = "asiaTab"><a href="#asia" role="tab" data-toggle="tab">Asia</a></li>');
			$('#europe').attr('class','tab-pane active');
		}
		else if(data[0]== "invalid"){
			alert("User name did not match password. Please try again")
		}
};

var formToJSON = function(){
	return JSON.stringify({
		"id": $('#NewUserId').val,
		"Password":$('#NewPassword').val()
	});
};

var showUsers = function(){
	console.log('showUsers');
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/DonalWebApp/rest/Users/viewAll',
		dataType: "json",
		success: renderShowUsers
	});
};

var renderShowUsers = function(data){
	$('#viewUsersDiv').empty();
	$('#viewUsersDiv').append('<div class="tab-pane" id="usersList"><table id ="usersTable><thead id "userHeader">'+
			'<tr><th>Users ID</th><th>User Type</th></tr></thead><tbody id = "tableBody"></tbody></table></div>');
	
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(list, function(index, user){
		$('#tableBody').append('<tr><td id = "userRow">'+user[0]+'</td><td id = "userRow"'+user[2]+'</td></tr>');
	});
};

var addUserToDataBase = function (){
	console.log('addUserToDataBase');
	var NewUserId = document.getElementById('NewUserId').value;
	var NewPassword = document.getElementById('NewPassword').value;
	var NewUserType = document.getElementById('NewUserType').value;
	
	if(NewUserId != '' && document.getElementById('NewPassword').value != ''){
		$.ajax({
			type: 'POST',
			contentType: 'application/json',
			url: "http://localhost:8080/DonalWebApp/rest/Users/check/"+ NewUserId+'&'+NewPassword+'&'+NewUserType,
			dataType: "json",
			success: function(data, textStatus, jqXHR){
				
				alert('User created successfully');
				document.getElementById('NewUserId').value= '';
				document.getElementById('NewPassword').value= '';
				
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('addNewUser errro: UserId :' + NewUserId+ ' already is user');
				document.getElementById('NewUserId').value= '';
			}
		});
	}
	else{
		alert("You must enter a value in for User Id AND password");
	}
};