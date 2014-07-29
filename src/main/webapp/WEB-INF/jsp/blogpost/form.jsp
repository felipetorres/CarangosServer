<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/posts" method="post">
  
	<c:if test="${not empty post.id}">
		<input type="hidden" name="post.id" value="${post.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Mensagem:<br />
	
		<input type="text" name="post.mensagem" value="${post.mensagem}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/posts">Back</a>
