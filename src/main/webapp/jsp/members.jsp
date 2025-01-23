<%@ page import="java.util.List" %>
<%@ page import="praticeServlet.praticeServlet.domain.member.Member" %>
<%@ page import="praticeServlet.praticeServlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member List</title>
</head>
<body>
    <a href="/index.html">메인</a>
    <table border="1">
        <thead>
            <tr>
                <th>id</th>
                <th>username</th>
                <th>age</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Member member : members) {
        %>
            <tr>
                <td><%= member.getId() %></td>
                <td><%= member.getUsername() %></td>
                <td><%= member.getAge() %></td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</body>
</html>
