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
			<h1 class="fs-4 border p-2 bg-light">할일 상세정보</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<p>
				할일 상세정보를 확인하세요.
				<c:if test="${not empty loginUserId }"> 
			   		<button class="btn btn-primary btn-sm float-end" data-bs-toggle="modal" data-bs-target="#modal-form-comments">작업내용 등록</button>
			   	</c:if>
			</p>
			<table class="table table-sm">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td>${todo.title }</td>
						<th>카테고리</th>
						<td>${todo.categoryName }</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${todo.createdDate }" pattern="yyyy-MM-dd" /></td>
						<th>최종수정일</th>
						<td><fmt:formatDate value="${todo.updatedDate }" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<th>번호</th>
						<td>${todo.no }</td>
						<th>작성자</th>
						<td>${todo.userName }</td>
					</tr>
					<tr>
						<th>시작일</th>
						<td><strong><fmt:formatDate value="${todo.beginDate }" pattern="yyyy-MM-dd" /></strong></td>
						<th>종료일</th>
						<td><strong class="text-danger"><fmt:formatDate value="${todo.endDate }" pattern="yyyy-MM-dd" /></strong></td>
					</tr>
					<tr>
						<th>할일 상태</th>
						<td><strong>${todo.status }</strong></td>
						<th>완료일</th>
						<td><fmt:formatDate value="${todo.completeDate }" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<th>설명</th>
						<td colspan="3">${todo.description }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<a href="modifyform.hta?todoNo=${todo.no }" class="btn btn-warning btn-sm">수정</a>
			<a href="complete.hta?todoNo=${todo.no }" class="btn btn-primary text-end btn-sm float-end">완료</a>
		</div>
	</div>
	
	<!-- 댓글 목록  -->
	<div class="row mb-3">
		<div class="col">
			<c:choose>
				<c:when test="${empty works }">
					<div class="border p-2 mb-2">
						<p class="mb-0">등록된 작업내용이 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="work" items="${works }">
						<div class="border p-2 mb-2">
							<p class="mb-0 small">
								<span class="text-muted">${work.userName }</span> 
								<span class="text-muted float-end"><fmt:formatDate value="${work.createdDate }" pattern="yyyy-MM-dd"/></span>
							</p>
							<p class="mb-0"><c:out value="${work.content }" /></p>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<!-- 댓글 등록폼 -->
<div class="modal" tabindex="-1" id="modal-form-comments">
	<div class="modal-dialog">
		<form id="form-add-depts" class="p-3" method="post" action="addwork.hta">
		<input type="hidden" name="todoNo" value="1000">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">작업 내용 등록폼</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p class="card-text">작업 내용을 작성하세요</p>
				<div class="row mb-2">
					<div class="col-sm-12">
						<textarea class="form-control" rows="3" name="content"></textarea>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn-xs" data-bs-dismiss="modal">닫기</button>
				<button type="submit" class="btn btn-primary btn-xs">등록</button>
			</div>
		</div>
		</form>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</body>
</html>