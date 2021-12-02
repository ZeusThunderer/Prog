<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="result_table">
    <jsp:useBean id="check" class="servelets.Point" scope="session"/>
    <tr>
        <th class="variable">X</th>
        <th class="variable">Y</th>
        <th class="variable">R</th>
        <th>Result</th>
    </tr>
    <tbody>
    <tr>
        <th class='the_X'><jsp:getProperty name="check" property="x"/>
        </th>
        <th class='the_Y'><jsp:getProperty name="check" property="y"/>
        </th>
        <th class='the_R'><jsp:getProperty  name="check" property="r"/>
        </th>
        <th class='result'><jsp:getProperty name="check" property="result"/>
        </th>
        </th>
    </tr>
    </tbody>
</table>