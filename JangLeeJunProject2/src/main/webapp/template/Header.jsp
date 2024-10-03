<%@page import="model.JWTokens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//토큰 기반 인증시-유효한 토큰인지 판단
	String token=JWTokens.getTokenInCookie(request, application.getInitParameter("TOKEN-NAME"));
	boolean isValidToken = JWTokens.verifyToken(token);
%> <style>
        .fa-j ,.fa-l {
            color: #747474;
        }

   
        </style>
			<!-- 상단 네비게이션 바 -->
			<nav class="navbar navbar-expand-sm custom-navbar">
				<div class="container-fluid">
					<a class="navbar-brand"><i class="fa-solid fa-j"></i><i class="fa-solid fa-l"></i><i class="fa-solid fa-j"></i></a>
				
					<div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
						<ul class="navbar-nav">
							<!-- 세션기반 인증 -->
							
							<% if(session.getAttribute("username") == null){ %>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/login/Login.jsp"><i class="fas fa-sign-in-alt"></i> 로그인</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/login/Register.jsp"><i class="fas fa-sign-in-alt"></i> 회원가입</a></li>
							<%}else{ %>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/Dataroom/Logout.ict"><i class="fa-solid fa-right-from-bracket"></i> 로그아웃</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/Dataroom/List.ict"><i class="fa-solid fa-list"></i> 게시판</a></li>
							<%} %>
							
							<!-- 토큰 기반 인증 -->
							<%-- 
							<% if(!isValidToken){ %>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/session06/Login.jsp"><i class="fas fa-sign-in-alt"></i> 로그인</a></li>
							<%}else{ %>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/session06/Logout.jsp"><i class="fas fa-sign-out-alt"></i> 로그아웃</a></li>
							<%} %>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/bbs08/List.jsp"><i class="fa-solid fa-chess-board"></i> 게시판</a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value="/Dataroom/List.ict"/>"><i class="far fa-folder-open"></i> 자료실</a></li>
							--%>
						</ul>
					</div>
				</div>
			</nav>
			<!-- 상단 네비게이션바 끝 -->