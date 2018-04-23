<%@include file="global.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jahia Customer management</title>
    <link href="<c:url value="/css/jquery-ui.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/buttons.css"/>" rel="stylesheet">
</head>
<body>
    <h2>Add a new customer</h2>
    <form id="mainForm" method="post">
        <input type="hidden" value="${customer.id}" name="id"/>
        <table>
            <tr>
                <td>
                    First name:
                </td>
                <td>
                    <input type="text" name="fName" value="${customer.fName}">
                </td>
            </tr>
            <tr>
                <td>
                    Last name:
                </td>
                <td>
                    <input type="text" name="lName" value="${customer.lName}">
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="btn btn-primary" onclick="cancel()">Cancel</button>
                </td>
                <td>
                    <button type="button" class="btn btn-success" onclick="addCustomer()">Submit</button>
                </td>
            </tr>
        </table>
    </form>
</body>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
    var form;
    $(document).ready(function() {
        form = document.getElementById("mainForm");
    });
    function addCustomer() {
        form.action="addCustomer.do";
        form.submit();
    }
    function cancel() {
        form.action="index.do";
        form.submit();
    }
</script>
</html>
