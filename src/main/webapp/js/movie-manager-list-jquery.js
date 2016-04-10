//var urlMoviesAssisted =  "http://localhost:8080/Movie-Manager/rest-api/movie/assisted";
//var urlMoviesNotAssisted =  "http://localhost:8080/Movie-Manager/rest-api/movie/notAssisted";
//var urlFlagAsAssistedMovies =  "http://localhost:8080/Movie-Manager/rest-api/movie/flagAsAssisted";
//var urlLoadById =  "http://localhost:8080/Movie-Manager/rest-api/movie/loadById";

var urlMoviesAssisted =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/assisted";
var urlMoviesNotAssisted =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/notAssisted";
var urlFlagAsAssistedMovies =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/flagAsAssisted";
var urlLoadById =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/loadById";

$(document).ready(function() {
	loadMoviesNotAssisted();
	loadMoviesAssisted();
});

function flagAsAssistedMovie() {
	var movieId = $(this).attr("id");
	var score = prompt("Informe uma nota entre 0 e 10.", "");
	var note = prompt("Descrição.", "");

	$.ajax({
		url: urlFlagAsAssistedMovies,
    	type: 'DELETE',
    	data: JSON.stringify({id:movieId,score:score,note:note}),
    	contentType: 'application/json; charset=utf-8',
    	dataType: 'json'
	}).done(function() {
			$row = $("#" + movieId + "-row-not-assisted")
			$row.hide('slow', function() {
				$row.remove();
				loadMovieById(movieId)
			});
		});
}

function loadMoviesNotAssisted() {

	$.getJSON(urlMoviesNotAssisted)
		.done(function(data) {
			addAllMoviesNotAssisted(data);
		});
}

function addAllMoviesNotAssisted(allMovies) {

	for(var i = 0; i < allMovies.length; i++) {
		
		addMovieNotAssisted(allMovies[i]);

	}
}

function addMovieNotAssisted(movie) {

	var movieRow = '<tr id="' + movie.id + '-row-not-assisted">'
				 +  '<td align="center">' + movie.id + '</td>'
				 +	'<td align="center">' + movie.name + '</td>'
				 +	'<td align="center">' + movie.country + '</td>'
				 +	'<td align="center">' + movie.year + '</td>'
				 +	'<td align="center">' + movie.genre + '</td>'
				 +	'<td align="center" id="' + movie.id + '">X</td>'
				+'</tr>';

	$("#movie-list-not-assisted").append(movieRow);
	$("#" + movie.id).click(flagAsAssistedMovie);
}


function loadMoviesAssisted() {
	$.getJSON(urlMoviesAssisted)
	.done(function(data) {
		addAllMoviesAssisted(data);
	});
}

function addAllMoviesAssisted(allMovies) {

	for(var i = 0; i < allMovies.length; i++) {
		
		addMovieAssisted(allMovies[i]);

	}
}

function addMovieAssisted(movie) {

	var movieRow = '<tr id="' + movie.id + '-row-assisted" class="row-assisted">'
				 +  '<td align="center">' + movie.id + '</td>'
				 +	'<td align="center">' + movie.name + '</td>'
				 +	'<td align="center">' + movie.country + '</td>'
				 +	'<td align="center">' + movie.year + '</td>'
				 +	'<td align="center">' + movie.genre + '</td>'
				 +	'<td align="center">' + movie.score + '</td>'
				 +	'<td align="center">' + movie.note + '</td>'
				+'</tr>';

	$("#movie-list-assisted").append(movieRow);
}

function loadMovieById(id) {
	$.ajax({
		url: urlLoadById + "?id=" + id,
    	type: 'GET',
	}).done(function(data) {
			addMovieAssisted(data)
		});
}


