package praticeServlet.praticeServlet.web.frontcontroller.v2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import praticeServlet.praticeServlet.domain.member.Member;
import praticeServlet.praticeServlet.domain.member.MemberRepository;
import praticeServlet.praticeServlet.web.frontcontroller.MyView;
import praticeServlet.praticeServlet.web.frontcontroller.v2.ControllerV2;

import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) {

        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
