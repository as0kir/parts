package dao;

import model.Part;
import util.DbUtil;
import util.PartFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.*;

public class PartDao {
    private Connection connection;

    public PartDao() {
        this.connection = DbUtil.getConnection();
    }

    private String getCommandOrder(String order){
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
        
        return commandOrder;
    }
    
    public List<Part> getPartsByFilterAndOrder(PartFilter partFilter) throws SQLException {
        List<Part> parts = new ArrayList<Part>();
        String commandOrder = getCommandOrder(partFilter.getOrder());

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
        preparedStatement.setString(1, partFilter.getPartName());
        preparedStatement.setString(2, partFilter.getPartName());
        preparedStatement.setString(3, partFilter.getPartNumber());
        preparedStatement.setString(4, partFilter.getPartNumber());
        preparedStatement.setString(5, partFilter.getVendor());
        preparedStatement.setString(6, partFilter.getVendor());
        preparedStatement.setInt(7, partFilter.getQty());
        preparedStatement.setInt(8, partFilter.getQty());

        preparedStatement.setDate(9, partFilter.getShippedBefore() == null ? null : valueOf(partFilter.getShippedBefore()));
        preparedStatement.setDate(10, partFilter.getShippedBefore() == null ? null : valueOf(partFilter.getShippedBefore()));
        preparedStatement.setDate(11, partFilter.getShippedAfter() == null ? null : valueOf(partFilter.getShippedAfter()));
        preparedStatement.setDate(12, partFilter.getShippedAfter() == null ? null : valueOf(partFilter.getShippedAfter()));

        preparedStatement.setDate(13, partFilter.getReceiveBefore() == null ? null : valueOf(partFilter.getReceiveBefore()));
        preparedStatement.setDate(14, partFilter.getReceiveBefore() == null ? null : valueOf(partFilter.getReceiveBefore()));
        preparedStatement.setDate(15, partFilter.getReceiveAfter() == null ? null : valueOf(partFilter.getReceiveAfter()));
        preparedStatement.setDate(16, partFilter.getReceiveAfter() == null ? null : valueOf(partFilter.getReceiveAfter()));

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

        return parts;
    }
}
