package praticeServlet.praticeServlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "webServlet",urlPatterns = "/test")
public class HelloServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);
        System.out.println(" username= " + req.getParameter("username"));

        extracted(req);
    }

    private static void extracted(HttpServletRequest req) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + req.getMethod()); //GET
        System.out.println("request.getProtocol() = " + req.getProtocol());//HTTP/1.1
        System.out.println("request.getScheme() = " + req.getScheme()); //http// http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + req.getRequestURL());// /request-header
        System.out.println("request.getRequestURI() = " + req.getRequestURI());//username=hi
        System.out.println("request.getQueryString() = " + req.getQueryString());
        System.out.println("request.isSecure() = " + req.isSecure());//https 사용유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }
}
