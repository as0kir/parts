package controller;

import dao.PartDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PartController extends HttpServlet {

    private static String LIST_PARTS = "/parts.jsp";
    private PartDao partDao;

    public PartController() {
        partDao = new PartDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int qty = 0;
        LocalDate shippedBefore = null;
        LocalDate shippedAfter = null;
        LocalDate receiveBefore = null;
        LocalDate receiveAfter = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        request.setAttribute("error", "");
        try {
            String partName = request.getParameter("part_name");
            String partNumber = request.getParameter("part_number");
            String vendor = request.getParameter("vendor");

            String strQty = request.getParameter("qty");
            if(strQty != null)
                qty = Integer.parseInt(strQty);

            String strShippedBefore = request.getParameter("shipped_before");
            String strShippedAfter = request.getParameter("shipped_after");
            String strReceiveBefore = request.getParameter("receive_before");
            String strReceiveAfter = request.getParameter("receive_after");

            if(strShippedBefore != null)
                shippedBefore = LocalDate.parse(strShippedBefore, formatter);

            if(strShippedAfter != null)
                shippedAfter = LocalDate.parse(strShippedAfter, formatter);

            if(strReceiveBefore != null)
                receiveBefore = LocalDate.parse(strReceiveBefore, formatter);

            if(strReceiveAfter != null)
                receiveAfter = LocalDate.parse(strReceiveAfter, formatter);

            String order = request.getParameter("order");

            request.setAttribute("parts", partDao.getPartsByFilterAndOrder(partName, partNumber, vendor, qty, shippedBefore, shippedAfter, receiveBefore, receiveAfter, order));
        }
        catch (Exception e){
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_PARTS);
        view.forward(request, response);
    }
}
