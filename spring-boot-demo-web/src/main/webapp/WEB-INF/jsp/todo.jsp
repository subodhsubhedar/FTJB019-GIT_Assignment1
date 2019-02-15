<%@include file="common/header.jspf"%>
<%@include file="common/nav.jspf"%>


<div class="container">
	<h2>Todo for ${user} as below</h2>

	<table class="table table-striped table-bordered">
		<thead class="thead-dark">
			<tr>
				<th>Id</th>
				<th>Description</th>
				<th>Target Date</th>
				<th>Status</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>


			<c:forEach items="${todoList}" var="todo">
				<tr>
					<td>${todo.id}</td>
					<td>${todo.desc}</td>

					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${todo.targetDate}" /></td>
					<td>${todo.done}</td>

					<td><a class="btn btn-success"
						href="/update-todo?id=${todo.id}">Update</a> <a
						class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a>

					</td>
				</tr>
			</c:forEach>


		</tbody>


	</table>

	<div>
		<a class=button href="/add-todo">Add a Todo</a>
	</div>
</div>
<%@include file="common/footer.jspf"%>