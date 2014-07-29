<head>
	<title>Blogpost [index]</title>
</head>
<body>
	<h1>Listing Blogposts</h1>

	<table>
		<tr>
			<th>Mensagem</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${blogPostList}" var="post">
			<tr>
				<td>${post.mensagem}</td>
				<td><a href="${pageContext.request.contextPath}/posts/${post.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/posts/${post.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/posts/${post.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/posts/new">New Blogpost</a> 
</body>