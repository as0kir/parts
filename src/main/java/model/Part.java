package model;

import java.util.Date;

public class Part {
    private String partName;
    private String partNumber;
    private String vendor;
    private int qty;
    private Date shipped;
    private Date receive;

    public Part() {
    }

    public Part(String partName, String partNumber, String vendor, int qty, Date shipped, Date receive) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.vendor = vendor;
        this.qty = qty;
        this.shipped = shipped;
        this.receive = receive;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public Date getReceive() {
        return receive;
    }

    public void setReceive(Date receive) {
        this.receive = receive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        if (qty != part.qty) return false;
        if (!partName.equals(part.partName)) return false;
        if (!partNumber.equals(part.partNumber)) return false;
        if (!vendor.equals(part.vendor)) return false;
        if (!shipped.equals(part.shipped)) return false;
        return receive.equals(part.receive);
    }

    @Override
    public int hashCode() {
        int result = partName.hashCode();
        result = 31 * result + partNumber.hashCode();
        result = 31 * result + vendor.hashCode();
        result = 31 * result + qty;
        result = 31 * result + shipped.hashCode();
        result = 31 * result + receive.hashCode();
        return result;
    }
}
