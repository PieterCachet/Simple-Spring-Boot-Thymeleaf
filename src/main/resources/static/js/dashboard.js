$(document).ready(function() {
	var refreshRate = 5000;// 5sec
	graph();
	setInterval(graph, refreshRate);

});


function graph() {
	var processed_json = new Array();
	$.getJSON('/getGraphData', function(data) {
		for (i = 0; i < data.length; i++) {
			processed_json.push([ data[i].key, data[i].value ]);
		}
		$('#msgpermingraph').highcharts({
			chart : {
				type : "pie"
			},
			title : {
				text : "New Creations"
			},
			series : [ {
				name : 'Amount',
				data : processed_json
			} ]
		});
		var dt = new Date();
		$("#graphupdatetime").html(dt);
	});
	return false;
}
