package dao;

import model.Part;
import util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.*;

public class PartDao {
    private Connection connection;

    public PartDao() {
        this.connection = DbUtil.getConnection();
    }

    public List<Part> getAllParts() {
        List<Part> parts = new ArrayList<Part>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from parts");
            while (rs.next()) {
                Part part = new Part();
                part.setPartName(rs.getString("part_name"));
                part.setPartNumber(rs.getString("part_number"));
                part.setQty(rs.getInt("qty"));
                part.setVendor(rs.getString("vendor"));
                part.setReceive(rs.getDate("receive"));
                part.setShipped(rs.getDate("shipped"));
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }

    public List<Part> getPartsByFilterAndOrder(String partName, String partNumber, String vendor, int qty, LocalDate shippedBefore, LocalDate shippedAfter, LocalDate receiveBefore, LocalDate receiveAfter, String order) {
        List<Part> parts = new ArrayList<Part>();
        try {
            String commandOrder = "";
            if("part_name".equals(order))
                commandOrder = "order by part_name asc";
            else if("-part_name".equals(order))
                commandOrder = "order by part_name desc";

            else if("part_number".equals(order))
                commandOrder = "order by part_number asc";
            else if("-part_number".equals(order))
                commandOrder = "order by part_number desc";

            else if("vendor".equals(order))
                commandOrder = "order by vendor asc";
            else if("-vendor".equals(order))
                commandOrder = "order by vendor desc";

            else if("qty".equals(order))
                commandOrder = "order by qty asc";
            else if("-qty".equals(order))
                commandOrder = "order by qty desc";

            else if("shipped".equals(order))
                commandOrder = "order by shipped asc";
            else if("-shipped".equals(order))
                commandOrder = "order by shipped desc";

            else if("receive".equals(order))
                commandOrder = "order by receive asc";
            else if("-receive".equals(order))
                commandOrder = "order by receive desc";

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from parts " +
                            "where (? is null or part_name like '%'||?||'%') " +
                            "  and (? is null or part_number like '%'||?||'%') " +
                            "  and (? is null or vendor like '%'||?||'%') " +
                            "  and (? is null or qty >= ?) " +
                            "  and (cast(? as date) is null or shipped < cast(? as date)) " +
                            "  and (cast(? as date) is null or shipped > cast(? as date)) " +
                            "  and (cast(? as date) is null or receive < cast(? as date)) " +
                            "  and (cast(? as date) is null or receive > cast(? as date)) " +
                            commandOrder
            );
            preparedStatement.setString(1, partName);
            preparedStatement.setString(2, partName);
            preparedStatement.setString(3, partNumber);
            preparedStatement.setString(4, partNumber);
            preparedStatement.setString(5, vendor);
            preparedStatement.setString(6, vendor);
            preparedStatement.setInt(7, qty);
            preparedStatement.setInt(8, qty);

            preparedStatement.setDate(9, shippedBefore == null ? null : valueOf(shippedBefore));
            preparedStatement.setDate(10, shippedBefore == null ? null : valueOf(shippedBefore));
            preparedStatement.setDate(11, shippedAfter == null ? null : valueOf(shippedAfter));
            preparedStatement.setDate(12, shippedAfter == null ? null : valueOf(shippedAfter));

            preparedStatement.setDate(13, receiveBefore == null ? null : valueOf(receiveBefore));
            preparedStatement.setDate(14, receiveBefore == null ? null : valueOf(receiveBefore));
            preparedStatement.setDate(15, receiveAfter == null ? null : valueOf(receiveAfter));
            preparedStatement.setDate(16, receiveAfter == null ? null : valueOf(receiveAfter));

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Part part = new Part();
                part.setPartName(rs.getString("part_name"));
                part.setPartNumber(rs.getString("part_number"));
                part.setQty(rs.getInt("qty"));
                part.setVendor(rs.getString("vendor"));
                part.setReceive(rs.getDate("receive"));
                part.setShipped(rs.getDate("shipped"));
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }
}
