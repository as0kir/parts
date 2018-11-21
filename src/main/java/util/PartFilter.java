package util;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PartFilter {
    private String partName;
    private String partNumber;
    private String vendor;

    private int qty;
    private LocalDate shippedBefore;
    private LocalDate shippedAfter;
    private LocalDate receiveBefore;
    private LocalDate receiveAfter;
    private String order;

    public PartFilter(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);

        qty = 0;
        shippedBefore = null;
        shippedAfter = null;
        receiveBefore = null;
        receiveAfter = null;

        partName = request.getParameter("part_name");
        partNumber = request.getParameter("part_number");
        vendor = request.getParameter("vendor");

        String strQty = request.getParameter("qty");
        if (strQty != null && !"".equals(strQty))
            qty = Integer.parseInt(strQty);

        String strShippedBefore = request.getParameter("shipped_before");
        String strShippedAfter = request.getParameter("shipped_after");
        String strReceiveBefore = request.getParameter("receive_before");
        String strReceiveAfter = request.getParameter("receive_after");

        if (strShippedBefore != null && !"".equals(strShippedBefore))
            shippedBefore = LocalDate.parse(strShippedBefore, formatter);

        if (strShippedAfter != null && !"".equals(strShippedAfter))
            shippedAfter = LocalDate.parse(strShippedAfter, formatter);

        if (strReceiveBefore != null && !"".equals(strReceiveBefore))
            receiveBefore = LocalDate.parse(strReceiveBefore, formatter);

        if (strReceiveAfter != null && !"".equals(strReceiveAfter))
            receiveAfter = LocalDate.parse(strReceiveAfter, formatter);

        order = request.getParameter("order");
    }

    public String getPartName() {
        return partName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public int getQty() {
        return qty;
    }

    public LocalDate getShippedBefore() {
        return shippedBefore;
    }

    public LocalDate getShippedAfter() {
        return shippedAfter;
    }

    public LocalDate getReceiveBefore() {
        return receiveBefore;
    }

    public LocalDate getReceiveAfter() {
        return receiveAfter;
    }

    public String getOrder() {
        return order;
    }
}
