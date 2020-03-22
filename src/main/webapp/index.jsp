<%@ page language="java" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
  <title>DWR test page</title>
  <mata charset="utf-8"/>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
  <script type='text/javascript' src='dwr/interface/helloWorld.js'></script>
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
    <td><input type="button" value="hello1" onclick="hello1();"></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td><input type="button" value="hello2" onclick="hello2();"></td>
    <td><input type="text" id="hello2" value="post params to server"></td>
    <td></td>
  </tr>
  <tr>
    <td><input type="button" value="hello3" onclick="hello3();"></td>
    <td>String converter</td>
    <td id="hello3Value"></td>
  </tr>
  <tr>
    <td><input type="button" value="hello4" onclick="hello4();"></td>
    <td><input type="text" id="hello4" value="response from server"></td>
    <td id="hello4Value"></td>
  </tr>
  <tr>
    <td><input type="button" value="hello5" onclick="hello5();"></td>
    <td><input type="text" id="hello5" value="java bean converter"></td>
    <td id="hello5Value"></td>
  </tr>
  <tr>
    <td><input type="button" value="hello6" onclick="hello6();"></td>
    <td><input type="text" id="hello6" value="jackson converter"></td>
    <td id="hello6Value"></td>
  </tr>
  <tr>
    <td><input type="button" value="hello7" onclick="hello7();"></td>
    <td><input type="text" id="hello7" value="array converter"></td>
    <td id="hello7Value"></td>
  </tr>
  <tr>
    <td><input type="button" value="hello8" onclick="hello8();"></td>
    <td><input type="text" id="hello8" value="list converter"></td>
    <td id="hello8Value"></td>
  </tr>
</table>
</body>
<script type="text/javascript">
function hello1(){
    console.log("hello1");
    helloWorld.hello1();
}

function hello2(){
    console.log("hello2");
    var name = dwr.util.getValue("hello2");
    helloWorld.hello2(name);
}

function hello3(){
    console.log("hello3");
    helloWorld.hello3(function(data) {
        console.log("hello3 callback");
        dwr.util.setValue("hello3Value", data);
    });
}

function hello4(){
    console.log("hello4");
    var name = dwr.util.getValue("hello4");
    helloWorld.hello4(name, function(data) {
        console.log("hello4 callback");
        dwr.util.setValue("hello4Value", data);
    });
}

function hello5(){
    console.log("hello5");
    var name = dwr.util.getValue("hello5");
    helloWorld.hello5(name, function(data) {
        console.log(data);
        dwr.util.setValue("hello5Value", JSON.stringify(data));
    });
}

function hello6(){
    console.log("hello6");
    var name = dwr.util.getValue("hello6");
    helloWorld.hello6(name, function(data) {
        console.log(data);
        dwr.util.setValue("hello6Value", JSON.stringify(data));
    });
}

function hello7(){
    console.log("hello7");
    var name = dwr.util.getValue("hello7");
    helloWorld.hello7(name, function(data) {
        console.log(data);
        dwr.util.setValue("hello7Value", JSON.stringify(data));
    });
}

function hello8(){
    console.log("hello8");
    var name = dwr.util.getValue("hello8");
    helloWorld.hello8(name, function(data) {
        console.log(data);
        dwr.util.setValue("hello8Value", JSON.stringify(data));
    });
}
</script>
</html>