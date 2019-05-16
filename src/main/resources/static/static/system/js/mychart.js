//获取basePath，测试的话就能用就完了
var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
var  date1,date1Num = 0
    ,date2,date2Num = 0
    ,date3,date3Num = 0
    ,date4,date4Num = 0
    ,date5,date5Num = 0
    ,date6,date6Num = 0
    ,date7,date7Num = 0

var donationMonth1,donationMonth1AmountReal    ,donationMonth1AmountAnonymous
    ,donationMonth2,donationMonth2AmountReal    ,donationMonth2AmountAnonymous
    ,donationMonth3,donationMonth3AmountReal    ,donationMonth3AmountAnonymous
    ,donationMonth4,donationMonth4AmountReal    ,donationMonth4AmountAnonymous
    ,donationMonth5,donationMonth5AmountReal    ,donationMonth5AmountAnonymous

var viewCounts1,viewCounts2,viewCounts3,viewCounts4,viewCounts5
var commentCounts1,commentCounts2,commentCounts3,commentCounts4,commentCounts5

var newsMonth1,newsMonth1Amount
    ,newsMonth2,newsMonth2Amount
    ,newsMonth3,newsMonth3Amount
    ,newsMonth4,newsMonth4Amount
    ,newsMonth5,newsMonth5Amount
