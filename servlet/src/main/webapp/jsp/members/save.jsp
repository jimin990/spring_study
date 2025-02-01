<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="praticeServlet.praticeServlet.domain.member.Member"%>
<%@ page import="praticeServlet.praticeServlet.domain.member.MemberRepository"%>
<%
        MemberRepository memberRepository = MemberRepository.getInstance();
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

%>

<html>
<head>
    <meta charset="UTF-8">
</head>

<body>
성공
<ul>
    <li> userId=<%=member.getId()%></li>
    <li> username=<%=member.getUsername()%></li>
    <li> userAge=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>


