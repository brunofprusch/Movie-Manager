var urlCreateMovie =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/createMovie";

$(document).ready(function() {
	$("#save-movie").click(saveMovie);
});

function saveMovie() {

	var parameters = getParametersValue();

	$.ajax({
		url: urlCreateMovie,
    	type: 'POST',
    	data: parameters,
    	contentType: 'application/json; charset=utf-8',
    	dataType: 'json'
	}).done(function(data) {
			alert("Filme incluído com sucesso. Código: " + data.id);
		   });
}

function getParametersValue() {
	var name = $("#name").val();
	var country = $("#country").val();
	var year = $("#year").val();
	var genre = $("#genre").val();
	var score = $("#score").val();
	var note = $("#note").val();

	return JSON.stringify({name:name,country:country,year:year,genre:genre,score:score,note:note});
}