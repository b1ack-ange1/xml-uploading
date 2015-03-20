<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>XML upload</title>
        <style>
            body{
                background: skyblue;
                width: 100%;
                height: 100%;
            }
            .container-fluid{
                position: absolute;
                top: 0px;
                bottom: 0px;
                left: 0px;
                right: 0px;
                width: 410px;
                height: 350px;
                margin: auto;
                background:  aquamarine;
                border: 2px solid black;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
            }
            td{
                border: 1px solid black;
                border-radius: 5px;
                
                padding:  5px;
            }
            td:hover{
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
            }

        </style>
    </head>
    <body>
        <div class="container-fluid">
            <h2>Uploaded File <i><c:out value="${name}" /></i> details: </h2>
                    <table>
                        <c:forEach items="${files}" var="filename">
                            <tr>
                                <td style="font-weight: bold"><c:out value="${filename}" /></td>
                            </tr>
                        </c:forEach>

                    </table>
                    Processed <c:out value="${size}" /> items
        </div>
    </body>
</html>