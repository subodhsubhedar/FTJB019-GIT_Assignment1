<%@include file="common/header.jspf"%>
<%@include file="common/nav.jspf"%>

<div class="container">
	Add a Todo for User ${name}:
	<form:form method="post" class="form-group" modelAttribute="todo">

		<form:hidden path="user" />

		<fieldset>
			<form:label path="desc">Description:</form:label>

			<form:input path="desc" type="text" class="form-control" />
			<br>
			<form:errors path="desc" cssClass="text-warning" />

		</fieldset>
		<fieldset>
			<form:label path="targetDate">Target date:</form:label>

			<form:input path="targetDate" type="text" class="form-control" />
			<br>
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>
		<form:button type="submit" class="btn btn-success">Add</form:button>
	</form:form>
</div>
<%@include file="common/footer.jspf"%>
