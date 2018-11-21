package controller;

import dao.PartDao;
import util.PartFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PartController extends HttpServlet {

    private static String LIST_PARTS = "/parts.jsp";
    private PartDao partDao;

    public PartController() {
        partDao = new PartDao();
    }

    private void addOrderToAttributes(HttpServletRequest request, String orderName) {
        String oldOrder = request.getParameter("order");
        if(oldOrder != null) {
            String queryString = request.getQueryString();
            StringBuffer requestURL = request.getRequestURL();

            if(oldOrder.equals(orderName)) {
                queryString = queryString.replace("order=" + oldOrder, "order=-" + orderName);
            }
            else {
                queryString = queryString.replace("order=" + oldOrder, "order=" + orderName);
            }
            request.setAttribute("order_" + orderName, requestURL + "?" + queryString);
        }
    }

    private void setAttributes(HttpServletRequest request){
        request.setAttribute("part_name", request.getParameter("part_name"));
        request.setAttribute("part_number", request.getParameter("part_number"));
        request.setAttribute("vendor", request.getParameter("vendor"));
        request.setAttribute("qty", request.getParameter("qty"));
        request.setAttribute("shipped_before", request.getParameter("shipped_before"));
        request.setAttribute("shipped_after", request.getParameter("shipped_after"));
        request.setAttribute("receive_before", request.getParameter("receive_before"));
        request.setAttribute("receive_after", request.getParameter("receive_after"));
        request.setAttribute("order", request.getParameter("order"));

        addOrderToAttributes(request, "part_name");
        addOrderToAttributes(request, "part_number");
        addOrderToAttributes(request, "vendor");
        addOrderToAttributes(request, "qty");
        addOrderToAttributes(request, "shipped");
        addOrderToAttributes(request, "receive");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAttributes(request);
        request.setAttribute("error", "");
        try {
            PartFilter partFilter = new PartFilter(request);
            request.setAttribute("parts", partDao.getPartsByFilterAndOrder(partFilter));
        }
        catch (Exception e){
            request.setAttribute("error", "Errors: " + e.getMessage());
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_PARTS);
        view.forward(request, response);
    }
}
