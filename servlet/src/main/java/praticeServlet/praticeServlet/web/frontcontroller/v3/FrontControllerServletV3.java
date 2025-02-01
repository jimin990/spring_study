package praticeServlet.praticeServlet.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import praticeServlet.praticeServlet.web.frontcontroller.ModelView;
import praticeServlet.praticeServlet.web.frontcontroller.MyView;
import praticeServlet.praticeServlet.web.frontcontroller.v2.ControllerV2;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberFormcontrollerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormcontrollerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("V3 test");

        String requestURI = request.getRequestURI();


        ControllerV3 controllerV3 = controllerMap.get(requestURI);
        if (controllerV3  == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);



        ModelView process = controllerV3.process(paramMap);

        MyView myView = viewRever(process);

        myView.render(process.getModel() , request,response);

    }

    private MyView viewRever(ModelView process) {
        return new MyView("/WEB-INF/views/" + process.getViewName() + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)) );
        return paramMap;
    }
}
