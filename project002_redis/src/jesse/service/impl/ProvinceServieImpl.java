package jesse.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jesse.dao.ProvinceDao;
import jesse.dao.impl.ProvinceDaoImpl;
import jesse.domain.Province;
import jesse.jedis.util.JedisPoolUtils;
import jesse.service.ProvinceService;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServieImpl implements ProvinceService {

    // 声明dao：
    private ProvinceDao dao = new ProvinceDaoImpl();


    @Override
    public List<Province> findALL() {
        return dao.findALL();
    }


    // 使用redis缓存：
    @Override
    public String findAllJson() {
        // 先从redis中查询数据：
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");


        //2判断 province_json 数据是否为null
        if(province_json == null || province_json.length() == 0){
            //redis中没有数据
            System.out.println("redis中没数据，查询数据库...");
            //2.1从数据中查询
            List<Province> ps = dao.findALL();
            //2.2将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //2.3 将json数据存入redis
            jedis.set("province",province_json);
            //归还连接
            jedis.close();

        }else{
            System.out.println("redis中有数据，查询缓存...");
        }


        return province_json;

    }
}
