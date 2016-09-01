<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="cn">
<head>
    <!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/ecmascript" src="js/commons.js"></script>
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="plugins/jqgrid/i18n/grid.locale-cn.js"></script>
    <!-- This is the Javascript file of jqGrid -->
    <script type="text/ecmascript" src="plugins/jqgrid/jquery.jqGrid.js"></script>
    <!-- A link to a Boostrap  and jqGrid Bootstrap CSS siles-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="plugins/jqgrid/css/ui.jqgrid-bootstrap.css"/>
    <script>
        $.jgrid.defaults.width = 780;
        $.jgrid.defaults.responsive = true;
        $.jgrid.defaults.styleUI = 'Bootstrap';
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <meta charset="utf-8"/>
    <title>jqGrid Loading Data - Edit Dialogs with Custom Layout</title>
</head>
<body>
<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript">
    var formParam = JSON.stringify($("#showUsers").serializeObject());
    $(document).ready(function () {
        console.log("------------------------")
        $("#showUsers").jqGrid({
            url: "/jersey/user/list2/all",
            editurl: 'index.jsp',
            datatype: "json",
            colNames: ["userId", "userName", "userAge"],
            colModel: [
                {
                    label: 'userId*',
                    name: 'userId',
                    width: 75
                },
                {
                    label: 'userName*',
                    name: 'userName',
                    width: 140,
                    editable: true, // must set editable to true if you want to make the field editable
                    // set options related to the layout of the Edit and Add Forms
                    formoptions: {
                        colpos: 1, // the position of the column
                        rowpos: 1, // the position of the row
                        label: "Company Name: " // the label to show for each input control
                        //elmsuffix: " * " // the suffix to show after that
                    }
                },
                {
                    label: 'userAge*',
                    name: 'userAge',
                    width: 100,
                    editable: true,
                    formoptions: {
                        colpos: 1,
                        rowpos: 2
                    }
                }
            ],
            loadOnce: true,
            viewrecords: true,
            width: 780,
            height: 200,
            rowNum: 10,
            pager: "#jqGridPager"
        });


        $('#showUsers').jqGrid('navGrid', '#gridPager',
                // the buttons to appear on the toolbar of the grid
                {
                    edit: true,
                    add: true,
                    del: true,
                    search: false,
                    refresh: false,
                    view: false,
                    position: "left",
                    cloneToTop: false
                },
                // options for the Edit Dialog
                {
                    editCaption: "The Edit Dialog",
                    recreateForm: true,
                    checkOnUpdate: true,
                    checkOnSubmit: true,
                    closeAfterEdit: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Add Dialog
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Delete Dailog
                {
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                }
        );

    });


</script>

<div style="margin-left:20px;margin-top: 20px;">
    <table id="showUsers" class="table-striped"></table>
    <div id="gridPager"></div>
</div>
</body>
</html>