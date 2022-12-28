<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link" href="/app/home.hta">샘플 애플리케이션</a></li>
			<li class="nav-item"><a class="nav-link" href="/app/post/list.hta">게시판</a></li><!-- c:if 쓰면 로그인하면 나오게 하고 안하면 안나오게 함 -->
			<li class="nav-item"><a class="nav-link" href="/app/todo/list.hta">할일</a></li>
		</ul>
		<ul class="navbar-nav">
			<c:choose>
				<c:when test="${not empty loginUserId }">
					<li class="nav-item"><a class="nav-link" href="/app/user/logout.hta">로그아웃</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link" href="/app/user/loginform.hta">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/app/user/registerform.hta">회원가입</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>