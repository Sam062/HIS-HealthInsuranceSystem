<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta>
            <title>View All Accounts</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

            <link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet"
                type="text/css">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
            <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
            <script>
                $(document).ready(function () {
                    $('#ACCNT_DTLS').DataTable({
                        "pagingType": "full_numbers"
                    });
                });
                function deleteConfirm(test, id) {
                    if (confirm("Are you sure, You want to " + test + "?")) {
                        let link = document.getElementById("delbtn").href;
                        link = "updateaccountstatus?status=delete&id=" + id;
                        window.open(link);
                        window.close();
                    }
                }
                function activateConfirm(test, id) {
                    if (confirm("Are you sure, You want to " + test + "?")) {
                        let link = document.getElementById("actbtn").href;
                        link = "updateaccountstatus?status=activate&id=" + id;
                        window.open(link);
                        window.close();
                    }
                }
            </script>
            <script>
                $(document).ready(function (event) {
                    $("#roleSelector").change(function () {
                        var roleSelectorValue = $("#roleSelector").val();
                        $('#ACCNT_DTLS').DataTable().clear().draw();
                        $('#ACCNT_DTLS').DataTable().destroy();
                        $.ajax({
                            contentType: "application/json",
                            type: "GET",
                            url: "getall?role=" + roleSelectorValue,
                            success: function (data) {
                                $.each(data, function (i, obj) {
                                    var t = $('#ACCNT_DTLS').DataTable();
                                    t.row.add([
                                        ++i,
                                        obj.fname + " " + obj.lname,
                                        obj.email,
                                        obj.mobileNo,
                                        obj.gender,
                                        obj.deleteStatus,
                                        `
                                        <a href="loadeditpage?id=`(obj.adminId)`">EDIT</a>|
                                        <a href="" id="del">DELETE</a>
                                        `,
                                        $(document).ready(function () {
                                            if(obj.deleteStatus=='ACTIVE'){
                                                
                                            }
                                            if (obj.deleteStatus == 'INACTIVE'){
                                                $('#del').css("display", "none");
                                            }
                                        })
                                    ]).draw();
                                });
                            }
                        });
                    });
                });
            </script>
        </head>

        <body>
            <%@ include file="header.jsp" %>
                <div class="container" align="center">
                    <table class="table table-striped" border="1" id="ACCNT_DTLS">
                        <thead>
                            <tr class="success">
                                <th colspan="8">
                                    <h3 align="center" class="text text-primary">Account
                                        Details</h3>
                                </th>
                            </tr>
                            <tr>
                                <td colspan="8" align="center">Select Role <select
                                        class="btn btn-default dropdown-toggle" id="roleSelector"
                                        data-toggle="dropdown">
                                        <option class="btn btn-default dropdown-toggle" value="ADMIN">ADMIN</option>
                                        <option class="btn btn-default dropdown-toggle" value="cw">CASE WORKER
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>S.No</td>
                                <td>Name</td>
                                <td>Email</td>
                                <td>Phone</td>
                                <td>Gender</th>
                                <td>Role</th>
                                <td align="center">Action</th>
                            </tr>
                        </thead>
                    </table>
                    <h3 class="text-success">${msg}</h3>
                </div>
        </body>

        </html>