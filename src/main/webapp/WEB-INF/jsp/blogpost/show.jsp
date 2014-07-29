<head>
	<title>Blogpost [show]</title>
</head>
<body>
	<p>
		<b>Mensagem:</b>
		${blogpost.mensagem}
	</p>

	<a href="${pageContext.request.contextPath}/blogposts/${blogpost.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/blogposts">Back</a>
</body>