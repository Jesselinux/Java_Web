package jesse.wu.dao.impl;

import jesse.wu.dao.ItemsDao;
import jesse.wu.domain.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements ItemsDao {

    public List<Items> findAll() throws SQLException, ClassNotFoundException {

        List<Items> list = null;
        list = new ArrayList<Items>();

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // 加载驱动类：
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 获取connection对象：
            connection = DriverManager.getConnection("jdbc:mysql:///maven_test","root","Jesse_508");

            // 获取真正操作数据的对象：
            pst = connection.prepareCall("select * from student");

            // 执行数据库查询操作：
            rs = pst.executeQuery();

            // 把数据库结果集转成java的list集合：
            while (rs.next()){
                Items items = new Items();
                items.setId(rs.getInt("id"));
                items.setName(rs.getString("name"));
                list.add(items);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
            pst.close();
            rs.close();
        }

        return list;
    }
}
