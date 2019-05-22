$(function () {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));
    var stroke=[];
    $.ajax({
        url: BASE_PATH + "/user/findDonationByUserId",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            stroke = data;
        },
        error: function f() {
        }
    });
    var tableColumns = [
        {field: 'userId', title: '项目编号', sortable: true},
        {field: 'applicantName', title: '用户姓名', sortable: true},
        {field: 'donationAmount', title: '捐赠金额', sortable: true},
        {field: 'donationMajor', title: '捐赠专业', sortable: true},
        {field: 'applicantContact', title: '联系方式', sortable: true},
        {field: 'applicantMail', title: '邮箱', sortable: true},
        {field: 'applicantPurpose', title: '捐赠目的', sortable: true}
    ];
    var data1= stroke;
    $('#tableL01').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格

    $('#tableL01').bootstrapTable({//表格初始化
        columns: tableColumns,  //表头
        data:data1, //通过ajax返回的数据
        width:300,
        height:268,
        method: 'get',
        pageSize: 4,
        pageNumber: 1,
        pageList: [],
        cache: false,
        striped: true,
        pagination: true,
        sidePagination: 'client',
        search: false,
        showRefresh: false,
        showExport: false,
        showFooter: true,
        // exportTypes: ['csv', 'txt', 'xml'],
        clickToSelect: true,
    });

});
