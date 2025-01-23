package praticeServlet.praticeServlet.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "requestParamServlet",urlPatterns ="/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("전체 파라미터 조회------");
        request.getParameterNames().asIterator().forEachRemaining(name-> System.out.println(request.getParameter(name)));
        System.out.println("전체 파라미터 조회------");
        System.out.println("개별 파라미터 조회");
        System.out.println("username : " + request.getParameter("username"));
        System.out.println("age : " +request.getParameter("age"));

        String[] usernames = request.getParameterValues("username");
        Arrays.stream(usernames).forEach(name -> System.out.println("이건이터레이터name = " + name));
    }
}
