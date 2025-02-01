package praticeServlet.praticeServlet.web.frontcontroller.v4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import praticeServlet.praticeServlet.web.frontcontroller.ModelView;
import praticeServlet.praticeServlet.web.frontcontroller.MyView;
import praticeServlet.praticeServlet.web.frontcontroller.v3.ControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberFormcontrollerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v4.contrloller.MemberFormControllerV4;
import praticeServlet.praticeServlet.web.frontcontroller.v4.contrloller.MemberListControllerV4;
import praticeServlet.praticeServlet.web.frontcontroller.v4.contrloller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("V4 test");

        String requestURI = request.getRequestURI();


        ControllerV4 controllerV4 = controllerMap.get(requestURI);
        if (controllerV4  == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);



        Map<String, Object> model = new HashMap<>();

        String viewName = controllerV4.process(paramMap, model);

        MyView myView = viewRever(viewName);

        myView.render(model , request,response);

    }

    private MyView viewRever(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)) );
        return paramMap;
    }
}
