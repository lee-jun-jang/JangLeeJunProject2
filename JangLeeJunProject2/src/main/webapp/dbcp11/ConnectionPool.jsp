<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	
	/*
	커넥션 pool을 이용한 커넥션 객체 사용]
	-톰캣 서버가 커넥션 pool에 미리 생성해 놓은  Connection객체를 갖다 사용하는것		
	*/
	//1]InitialContext객체 생성(Context > InitialContext)
	Context ctx = new InitialContext();
	
	/*
	 2]InitialContext 객체로 JNDI서비스 구조의  
	   초기 ROOT디렉토리 얻기(예:C드라이브로 접근)
	   lookup해서 각 WAS서버의 서비스 루트 디렉토리를 얻는다 
	   단, 톰캣은 루트 디렉토리명이 java:comp/env이다. 
	 */
	
	//ctx=(Context) ctx.lookup("java:comp/env");	
	 /* 
	  3]context.xml에 등록한 네이밍을 lookup
	    -톰캣의 context.xml에 등록한 네이밍으로 DataSource를 얻는다 
	*/
	 
	//DataSource source=(DataSource)ctx.lookup("jsp");
	//풀 경로로 접근하기
	DataSource source = (DataSource)ctx.lookup("java:comp/env/ICTUSER");	
	/* 
   	4]커넥션 pool에서 톰캣 서버가 생성해 놓은 Connection객체를 갖다 쓰자
    	DataSource의 getConnection()메소드로 
    	톰캣이 pool에 미리 생성해 놓은  Connection객체를 가져다 쓴다.
    */  
	Connection conn= source.getConnection();
	
	String message;
	if(conn !=null){
		message="<h3>커넥션 풀에서 커넥션객체 가져오기 성공</h3>";
		//5]커넥션 풀에 사용한 커넥션 객체 다시 반납(메모리 해제가 아님)
		conn.close();
	}
	else{
		message="<h3>커넥션 풀에서 커넥션객체 가져오기 실패</h3>";
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<%--@ include file="/template/Config.jsp" --%>
		<jsp:include page="/template/Config.jsp"/>
		<title>ConnectionPool.jsp</title>
	
	</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<%--@ include file="/template/Header.jsp" --%>	
			<jsp:include page="/template/Header.jsp"/>		
			<!-- 컨텐츠 시작 -->	
			<div class="p-5 bg-warning text-white">
				<h1>커넥션 풀</h1>
			</div>	
			<fieldset class="border rounded-3 p-3">
				<legend class="float-none w-auto px-3">Connection Pool</legend>
				<h1 class="display-6"><%=message %></h1>
				
			</fieldset>		
			<!-- 컨텐츠 끝 -->
			<%--@ include file="/template/Footer.jsp" --%>
			<jsp:include page="/template/Footer.jsp"/>		
		</div><!-- container-fluid -->
	</div><!--container  -->
</body>
</html>
			