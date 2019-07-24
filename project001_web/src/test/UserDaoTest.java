package test;

import dao.UserDao;
import domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){

        User loginUser = new User();
        loginUser.setUsername("Jesse");
        loginUser.setPassword("jesse");



        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);

    }


}
