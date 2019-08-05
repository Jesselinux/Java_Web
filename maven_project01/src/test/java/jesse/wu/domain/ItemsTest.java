package jesse.wu.domain;

import jesse.wu.dao.ItemsDao;
import jesse.wu.dao.impl.ItemsDaoImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ItemsTest {

    @Test
    public void findAll() throws SQLException, ClassNotFoundException {
        ItemsDao itemsDao = new ItemsDaoImpl();
        List<Items> list = itemsDao.findAll();
        for (Items items:list){
            System.out.println(items.getName());
        }
    }
}
