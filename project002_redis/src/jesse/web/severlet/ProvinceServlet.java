package jesse.web.severlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jesse.domain.Province;
import jesse.service.ProvinceService;
import jesse.service.impl.ProvinceServieImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1,调用service查询：
        ProvinceService service = new ProvinceServieImpl();
        List<Province> list = service.findALL();


        // 2,序列化list为json：
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);

        System.out.println(json);

        // 3,响应结果：
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
