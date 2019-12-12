<%--
  Created by IntelliJ IDEA.
  User: hj
  Date: 2019/12/11
  Time: 11:39 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<%--这里用jsp实现用户的添加功能--%>
<jsp:useBean id="users" scope="session" class="club.banyuan.bmDemo.bean.Users"></jsp:useBean>

<%--接受参数--%>
<%--${param.name}--%>
<%--${param.loginname}--%>
<%--${param.userid}--%>

<%--<jsp:setProperty name="users" property="name" value="阿苗"></jsp:setProperty>--%>
<%--<jsp:setProperty name="users" property="loginname" value="amiao"></jsp:setProperty>--%>
<%--<jsp:setProperty name="users" property="userid" value="4"></jsp:setProperty>--%>

<%--<%request.getParameter("name");%>--%>

<%--${users.userid}--%>

<sql:setDataSource url="jdbc:mysql://localhost:3306/banyuan?useSSL=false&serverTimezone=UTC&autoReconnect=true&characterEncoding=UTF-8"
                   driver="com.mysql.cj.jdbc.Driver" user="root" password="111111" var="ds"/>
<sql:update var="result" dataSource="${ds}">
    insert into users (name,loginname,userid) values ('${param.name}','${param.loginname}',${param.userid});
</sql:update>
插入了多少条数据：${result} ${users.name}
</body>
</html>
