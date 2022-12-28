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
			<h1 class="fs-4 border p-2 bg-light">게시글 상세정보</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<c:choose>
				<c:when test="${param.error eq 'comment' }"> <!-- 딜리트코멘트컨트롤러에서 사용자가 다를 때 지우려고 하면 error=comment 를 -->
					<div class="alert alert-danger">			<!--param. 이 el표현식에서 제공되는 내장객체이며 request.getParameter로 불러오는 것과 같음   -->
						다른 사람의 댓글은 삭제할 수 없습니다.
					</div>
				</c:when>
				<c:when test="${param.error eq 'post' }"> 	<!-- error=post를 불러오는 것 -->
					<div class="alert alert-danger">		
						다른 사람의 게시글은 수정하거나 삭제할 수 없습니다. 
					</div>
				</c:when>
			</c:choose>
			<p>
				게시글 상세정보를 확인하세요.
				<c:if test="${not empty loginUserId }">	<!-- 세션에 저장되어있는 객체를 불러오는 것  -->
					<button class="btn btn-primary btn-sm float-end" data-bs-toggle="modal" data-bs-target="#modal-form-comments">댓글 쓰기</button>
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
						<td colspan="3"><c:out value="${post.title }" /></td>
					</tr>
					<tr>
						<th>번호</th>
						<td>${post.no }</td>
						<th>작성자</th>
						<td>${post.userName }</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${post.createdDate }" /></td>
						<th>최종수정일</th>
						<td><fmt:formatDate value="${post.updatedDate }" /></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><fmt:formatNumber value="${post.readCount }" /></td>
						<th>댓글수</th>
						<td><fmt:formatNumber value="${post.commentCount }" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><c:out value="${post.content }" /></td>	<!-- xss(크로스사이트스크립트공격) 취약점(외부에서 검증되지 않은 입력문자)때문에 c:out으로 적어줘야 한다. -->
					</tr>
					<%-- <tr>
						<th>내용</th>
						<td colspan="3"><textarea rows="5" class="form-control" readonly="readonly">${post.content }</textarea></td><!-- 얘도 취약점 방어가능 -->
					</tr> --%>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${post.userId == loginUserId }">
		<div class="row mb-3">
			<div class="col-12">
				<a href="modifyform.hta?postNo=${post.no }" class="btn btn-warning btn-sm">수정</a>
				<a href="delete.hta?postNo=${post.no }" class="btn btn-danger btn-sm">삭제</a>
			</div>
		</div>
	</c:if>
	
	<!-- 댓글목록 -->
	<div class="row mb-3">
		<div class="col">
		<c:choose>
            <c:when test="${empty comments }">
               <div class="border p-2 mb-2">
                  <p class="mb-0">등록된 댓글이 없습니다.</p>
               </div>
            </c:when>
            <c:otherwise>
            	<%--
            		items="${comments }"는 요청객체에 "comments"로 저장된 객체를 조회한다.
            		${comments}로 조회된 객체는 List<CommentListDto>다.
            		var="comment"는 반복을 수행할 때마다 List<CommentListDto>에 저장된 CommentListDto객체가 순서대로 comment에 대입된다.
            		comment에 대입된 객체는 CommentListDto 다.
            		comment -> CommentListDto -> no=100, userId="hong", userName="홍길동", content="댓글입니다."
            	 --%>
               <c:forEach var="comment" items="${comments }"><!-- items에는 배열과 리스트같은  -->
                  <div class="border p-2 mb-2">
                     <p class="mb-0 small">
                        <span class="text-muted">${comment.userName }</span> 
                        <span class="text-muted float-end"><fmt:formatDate value="${comment.createdDate }"/></span>
                     </p>
                     <p class="mb-0">
                        ${comment.content }
                        <c:if test="${comment.userId == loginUserId }"> <!-- 세션에 저장되어있는 loginUserId 불러옴  -->
                       		<a href="deleteComment.hta?postNo=${comment.postNo }&commentNo=${comment.no }" class="float-end"><i class="bi bi-trash-fill text-danger"></i></a>
                        </c:if>
                     </p>
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
		<form id="form-add-depts" class="p-3" method="post" action="insertComment.hta">
		<input type="hidden" name="postNo" value="${post.no }">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">댓글 등록폼</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
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