package praticeServlet.praticeServlet.web.frontcontroller.v2;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import praticeServlet.praticeServlet.web.frontcontroller.MyView;

public interface ControllerV2 {
    MyView process(HttpServletRequest request, HttpServletResponse response);
}