jQuery(document).ready(function ($) {
    $.ajax({
        url: BASE_PATH + "/administrator/findUserAmountLastWeek",
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
    $.ajax({
        url: BASE_PATH + "/administrator/findAmountLast5MonthsReal",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var j = 1;
            for(var i in data){
                switch (j) {
                    case 1:
                        donationMonth1 = i;
                        donationMonth1AmountReal = data[i];
                        break;
                    case 2:
                        donationMonth2 = i;
                        donationMonth2AmountReal = data[i];
                        break;
                    case 3:
                        donationMonth3 = i;
                        donationMonth3AmountReal = data[i];
                        break;
                    case 4:
                        donationMonth4 = i;
                        donationMonth4AmountReal = data[i];
                        break;
                    case 5:
                        donationMonth5 = i;
                        donationMonth5AmountReal = data[i];
                        break;
                }
                j++;
            }
            $.ajax({
                url: BASE_PATH + "/administrator/findAmountLast5MonthsAnonymous",
                type: "post",
                dateType: "json",
                data: {},
                async: false,
                success: function f(data) {
                    var j = 1;
                    for(var i in data){
                        switch (j) {
                            case 1:
                                donationMonth1AmountAnonymous = data[i];
                                break;
                            case 2:
                                donationMonth2AmountAnonymous = data[i];
                                break;
                            case 3:
                                donationMonth3AmountAnonymous = data[i];
                                break;
                            case 4:
                                donationMonth4AmountAnonymous = data[i];
                                break;
                            case 5:
                                donationMonth5AmountAnonymous = data[i];
                                break;
                        }
                        j++;
                    }

                },
                error: function f() {
                }
            });
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
            labels: [donationMonth5,donationMonth4,donationMonth3,donationMonth2,donationMonth1],
            datasets: [{
                data: [donationMonth5AmountReal,donationMonth4AmountReal,donationMonth3AmountReal,donationMonth2AmountReal,donationMonth1AmountReal],
                label: '',
                borderColor: "#3e95cd",
                fill: false
            }, {
                data: [donationMonth5AmountAnonymous,donationMonth4AmountAnonymous,donationMonth3AmountAnonymous,donationMonth2AmountAnonymous,donationMonth5AmountAnonymous],
                label: '',
                borderColor: "#8e5ea2",
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

    //近1个月新闻浏览量
    $.ajax({
        url: BASE_PATH + "/news/findNewsViewCountsLastMonth",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var j = 1;
            for(var i in data){
                switch (j) {
                    case 1:
                        viewCounts1 = data[i];
                        break;
                    case 2:
                        viewCounts2 = data[i];
                        break;
                    case 3:
                        viewCounts3 = data[i];
                        break;
                    case 4:
                        viewCounts4 = data[i];
                        break;
                    case 5:
                        viewCounts5 = data[i];
                        break;
                }
                j++;
            }
        },
        error: function f() {
        }
    });

    //近1个月评论统计量
    $.ajax({
        url: BASE_PATH + "/news/findNewsCommentCountsLastMonth",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var j = 1;
            for(var i in data){
                switch (j) {
                    case 1:
                        commentCounts1 = data[i];
                        break;
                    case 2:
                        commentCounts2 = data[i];
                        break;
                    case 3:
                        commentCounts3 = data[i];
                        break;
                    case 4:
                        commentCounts4 = data[i];
                        break;
                    case 5:
                        commentCounts5 = data[i];
                        break;
                }
                j++;
            }
        },
        error: function f() {
        }
    });

    // Polar chart
    new Chart(document.getElementById("polar-chart").getContext('2d'), {
        type: 'polarArea',
        data: {
            labels: ["学院新闻", "校友新闻", "通知公告", "校友卡", "校友返校"],
            datasets: [{
                backgroundColor: [
                    "#31708f",
                    "#dc3545",
                    "#ffc107",
                    "#1B242F",
                    "#5cb85c",
                ],
                data: [viewCounts1, viewCounts2, viewCounts3, viewCounts4, viewCounts5]
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

// Polar chart2
    new Chart(document.getElementById("polar-chart2").getContext('2d'), {
        type: 'polarArea',
        data: {
            labels: ["学院新闻", "校友新闻", "通知公告", "校友卡", "校友返校"],
            datasets: [{
                backgroundColor: [
                    "#31708f",
                    "#dc3545",
                    "#ffc107",
                    "#1B242F",
                    "#5cb85c",
                ],
                data: [commentCounts1, commentCounts2, commentCounts3, commentCounts4, commentCounts5]
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

var count1 ,count2,count3,count4,count5=0;
var percentage1,percentage2,percentage3,percentage4,percentage5
//校友信息完善度汇总
$.ajax({
    url: BASE_PATH + "/administrator/findIntegrityCount",
    type: "post",
    dateType: "json",
    data: {},
    async: false,
    success: function f(data) {
        var j = 1;
        for(var i in data){
            switch (j) {
                case 1:
                    percentage1 = i;
                    count1 = data[i];
                    break;
                case 2:
                    percentage2 = i;
                    count2 = data[i];
                    break;
                case 3:
                    percentage3 = i;
                    count3 = data[i];
                    break;
                case 4:
                    percentage4 = i;
                    count4 = data[i];
                    break;
                case 5:
                    percentage5 = i;
                    count5 = data[i];
                    break;
            }
            j++;
        }
    },
    error: function f() {
    }
});
//校友信息完善度汇总
new Chart(document.getElementById("myChart4").getContext('2d'), {
  type: 'doughnut',
  data: {
    labels: [percentage1, percentage2, percentage3, percentage4, percentage5],
    datasets: [{
      backgroundColor: [
        "#95a5a6",
        "#2ecc71",
        "#3498db",
        "#9b59b6",
        "#f1c40f",
      ],
      data: [count1, count2, count3, count4, count5]
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

var goodClass1 ,goodClass2,goodClass3,goodClass4,goodClass5,goodClass6,goodClass7
var goodPercentage1,goodPercentage2,goodPercentage3,goodPercentage4,goodPercentage5,goodPercentage6,goodPercentage7
//校友信息完善度较好排名
$.ajax({
    url: BASE_PATH + "/administrator/findIntegrityBetter",
    type: "post",
    dateType: "json",
    data: {},
    async: false,
    success: function f(data) {
        var j = 1;
        for(var i in data){
            switch (j) {
                case 1:
                    goodClass1 = i;
                    goodPercentage1 = data[i];
                    break;
                case 2:
                    goodClass2 = i;
                    goodPercentage2 = data[i];
                    break;
                case 3:
                    goodClass3 = i;
                    goodPercentage3 = data[i];
                    break;
                case 4:
                    goodClass4 = i;
                    goodPercentage4 = data[i];
                    break;
                case 5:
                    goodClass5 = i;
                    goodPercentage5 = data[i];
                    break;
                case 6:
                    goodClass6 = i;
                    goodPercentage6 = data[i];
                    break;
                case 7:
                    goodClass7 = i;
                    goodPercentage7 = data[i];
                    break;
            }
            j++;
        }
    },
    error: function f() {
    }
});
//校友信息完善度较好排名
new Chart(document.getElementById("myChart5").getContext('2d'), {
    type: 'bar',
    data: {
        labels: [goodClass1, goodClass2, goodClass3,goodClass4,goodClass5,goodClass6,goodClass7],
        datasets: [{
            label: '',
            type: 'bar',
            data: [goodPercentage1, goodPercentage2, goodPercentage3,goodPercentage4,goodPercentage5,goodPercentage6,goodPercentage7],
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

var badClass1 ,badClass2,badClass3,badClass4,badClass5,badClass6,badClass7
var badPercentage1,badPercentage2,badPercentage3,badPercentage4,badPercentage5,badPercentage6,badPercentage7
//校友信息完善度较差排名
$.ajax({
    url: BASE_PATH + "/administrator/findIntegrityWorse",
    type: "post",
    dateType: "json",
    data: {},
    async: false,
    success: function f(data) {
        var j = 1;
        for(var i in data){
            switch (j) {
                case 1:
                    badClass1 = i;
                    badPercentage1 = data[i];
                    break;
                case 2:
                    badClass2 = i;
                    badPercentage2 = data[i];
                    break;
                case 3:
                    badClass3 = i;
                    badPercentage3 = data[i];
                    break;
                case 4:
                    badClass4 = i;
                    badPercentage4 = data[i];
                    break;
                case 5:
                    badClass5 = i;
                    badPercentage5 = data[i];
                    break;
                case 6:
                    badClass6 = i;
                    badPercentage6 = data[i];
                    break;
                case 7:
                    badClass7 = i;
                    badPercentage7 = data[i];
                    break;
            }
            j++;
        }
    },
    error: function f() {
    }
});
//校友信息完善度较差排名
new Chart(document.getElementById("myChart6").getContext('2d'), {
    type: 'bar',
    data: {
        labels: [badClass1, badClass2, badClass3, badClass4,badClass5, badClass6, badClass7],
        datasets: [{
            label: '',
            type: 'bar',
            data: [badPercentage1, badPercentage2, badPercentage3, badPercentage4, badPercentage5, badPercentage6, badPercentage7],
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

