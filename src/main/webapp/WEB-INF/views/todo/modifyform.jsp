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
			<h1 class="fs-4 border p-2 bg-light">할일 수정폼</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<form class="border bg-light p-3" method="post" action="modify.hta">
				<input type="hidden" name="todoNo" value="${todo.no }" />
				<div class="mb-3">
					<label class="form-label">카테고리</label>
					<select class="form-select" name="categoryNo">
						<option value="" selected="selected" disabled="disabled"> 카테고리를 선택하세요.</option>
						<c:forEach var="category" items="${categories }">
							<option value="${category.no }"> ${category.no eq todo.categoryNo ? 'seleted' : '' }</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">
					<label class="form-label">제목</label>
					<input type="text" class="form-control" name="title" value="${todo.title }"/>
				</div>
				<div class="mb-3">
					<label class="form-label">시작일</label>
					<input type="date" class="form-control" name="beginDate" value="<fmt:formatDate value="${todo.beginDate }" pattern="yyyy-MM-dd" />"/>
				</div>
				<div class="mb-3">
					<label class="form-label">종료일</label>
					<input type="date" class="form-control" name="endDate" value="<fmt:formatDate value="${todo.endDate }" pattern="yyyy-MM-dd" />"/>
				</div>
				<div class="mb-3">
					<label class="form-label">상태</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status" value="등록" ${todo.status eq '등록' ? 'checked' : '' }>
							<label class="form-check-label">등록</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status" value="완료" ${todo.status eq '완료' ? 'checked' : '' }>
							<label class="form-check-label">완료</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status" value="연기" ${todo.status eq '연기' ? 'checked' : '' }>
							<label class="form-check-label">연기</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status" value="취소" ${todo.status eq '취소' ? 'checked' : '' }>
							<label class="form-check-label">취소</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status" value="삭제" ${todo.status eq '삭제' ? 'checked' : '' }>
							<label class="form-check-label">삭제</label>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<label class="form-label">설명</label>
					<textarea rows="3" class="form-control" name="description">${todo.description }</textarea>
				</div>
				<div class="text-end">
					<a href="list.hta" class="btn btn-secondary btn-sm">취소</a>
					<button type="submit" class="btn btn-primary btn-sm">수정</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</body>
</html>