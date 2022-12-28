<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<title>application</title>
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container my-3">
	<div class="row mb-3">
		<div class="col">
			<h1 class="fs-4 border p-2 bg-light">게시글 목록</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<p>
				게시글 목록을 확인하세요. 
				<c:if test="${not empty loginUserId }">	<!-- 속성에서 찾았을 때 존재하면 나오게 한다. -->
					<a href="form.hta" class="btn btn-primary btn-sm float-end">새 글쓰기</a>
				</c:if>
			</p>
			<table class="table table-sm">
				<colgroup>
					<col width="7%">
					<col width="*">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">조회수</th>
						<th class="text-center">댓글수</th>
						<th class="text-center">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty posts }">
							<tr>
								<td colspan="6" class="text-center">게시글이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<%--
							느낌표 주석처리 해야 에러안남. 자바코드 변환될 떄 느낌표주석처리는 에러가 남(인식해버려), EL, JSTL 이 자바코드로 인식됨 느낌표 주석처리는
								items="${posts }"는 List<PostListDto> 객체가 조회된다.
								<c:forEach />는 List<PostListDto> 객체에 저장된 PostListDto의 개수만큼 콘텐츠를 반복해서 출력한다.
								var="dto"는 List<PostListDto> 객체에서 순서대로 하나씩 조회한 PostListDto객체가 대입된다.
								${dto.no }는 PostListDto객체의 멤버변수 no에 저장된 값을 출력한다.
							 --%>
							<c:forEach var="dto" items="${posts }"><!-- items에는 배열, 리스트가 와야함 아니면 에러, 반복처리하는 것만 !! -->
								<tr>
									<td>${dto.no }</td>	<!-- 바로 detail로 가는게 아니라 카운트를 증가시키는 readcontroller를 찍고 간다. 이 접근일때만 조회수증가 -->
									<td><a href="read.hta?postNo=${dto.no }" class="text-decoration-none">${dto.title }</a></td>
									<td class="text-center">${dto.userName }</td>
									<td class="text-center">${dto.readCount }</td>
									<td class="text-center">${dto.commentCount }</td>
									<td class="text-center"><fmt:formatDate value="${dto.createdDate }" /></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:if test="${pagination.totalRows > 0 }"> <!-- pagination이 이 조건때 나왔으면 좋겠다. pagination.totalRows gt 0-->
				<nav>
					<ul class="pagination pagination-sm justify-content-center">
						<li class="page-item"><!-- boolean타입에서 isFirst에 is가 getFirst와 같은 형식이라 get타입이라 생략하고 생략하여 사용해줌-->
							<a class="page-link ${pagination.first ? 'disabled' : '' }" href="list.hta?page=${pagination.prevPage }">이전</a>
						</li>
						<!-- el에 내장객체로 param이라는 내장객체가 있다. request.getParameter("page")와 같은 것이다.  -->
						<c:forEach var="number" begin="${pagination.beginPage }" end="${pagination.endPage }">
							<li class="page-item">
								<a class="page-link ${param.page == number ? 'active' : '' }" href="list.hta?page=${number }">${number }</a>
							</li>
						</c:forEach>
						<li class="page-item">
							<a class="page-link ${pagination.last ? 'disabled' : '' }" href="list.hta?page=${pagination.nextPage }">다음</a>
						</li>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</body>
</html>