//获取basePath，测试的话就能用就完了
var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
var  date1,date1Num
    ,date2,date2Num
    ,date3,date3Num
    ,date4,date4Num
    ,date5,date5Num
    ,date6,date6Num
    ,date7,date7Num

var donationMonth1,donationMonth1Amount
    ,donationMonth2,donationMonth2Amount
    ,donationMonth3,donationMonth3Amount
    ,donationMonth4,donationMonth4Amount
    ,donationMonth5,donationMonth5Amount

var newsMonth1,newsMonth1Amount
    ,newsMonth2,newsMonth2Amount
    ,newsMonth3,newsMonth3Amount
    ,newsMonth4,newsMonth4Amount
    ,newsMonth5,newsMonth5Amount
jQuery(document).ready(function ($) {
    $.ajax({
        url: BASE_PATH + "/manager/findUserAmountLastWeek",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var j = 1;
            for(var i in data){
                switch (j) {
                    case 1:
                        date1 = i;
                        date1Num = data[i];
                        break;
                    case 2:
                        date2 = i;
                        date2Num = data[i];
                        break;
                    case 3:
                        date3 = i;
                        date3Num = data[i];
                        break;
                    case 4:
                        date4 = i;
                        date4Num = data[i];
                        break;
                    case 5:
                        date5 = i;
                        date5Num = data[i];
                        break;
                    case 6:
                        date6 = i;
                        date6Num = data[i];
                        break;
                    case 7:
                        date7 = i;
                        date7Num = data[i];
                        break;
                }
                j++;
            }
        },
        error: function f() {
        }
    });
    //近一周用户注册量
    new Chart(document.getElementById("myChart1").getContext('2d'), {
        type: 'bar',
        data: {
            labels: [date1, date2, date3, date4,date5, date6, date7],
            datasets: [{
                label: '',
                type: 'bar',
                data: [date1Num, date2Num, date3Num, date4Num, date5Num, date6Num, date7Num],
                backgroundColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(255, 0,   0,   1)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(255, 0,   0,   1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            legend: { display: false },
            title: {
                display: true,
                text: ''
            }
        }
    });

    //近5个月捐赠金额
    new Chart(document.getElementById("myChart2"), {
        type: 'line',
        data: {
            labels: [1850,1900,1950,1999,2050],
            datasets: [{
                data: [111,133,221,783,2478],
                label: '',
                borderColor: "#3e95cd",
                fill: false
            }, {
                data: [809,947,1402,3700,5267],
                label: '',
                borderColor: "#8e5ea2",
                fill: false
            }, {
                data: [276,408,547,675,734],
                label: '',
                borderColor: "#3cba9f",
                fill: false
            }, {
                data: [38,74,167,508,784],
                label: '',
                borderColor: "#e8c3b9",
                fill: false
            }, {
                data: [26,82,172,312,433],
                label: '',
                borderColor: "#c45850",
                fill: false
            }
            ]
        },
        options: {
            legend: { display: false },
            title: {
                display: true,
                text: ''
            }
        }
    });


});






// //Mychart3
// new Chart(document.getElementById("myChart3"), {
//     type: 'bubble',
//     data: {
//       datasets: [
//         {
//           label: ["China"],
//           backgroundColor: "rgba(255,221,50,0.2)",
//           borderColor: "rgba(255,221,50,1)",
//           data: [{
//             x: 69017,
//             y: 5.245,
//             r: 15
//           }]
//         }, {
//           label: ["Denmark"],
//           backgroundColor: "rgba(60,186,159,0.2)",
//           borderColor: "rgba(60,186,159,1)",
//           data: [{
//             x: 258702,
//             y: 7.526,
//             r: 10
//           }]
//         }, {
//           label: ["Germany"],
//           backgroundColor: "rgba(0,0,0,0.2)",
//           borderColor: "#000",
//           data: [{
//             x: 759083,
//             y: 6.994,
//             r: 7
//           }]
//         }, {
//           label: ["Japan"],
//           backgroundColor: "rgba(193,46,12,0.2)",
//           borderColor: "rgba(193,46,12,1)",
//           data: [{
//             x: 891877,
//             y: 5.921,
//             r: 20
//           }]
//         }
//       ]
//     },
//     options: {
//       legend: { display: false },
//       title: {
//         display: true,
//         text: ''
//        }
//     }
// });

//Mychart3
new Chart(document.getElementById("myChart3"), {
    type: 'line',
    data: {
        labels: [1850,1900,1950,1999,2050],
        datasets: [{
            data: [111,133,221,783,2478],
            label: '',
            borderColor: "#3e95cd",
            fill: false
        }, {
            data: [809,947,1402,3700,5267],
            label: '',
            borderColor: "#8e5ea2",
            fill: false
        }, {
            data: [276,408,547,675,734],
            label: '',
            borderColor: "#3cba9f",
            fill: false
        }, {
            data: [38,74,167,508,784],
            label: '',
            borderColor: "#e8c3b9",
            fill: false
        }, {
            data: [26,82,172,312,433],
            label: '',
            borderColor: "#c45850",
            fill: false
        }
        ]
    },
    options: {
        legend: { display: false },
        title: {
            display: true,
            text: ''
        }
    }
});

//Mychart4
new Chart(document.getElementById("myChart4").getContext('2d'), {
  type: 'doughnut',
  data: {
    labels: ["M", "T", "W", "T", "F", "S", "S"],
    datasets: [{
      backgroundColor: [
        "#2ecc71",
        "#3498db",
        "#95a5a6",
        "#9b59b6",
        "#f1c40f",
        "#e74c3c",
        "#34495e"
      ],
      data: [12, 19, 3, 17, 28, 24, 7]
    }]
  },
  options: {
      legend: { display: false },
      title: {
        display: true,
        text: ''
       } 
    }
});

// Polar chart
new Chart(document.getElementById("polar-chart").getContext('2d'), {
  type: 'polarArea',
  data: {
    labels: ["M", "T", "W", "T", "F", "S", "S"],
    datasets: [{
      backgroundColor: [
        "#2ecc71",
        "#3498db",
        "#95a5a6",
        "#9b59b6",
        "#f1c40f",
        "#e74c3c",
        "#34495e"
      ],
      data: [12, 19, 3, 17, 28, 24, 7]
    }]
  },
  options: {
    legend: { display: false },
    title: {
      display: true,
      text: ''
     } 
  }
});