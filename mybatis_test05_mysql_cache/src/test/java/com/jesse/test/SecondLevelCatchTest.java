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

public class SecondLevelCatchTest {

    private InputStream in;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        in.close();
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession01 = factory.openSession();
        IUserDao dao01 = sqlSession01.getMapper(IUserDao.class);
        User user01 = dao01.findById(41);
        System.out.println(user01);
        sqlSession01.close();//一级缓存消失

        SqlSession sqlSession02 = factory.openSession();
        IUserDao dao02 = sqlSession02.getMapper(IUserDao.class);
        User user02 = dao02.findById(41);
        System.out.println(user02);
        sqlSession02.close();

        System.out.println(user01 == user02);
    }

}
