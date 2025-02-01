package praticeServlet.praticeServlet.web.frontcontroller.v3;

import praticeServlet.praticeServlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
