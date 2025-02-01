package praticeServlet.praticeServlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import praticeServlet.praticeServlet.web.frontcontroller.ModelView;
import praticeServlet.praticeServlet.web.frontcontroller.MyView;
import praticeServlet.praticeServlet.web.frontcontroller.v3.ControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberFormcontrollerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import praticeServlet.praticeServlet.web.frontcontroller.v4.contrloller.MemberFormControllerV4;
import praticeServlet.praticeServlet.web.frontcontroller.v4.contrloller.MemberListControllerV4;
import praticeServlet.praticeServlet.web.frontcontroller.v4.contrloller.MemberSaveControllerV4;
import praticeServlet.praticeServlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import praticeServlet.praticeServlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="frontControllerServletV5",urlPatterns ="/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormcontrollerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Object handler = getHandler(request);

        if (handler  == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        MyView myView = viewRever(mv);

        myView.render(mv.getModel() , request,response);
        
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }

        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler =" +handler );
    }
    
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);

    }

    private MyView viewRever(ModelView process) {
        return new MyView("/WEB-INF/views/" + process.getViewName() + ".jsp");
    }
}
