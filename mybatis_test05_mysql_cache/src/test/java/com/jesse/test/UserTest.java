package com.jesse.test;


import com.jesse.dao.IUserDao;
import com.jesse.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 测试mybatis的crud操作
 */
public class UserTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
       // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCatch(){
        User user01 = userDao.findById(50);
        System.out.println(user01);

        sqlSession.close();

        // 再次获取sqlSession对象
        sqlSession = factory.openSession();
        // 重新获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);


        User user02 = userDao.findById(50);
        System.out.println(user02);

        System.out.println(user01 == user02);
    }


    /**
     * 测试缓存的同步，缓存清空后是否一致
     */
    @Test
    public void testClearCatch(){

        // 根据id查询用户
        User user01 = userDao.findById(50);
        System.out.println(user01);

        // 更新用户信息
        user01.setUsername("update user,test clear catch.");
        user01.setAddress("ZheJiang JinHua");

        userDao.updateUser(user01);

        // 再次查询id用户
        User user02 = userDao.findById(50);
        System.out.println(user02);

        System.out.println(user01 == user02);
    }
}
