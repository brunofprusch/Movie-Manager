//var urlCreateMovie = "http://localhost:8080/Movie-Manager/rest-api/movie/create";
var urlCreateMovie =  "http://movie-manager.elasticbeanstalk.com/rest-api/movie/create";

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
			$("#name").val('');
			$("#country").val('');
			$("#year").val('');
			$("#genre").val('');
			$("#score").val('');
			$("#note").val('');
		   });
}

function getParametersValue() {
	var name = $("#name").val();
	var country = $("#country").val();
	var year = $("#year").val();
	var genre = $("#genre").val();
	var assisted = $("#assisted").val();
	var score = $("#score").val();
	var note = $("#note").val();

	return JSON.stringify({name:name,country:country,year:year,genre:genre,assisted:assisted,score:score,note:note});
}