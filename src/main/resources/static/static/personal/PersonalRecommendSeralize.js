$(function () {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));
    var stroke=[];
    $.ajax({
        url: BASE_PATH + "/user/findRecommendUser",
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
        {field: 'userId', title: '序列号', sortable: true},
        {field: 'userName', title: '用户姓名', sortable: true},
        {field: 'userGrade', title: '用户年级', sortable: true},
        {field: 'userMajor', title: '用户专业', sortable: true},
        {field: 'userWorkProvince', title: '工作省份', sortable: true},
        {field: 'userWorkCity', title: '工作城市', sortable: true},
        {field: 'userPosition', title: '工作岗位', sortable: true},
        {field: 'userMail', title: '邮箱', sortable: true},
        {field: 'phone', title: '联系方式', sortable: true},
        {field: 'userWX', title: '微信号', sortable: true}
    ];
    var data1= stroke;
    $('#tableL01').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格

    $('#tableL01').bootstrapTable({//表格初始化
        columns: tableColumns,  //表头
        data:data1, //通过ajax返回的数据
        width:300,
        height:350,
        method: 'get',
        pageSize: 5,
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

$("#with_appr_status").on("change",function () {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));
    var strokeVal = $("option:selected",this).val();
    $.ajax({
        url: BASE_PATH + "/user/findStrokeByUserIdAndState",
        type: "post",
        dateType: "json",
        data: {"strokeVal":strokeVal},
        async: false,
        success: function f(data) {
            var tableColumns = [
                {field: 'schId', title: '行程编号', sortable: true},
                {field: 'userId', title: '用户编号', sortable: true},
                {field: 'schTime', title: '行程时间', sortable: true},
                {field: 'schPlace', title: '行程地点', sortable: true},
                {field: 'schState', title: '行程状态', sortable: true}
            ];
            $('#tableL01').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
            $('#tableL01').bootstrapTable({//表格初始化
                columns: tableColumns,  //表头
                data:data, //通过ajax返回的数据
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
        },
        error: function f() {
        }
    });
})