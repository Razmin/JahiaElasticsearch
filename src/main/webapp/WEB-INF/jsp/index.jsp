<%@include file="global.jsp"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="<c:url value="/css/jquery-ui.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/buttons.css"/>" rel="stylesheet">
</head>
<body>
<h2>Jahia Customers</h2>
<form id="mainForm" method="post">
    <span class="error">
    <br/><br/>${message}<br/><br/>
    </span>
<table id="customersTable" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Customer id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:choose>
        <c:when test="${not empty customers}">
            <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.fName}</td>
                    <td>${customer.lName}</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </c:forEach>
            </tbody>
        </c:when>
        <c:otherwise>
            <tbody>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
            </tbody>
        </c:otherwise>
    </c:choose>
</table>
    <button type="button" class="btn btn-success" onclick="addCustomer()">Add new customer</button>
    <input type="hidden" name="id" id="id">
    <div id="dialog" title="Warning" style="display: none">
        <p>
        <table width="450px">
            <tr>
                <td colspan="2" height="50px">
                    <b>Are you sure you want to delete?</b>
                </td>
            </tr>
            <tr style="height: 50px">
                <td style="text-align: center; margin: 20px">
                    <button class="btn btn-primary" type="button" onclick="$('#dialog').dialog('close');">Cancel</button>
                </td>
                <td style="text-align: center; margin: 20px">
                    <button type="button" class="btn btn-success" onclick="deleteCustomer()">Yes</button>
                </td>
            </tr>
        </table>
        </p>
    </div>

</form>

</body>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    var form;
    var cId;
    $("#dialog").dialog({
        autoOpen: false, modal: true, show: "blind", hide: "blind", width: "470"
    });
    var table;
    $(document).ready(function() {
        form = document.getElementById("mainForm");
        table = $('#customersTable').DataTable({
            "columns": [
                { "orderable": false,
                    "visible": true
                },
                { "width": "100" },
                { "width": "200" },
                { "className": "edit-control",
                    "orderable": false,
                    "data":      null,
                    "defaultContent": '',
                    "width": "10"
                },
                { "className": "delete-control",
                    "orderable": false,
                    "data":      null,
                    "defaultContent": '',
                    "width": "10"
                }
            ]
        });
    } );
    $('#customersTable').on( 'click', 'td.edit-control', function (e) {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        cId = row.data()[0];
        document.getElementById("id").value = cId;
        form.action="editCustomer.do";
        form.submit();
    });

    $('#customersTable').on( 'click', 'td.delete-control', function (e) {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        cId = row.data()[0];
        document.getElementById("id").value = cId;
        $("#dialog").dialog("open");
    });
    function addCustomer() {
        form.action = "addingCustomer.do";
        form.submit();
    }
    function deleteCustomer() {
        form.action = "deleteCustomer.do";
        form.submit();
    }
</script>
</html>
