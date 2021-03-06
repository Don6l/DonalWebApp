/**
 * Author : Donal McClean
 */

$(document).ready(function(){
	console.log('Begin');
	hideEverything();
	$('#userTable').DataTable();
	$('#submitButton').button('reset');
	$('#Password').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			authenticateLogin();
		}
	});
	$('#UserId').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			authenticateLogin();
		}
	});
	$(document).on("click", '#submitButton', function(){addDataSetToDataBase();return false;});
	$(document).on("click", '#logInButton', function(){authenticateLogin();return false;});
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
	$('#admin').attr('class','tab-pane');
	$('#europe').attr('class','tab-pane');
	$('#nAmerica').attr('class','tab-pane');
	$('#sAmerica').attr('class','tab-pane');
	$('#asia').attr('class','tab-pane');
	$('.tab-content').hide();
	document.getElementById('NewUserId').value= '';
	document.getElementById('NewPassword').value= '';
};

var authenticateLogin = function(){
	console.log('athenticateLogin');
	$('#logInButton').button('loading');
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
		showUsers();
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
	sampleData();
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



var addDataSetToDataBase = function(){
	console.log("addDataSetToDataBase");
	//$('#submitButton').button('loading');
	if(document.getElementById('file').value != ''){
		var data = new FormData();
		var fileinfo = $('#file');
		var f2 = $('#file')[0];
		var f3 = $('#file')[0].files;
		$.each($('#file')[0].files, function(i, file){
			data.append('file', file);
		});
		$.ajax({
			type: 'POST',
			data: data,
			cache: false,
			contentType: false,
			processData: false,
			url: "./rest/upload/file",
			success: function(textStatus){
				$('#submitButton').button('reset');
				alert(textStatus);
			},
			failure: function(textStatus){
				$('#submitButton').button('reset');
				alert(textStatus);
			}
		});
	}
	else{
		$('#submitButton').button('reset');
		alert("You must choose a file to load.");
	}
	$('#submitButton').button('reset');
	$('#file').empty();
};



var showUsers = function(){
	console.log('showUsers');
	$.ajax({
		type: 'GET',
		url: './rest/Users/viewAll',
		dataType: "json",
		success: renderShowUsers
	});
};


var count;
var table = function(data){
	count = 0;
	for(var user in data){
			$(userTable).append('<tr><td><input type="text" name="'+user+'" id ="userName'+count+'" value="'+user+'"/></td>'+
					'<td><input type="text" name="userTypeName" id ="userTypeName'+count+'" disabled = true value="'+data[user]+'"/></td>'+
					'<td><button type="button" id="'+count+'" name = "'+user+'" class="btn btn-primary">Delete</button></td>'+
					'<td><button type="button" id="update'+count+'" name = "update'+user+'" class="btn btn-primary">Update</button></td></tr>');
			//console.log(document.getElementById('userName'+count).value);
			count++;
			
		}
};

var sampleData = function(){
	$(europeTable).empty();
	$(nAmericaTable).empty();
	$(sAmericaTable).empty();
	$(asiaTable).empty();
	$(europeTable).append('<thead><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></thead>'+
			'<tbody></tbody>'+
			'<tfoot><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></tfoot>');
	$(nAmericaTable).append('<thead><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></thead>'+
			'<tbody></tbody>'+
			'<tfoot><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></tfoot>');
	$(sAmericaTable).append('<thead><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></thead>'+
			'<tbody></tbody>'+
			'<tfoot><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></tfoot>');
	$(asiaTable).append('<thead><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></thead>'+
			'<tbody></tbody>'+
			'<tfoot><tr><th>Test ID</th><th>Server Country</th><th>Net Speed</th></tr></tfoot>');
	$(europeTable).append('<tr><td>111</td><td>England</td><td>3.0kbps</td></tr>');
	$(nAmericaTable).append('<tr><td>31</td><td>USA</td><td>16.0kbps</td></tr>');
	$(sAmericaTable).append('<tr><td>4011</td><td>Mexico</td><td>2.0kbps</td></tr>');
	$(asiaTable).append('<tr><td>21</td><td>Taiwan</td><td>23.0kbps</td></tr>');
};

var renderShowUsers = function(data){
	var originalID = [];
	console.log('renderShowUsers');
	$('#userTable').empty();
	$('#userTable').append('<thead><tr><th>User ID</th><th>User Type</th><th>Delete User</th><th>Edit User</th></tr></thead>'+
	'<tbody></tbody>'+
	'<tfoot><tr><th>User ID</th><th>User Type</th><th>Delete User</th><th>Edit User</th></tr></tfoot>');
	table(data);
var buttons = document.getElementsByTagName("button");
var buttonsCount = buttons.length;
for (var i = 0; i <= buttonsCount; i += 1) {
	
//	var abc = document.getElementById('userName'+i).value;
	if(i<count){
	originalID[i] = document.getElementById('userName'+i).value;
	console.log(originalID[i]);
}
	
	$(document).on("click", '#'+i, function(e){
		var id = this.name;
   		console.log(id);
    	$.ajax({
    		type: 'DELETE',
    		url: "./rest/Users/remove/"+id,
   			dataType: 'json',
   			success: function(data, textStatus, jqXHR){
 			alert(id+' user deleted');
    		showUsers();
    		},
    		failure : function(){
    		alert('Failed to delete user '+id);
    		}
    		});
	});

	$(document).on("click", '#update'+i, function(e){
		
		var id = this.id;
		var idNum = id.substring(6,id.length);
		var NewUserId = document.getElementById('userName'+idNum).value;
		var OldUser = originalID[idNum];
		var NewPassword = '123';
		var NewUserType = document.getElementById('userTypeName'+idNum).value;
		alert(NewUserId + " " + NewUserType+" "+ NewPassword+ " "+ OldUser);
		$.ajax({
				type: 'POST',
				contentType: 'application/json',
				url: "./rest/Users/check/"+ NewUserId+'&'+NewPassword+'&'+NewUserType,
				dataType: "json",
				success: function(data, textStatus, jqXHR){
					document.getElementById('#userTable')
					showUsers();
					alert('User updated created successfully from '+ OldUser +" to "+ NewUserId);
					document.getElementById('NewUserId').value= '';
					document.getElementById('NewPassword').value= '';
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('addNewUser errro: UserId :' + NewUserId+ ' already is user');
					document.getElementById('NewUserId').value= '';
				}
			});
		$.ajax({
    		type: 'DELETE',
    		url: "./rest/Users/remove/"+originalID[idNum],
   			dataType: 'json',
   			success: function(data, textStatus, jqXHR){
    		showUsers();
    		},
    		failure : function(){
    		alert('Failed to delete user '+originalID[idNum]);
    		}
		});
	});
}
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
			url: "./rest/Users/check/"+ NewUserId+'&'+NewPassword+'&'+NewUserType,
			dataType: "json",
			success: function(data, textStatus, jqXHR){
				document.getElementById('#userTable')
				showUsers();
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