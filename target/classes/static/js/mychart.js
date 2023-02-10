const ctx = document.getElementById('myPieChart');

var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr); 
var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for(var i=0;i<arrayLength; i++)
{
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}
 
 new Chart(ctx, {
    type: 'pie',
    data: {
      labels: labelData,
      datasets: [{  
		label: 'Projects Status',      
        data: numericData,
        borderWidth: 1
      }]
    },
    options: {	
      scales: {
        y: {
          beginAtZero: true
        }
      } 
    }
  });
    
 function decodeHtml(html) {
	 var txt = document.createElement("textarea");
	 txt.innerHTML = html;
	 return txt.value;
 }