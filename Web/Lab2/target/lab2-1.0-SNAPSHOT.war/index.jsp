<%@ page import="servelets.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Lab1</title>
    <%
        int side = 500;
        int padding = 50;
        int border = 10;
        int text = 10;
        int serif = 5;
        int arrow_height = 5;
        int arrow_width = 4;

        int r = side / 2 - padding;
        int arrow_side = arrow_width / 2;
        int center = side / 2;
        int r2 = r / 2;
    %>
    <script>
        let center = "<%=center%>"
        let scale = "<%=r%>"
    </script>
    <script src="main.js"></script>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<body>
<div id="head">
    <p>Закиров Тимур P3211 Вариант:12010</p>
</div>
<div class="div-svg">
    <svg id="svg" width="<%=side%>" height="<%=side%>">
        <path class="svg-figure" d="M<%=center%> <%=center%> h -<%=r2%> a <%=r2%> <%=r2%> 0 0 0 <%=r2%> <%=r2%> Z"></path>
        <rect class="svg-figure" x="<%=center%>" y="<%=center%>" width="<%=r%>" height="<%=r2%>"></rect>
        <polygon class="svg-figure" points="<%=center%>,<%=center%> <%=center-r%>,<%=center%> <%=center%>,<%=center-r2%>"></polygon>

        <line class="svg-plot" x1="<%=border%>" y1="<%=center%>" x2="<%=side - border%>" y2="<%=center%>"></line>
        <polygon class="svg-plot" points="<%=side - border%>,<%=center%> <%=side - border - arrow_height%>,<%=center - arrow_side%> <%=side - border - arrow_height%>,<%=center + arrow_side%> "></polygon>
        <text class="svg-text" x="<%=side - border%>" y="<%=center - text%>" text-anchor="middle">x</text>

        <line class="svg-plot" x1="<%=center - r%>" y1="<%=center - serif%>" x2="<%=center - r%>" y2="<%=center + serif%>"></line>
        <text class="svg-text" x="<%=center - r%>" y="<%=center - text%>" text-anchor="middle">-R</text>
        <line class="svg-plot" x1="<%=center - r2%>" y1="<%=center - serif%>" x2="<%=center - r2%>" y2="<%=center + serif%>"></line>
        <text class="svg-text" x="<%=center - r2%>" y="<%=center - text%>" text-anchor="middle">-R/2</text>
        <line class="svg-plot" x1="<%=center + r2%>" y1="<%=center - serif%>" x2="<%=center + r2%>" y2="<%=center + serif%>"></line>
        <text class="svg-text" x="<%=center + r2%>" y="<%=center - text%>" text-anchor="middle">R/2</text>
        <line class="svg-plot" x1="<%=center + r%>" y1="<%=center - serif%>" x2="<%=center + r%>" y2="<%=center + serif%>"></line>
        <text class="svg-text" x="<%=center + r%>" y="<%=center - text%>" text-anchor="middle">R</text>

        <line class="svg-plot" x1="<%=center%>" y1="<%=border%>" x2="<%=center%>" y2="<%=side - border%>"></line>
        <polygon class="svg-plot" points="<%=center%>,<%=border%> <%=center - arrow_side%>,<%=border + arrow_height%> <%=center + arrow_side%>,<%=border + arrow_height%>"></polygon>
        <text class="svg-text" x="<%=center + text%>" y="<%=border%>" dominant-baseline="middle">y</text>

        <line class="svg-plot" x1="<%=center - serif%>" y1="<%=center - r%>" x2="<%=center + serif%>" y2="<%=center - r%>"></line>
        <text class="svg-text" x="<%=center + text%>" y="<%=center - r%>" dominant-baseline="middle">R</text>
        <line class="svg-plot" x1="<%=center - serif%>" y1="<%=center - r2%>" x2="<%=center + serif%>" y2="<%=center - r2%>"></line>
        <text class="svg-text" x="<%=center + text%>" y="<%=center - r2%>" dominant-baseline="middle">R/2</text>
        <line class="svg-plot" x1="<%=center - serif%>" y1="<%=center + r2%>" x2="<%=center + serif%>" y2="<%=center + r2%>"></line>
        <text class="svg-text" x="<%=center + text%>" y="<%=center + r2%>" dominant-baseline="middle">-R/2</text>
        <line class="svg-plot" x1="<%=center - serif%>" y1="<%=center + r%>" x2="<%=center + serif%>" y2="<%=center + r%>"></line>
        <text class="svg-text" x="<%=center + text%>" y="<%=center + r%>" dominant-baseline="middle">-R</text>
    </svg>
</div>
<form id="form" action="ControllerServlet" method="get">
<div id="inputX">
    <label for="x">Введите X: </label>
    <input id="x" type="text" name="x" placeholder="[-3; 5]" >
</div>
<div id="inputY" class="inline" >
    <label for="y">Введите Y: </label>
    <input id="y" type="text" name="y" placeholder="[-3; 5]" >
</div>
<div id="inputR" class="inline">
    <label for="r">Введите R: </label>
    <input type="text" id="r" name="r" placeholder="[2; 5]" value="2">
</div>
<input type=submit  id="send"  class="inline" value="Submit">
</form>
<div>
    <table class="result_table">
        <tr>
            <th class="variable">X</th>
            <th class="variable">Y</th>
            <th class="variable">R</th>
            <th>Результат</th>
        </tr>
        <%
            if (session.getAttribute("results") != null) {
                for (Point check : (ArrayList<Point>) session.getAttribute("results")) {
        %>
        <tr>
            <th class='X'><%=check.getX()%>
            </th>
            <th class='Y'><%=check.getY()%>
            </th>
            <th class='R'><%=check.getR()%>
            </th>
            <th class='result'><%=check.getResult()%>
            </th>
            </th>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
<script src="main.js"></script>
</body>