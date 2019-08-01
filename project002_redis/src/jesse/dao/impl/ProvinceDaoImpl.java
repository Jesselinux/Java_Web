package jesse.dao.impl;

import jesse.dao.ProvinceDao;
import jesse.domain.Province;
import jesse.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {

    // 声明成员变量
    private JdbcTemplate tem = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Province> findALL() {

        // 定义aql：
        String sql = "select * from province";

        // 执行sql
        List<Province> list = tem.query(sql, new BeanPropertyRowMapper<Province>(Province.class));

        return list;
    }
}
