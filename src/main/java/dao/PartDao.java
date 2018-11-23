package dao;

import model.Part;
import util.PartFilter;

import java.sql.SQLException;
import java.util.List;

public interface PartDao {
    List<Part> getPartsByFilterAndOrder(PartFilter partFilter) throws SQLException;
}
