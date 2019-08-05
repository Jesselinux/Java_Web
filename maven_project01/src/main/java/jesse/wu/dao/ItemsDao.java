package jesse.wu.dao;

import jesse.wu.domain.Items;

import java.sql.SQLException;
import java.util.List;

public interface ItemsDao {

    public List<Items> findAll() throws SQLException, ClassNotFoundException;

}
