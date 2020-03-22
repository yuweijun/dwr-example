<%@ page language="java" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
  <title>DWR Ajax user page</title>
  <mata charset="utf-8"/>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <script type='text/javascript' src='user/engine.js'></script>
  <script type='text/javascript' src='user/util.js'></script>
  <script type='text/javascript' src='user/interface/userController.js'></script>
  <style type="text/css">
    table {
        border: 0px;
    }
    td {
        border: solid 1px;
    }
    input[type="button"] {
        background-color: beige;
    }
  </style>
</head>
<body>
<table>
  <thead>
  <th>BUTTON</th>
  <th>INPUT</th>
  <th>OUTPUT</th>
  </thead>
  <tr>
    <td><input type="button" value="get only USER" onclick="getUser();"></td>
    <td>USER</td>
    <td id="userValue"></td>
  </tr>
  <tr>
    <td><input type="button" value="get by TYPE" onclick="getType();"></td>
    <td><input type="text" id="type" value="ADMIN"></td>
    <td id="typeValue"></td>
  </tr>
</table>
</body>
<script type="text/javascript">
function getUser(){
    console.log("getUser");
    userController.getUser(function(data) {
        console.log("getUser callback", data);
        dwr.util.setValue("userValue", data);
    });
}

function getType(){
    var type = dwr.util.getValue("type");
    console.log("getType : " + type);
    userController.getType(type, function(data) {
        console.log("getType callback", data);
        dwr.util.setValue("typeValue", data);
    });
}
</script>
</html>