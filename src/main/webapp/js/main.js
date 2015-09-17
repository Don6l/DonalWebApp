/**
 * Like a bauuuussssssss!!
 */

$(document).ready(function(){
	console.log('Begin');
	
	hideEverything();
	getMovie();
	
});


var hideEverything = function(){
	console.log('hideEverything()');
	$('header .logOut').hide();
};



var getMovie = function(){
	
	console.log('getMovie()');
	var url = 'http://api.themoviedb.org/3/',
    mode = 'search/movie?query=',
    input = 'Golden Eye',
    movieName = 'Golden Eye',
	key = '8d6309d947f4b14fb1993727a6e612a4';
	
	
	$.ajax({
		type: 'GET',
		url : url + mode + input + key,		
	     async: false,
         jsonpCallback: 'testing',
         contentType: 'application/json',
         dataType: 'jsonp',
         success: function(json) {
             console.dir(json);
         },
         error: function(e) {
             console.log(e.message);
         }
	});
};

function movieInfo(data){
	console.log('movieInfo()');
	$(document.body).append("<h2>"+data.Title+"</h2>");
}
