package servelets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AreaCheckServlet", value = "/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            double x = Double.parseDouble(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
            double r = Double.parseDouble(req.getParameter("r"));
        } catch (Exception e) {
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession(true);
        Point point= getPoint(req, resp);
        if(!point.isValid())
            return;
        session.setAttribute("results", setResults(point, session));
        if(req.getParameter("clicked") == null)
            headToTablePage(req, resp, point, session);
        else
            sendAJAXResponse(req, resp, point, session);
    }

    private ArrayList<Point> setResults(Point check, HttpSession session) {
        ArrayList<Point> results = (ArrayList) session.getAttribute("results");
        if(results == null)
            results = new ArrayList();
        results.add(check);
        return results;
    }

    private void sendAJAXResponse(HttpServletRequest req, HttpServletResponse resp,Point point, HttpSession session) throws IOException {
        String str =    "        <tr>\n" +
                        "            <th>"+point.getX()+"</th>\n" +
                        "            <th>"+point.getY()+"</th>\n" +
                        "            <th>"+point.getR()+"</th>\n" +
                        "            <th>"+point.getResult()+"</th>\n" +
                        "        </tr>";
        resp.setContentType("text/json; charset=UTF-8");
        resp.getWriter().write(str);
    }

    private void headToTablePage(HttpServletRequest req, HttpServletResponse resp,Point point, HttpSession session) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/table.jsp");
        session.setAttribute("check", point);
        rd.include(req, resp);
    }

    private Point getPoint(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double x = Double.parseDouble(req.getParameter("x"));
        double y = Double.parseDouble(req.getParameter("y"));
        double r = Double.parseDouble(req.getParameter("r"));
        Point point = new Point(x, y, r);
        point.setResult(isAreaHit(x, y, r));
        if (!isValid(x, y, r)){
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            point.setValid(false);
        }
        return point;
    }

    public boolean isAreaHit(double x, double y, double r) {
        return ((x >= -r) && (x <= 0) && (y >= 0) && (y <= r/2) && (y<=x/2 + r/2)) ||
                ((x >= -r/2) && (x <= 0) && (y <= 0) && (y >= -r/2) && (y*y+x*x <= r/4  )) ||
                ((x >= 0) && (x <= r) && (y <= 0) && (y >= -r/2));
    }

    public boolean isValid(double x, double y, double r) {
        return (x >= -3) && (x <= 5) && (y >= -3) && (y <= 5) && (r >= 2) && (r <= 5);
    }
    public void destroy() {
    }
}