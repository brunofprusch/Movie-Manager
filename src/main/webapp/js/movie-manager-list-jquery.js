var urlAllMovies =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/allMovies";

$(document).ready(function() {
	loadMovies();
});


function loadMovies() {

	$.getJSON(urlAllMovies)
		.done(function(data) {
			addAllMovies(data);
		});
}

function addAllMovies(allMovies) {

	for(var i = 0; i < allMovies.length; i++) {
		
		addMovie(allMovies[i]);

	}
}

function addMovie(movie) {

	var movie = '<tr>'
				 + '<td align="center">' + movie.id + '</td>'
				 +	'<td align="center">' + movie.name + '</td>'
				 +	'<td align="center">' + movie.country + '</td>'
				 +	'<td align="center">' + movie.year + '</td>'
				 +	'<td align="center">' + movie.genre + '</td>'
				 +	'<td align="center">' + movie.score + '</td>'
				 +	'<td align="center">' + movie.note + '</td>'
				+'</tr>';

	$("#movie-list").append(movie);
}