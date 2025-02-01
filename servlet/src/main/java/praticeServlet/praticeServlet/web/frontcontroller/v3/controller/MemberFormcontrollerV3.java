package praticeServlet.praticeServlet.web.frontcontroller.v3.controller;

import praticeServlet.praticeServlet.web.frontcontroller.ModelView;
import praticeServlet.praticeServlet.web.frontcontroller.MyView;
import praticeServlet.praticeServlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormcontrollerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        System.out.println("formV3");
        return new ModelView("new-form");
    }
}
