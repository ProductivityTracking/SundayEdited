<!DOCTYPE html>
<html>
<head>
  <title>Overall Project Analytics</title>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-moment@1.0.0/dist/chartjs-adapter-moment.min.js"></script>
</head>
<body onload="getAnalysis(); getDoughnutData();">
  <h1>Overall Projects Analytics</h1>
  <div class="chart-container">
    <div class="chart">
      <h3>Tasks Overview - (past 30 days progress)</h3>
      <div id="tasksCompletedChartContainer">
        <canvas id="tasksCompletedChart"></canvas>
      </div>
    </div>
  </div>
  <div class="chart-container">
    <div class="chart">
      <h3>Project Metrics - Doughnut Chart</h3>
      <div id="doughnutChartContainer" class="smaller-chart">
        <canvas id="doughnutChart"></canvas>
      </div>
    </div>
  </div>


  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script>
    var momentAdapter = chartjs.adapters.moment;

    function getAnalysis(){
      $(document).ready(function() {
        $.ajax({
          url: 'getDashboardAnalytics',
          method: 'POST',
          dataType: 'json',
          success: function(response) {
            console.log(response); // Check the response data in the console

            var completedTasksByDate = response; // Assign the response directly

            var completedDates = [];
            var tasksCompletedData = [];

         // Parse the array and extract dates and tasks completed
            for (var i = 0; i < completedTasksByDate.length; i++) {
              var item = completedTasksByDate[i];
              console.log(item); // Check each item in the array

              var parts = item.split(" Tasks: ");
              var dateString = parts[0];
              var tasksCompleted = parseInt(parts[1]);

              var dateParts = dateString.split("-");
              var date = new Date(
                parseInt(dateParts[2]), // Year
                parseInt(dateParts[1]) - 1, // Month (zero-based)
                parseInt(dateParts[0]) // Day
              );

              completedDates.push(date);
              tasksCompletedData.push(tasksCompleted);
            }

            if (completedDates.length > 0) {
              var tasksCompletedCtx = document.getElementById('tasksCompletedChart').getContext('2d');
              var tasksCompletedChart = new Chart(tasksCompletedCtx, {
                type: 'bar',
                data: {
                  datasets: [
                    {
                      label: 'Tasks Completed',
                      data: tasksCompletedData,
                      backgroundColor: 'rgba(54, 162, 235, 0.5)',
                      borderColor: 'rgba(54, 162, 235, 1)',
                      borderWidth: 1.5
                    },
                    {
                      label: 'Tasks Completed',
                      data: tasksCompletedData,
                      type: 'line',
                      fill: false,
                      borderColor: 'rgba(255, 99, 132, 1)',
                      borderWidth: 1.5,
                      pointRadius: 0
                    }
                  ],
                  labels: completedDates
                },
                options: {
                  scales: {
                    x: {
                      type: 'time',
                      time: {
                        unit: 'day',
                        parser: 'DD-MM-YY',
                        tooltipFormat: 'DD-MM-YY',
                        adapter: momentAdapter // Use the momentAdapter for handling time-based data
                      }
                    },
                    y: {
                      stacked: true
                    }
                  }
                }
              });
            } else {
              console.log('No data available for tasks completed.');
            }
          },
          error: function() {
            console.log('Error retrieving data from the server.');
          }
        });
      });

    }
    
    

    function getDoughnutData() {
      $(document).ready(function() {
        $.ajax({
          url: 'getDashboardAllAnalytics', // Replace with the actual URL to retrieve doughnut data
          method: 'POST',
          dataType: 'json',
          success: function(response) {
            console.log(response); // Check the response data in the console

            var doughnutData = response; // Assign the response directly

            var doughnutCtx = document.getElementById('doughnutChart').getContext('2d');
            var doughnutChart = new Chart(doughnutCtx, {
              type: 'doughnut',
              data: {
                labels: Object.keys(doughnutData),
                datasets: [
                  {
                    label: 'Project Metrics',
                    data: Object.values(doughnutData),
                    backgroundColor: [
                      'rgba(255, 99, 132, 0.5)',
                      'rgba(54, 162, 235, 0.5)',
                      'rgba(255, 206, 86, 0.5)',
                      'rgba(75, 192, 192, 0.5)'
                    ],
                    borderColor: [
                      'rgba(255, 99, 132, 1)',
                      'rgba(54, 162, 235, 1)',
                      'rgba(255, 206, 86, 1)',
                      'rgba(75, 192, 192, 1)'
                    ],
                    borderWidth: 1.5
                  }
                ]
              },
              options: {}
            });
          },
          error: function() {
            console.log('Error retrieving doughnut data from the server.');
          }
        });
      });
    }
  </script>
  
  
  <style>
    .chart-container {
      display: flex;
      margin-bottom: 20px;
    }

    .chart {
      flex: 1;
      padding: 20px;
    }

    .chart canvas {
      width: 100%;
      height: 100%;
    }

    #tasksCompletedChartContainer {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 90%; /* Adjust the width as needed */
      height: 450px; /* Adjust the height as needed */
    }

    #doughnutChartContainer.canvas {
      max-width: 400px; /* Adjust the width as needed */
      max-height: 400px; /* Adjust the height as needed */
    }

    .smaller-chart {
      width: 50%; /* Adjust the width as needed */
      margin-left:300px;
    }
  </style>
</body>
</html>