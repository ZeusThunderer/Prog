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
        <td class='the_X'><jsp:getProperty name="check" property="x"/>
        </td>
        <td class='the_Y'><jsp:getProperty name="check" property="y"/>
        </td>
        <td class='the_R'><jsp:getProperty  name="check" property="r"/>
        </td>
        <td class='result'><jsp:getProperty name="check" property="result"/>
        </td>
    </tr>
    </tbody>
</table>