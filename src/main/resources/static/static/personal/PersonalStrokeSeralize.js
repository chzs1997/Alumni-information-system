$(function () {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));
    var stroke=[];
    $.ajax({
        url: BASE_PATH + "/user/findStrokeByUserId",
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
        {field: 'schId', title: '行程编号', sortable: true},
        {field: 'userId', title: '用户编号', sortable: true},
        {field: 'schTime', title: '行程时间', sortable: true},
        {field: 'schPlace', title: '行程地点', sortable: true},
        {field: 'schState', title: '行程状态', sortable: true}
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