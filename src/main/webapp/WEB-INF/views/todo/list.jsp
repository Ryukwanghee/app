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
			<h1 class="fs-4 border p-2 bg-light">할일 목록</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<p>
				할일 목록을 확인하세요.
				<c:if test="${not empty loginUserId }">
					<a href="form.hta" class="btn btn-primary btn-sm float-end">새 할일 등록하기</a>
				</c:if>
			</p>
			<table class="table table-sm">
				<colgroup>
					<col width="5%">
					<col width="25%">
					<col width="*">
					<col width="6%">
					<col width="5%">
					<col width="8%">
					<col width="8%">
					<col width="8%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>카테고리</th>
						<th>제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">상태</th>
						<th class="text-center">시작일</th>
						<th class="text-center">종료일</th>
						<th class="text-center">완료일</th>
					</tr>
				</thead>
				<tbody class="small">
					<c:choose>
						<c:when test="${empty todos }">
							<tr>
								<td colspan="8" class="text-center">등록된 일정이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="dto" items="${todos }">
								<tr>
									<td>${dto.no }</td>
									<td>${dto.categoryName }</td>
									<td><a href="detail.hta?todoNo=${dto.no }" class="text-decoration-none">${dto.title }</a></td>
									<td class="text-center">${dto.userName }</td>
									<td class="text-center">${dto.status }</td>
									<td class="text-center"><fmt:formatDate value="${dto.beginDate }" pattern="yyyy-MM-dd" /></td>
									<td class="text-center"><fmt:formatDate value="${dto.endDate }" pattern="yyyy-MM-dd" /></td>
									<td class="text-center"><fmt:formatDate value="${dto.completeDate }" pattern="yyyy-MM-dd" /></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:if test="${pagination.totalRows > 0 }">
				<nav>
					<ul class="pagination pagination-sm justify-content-center">
						<li class="page-item">
							<a class="page-link ${pagination.first ? 'disabled' : '' }" href="list.hta?page=${pagination.prevPage }">이전</a>
						</li>
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