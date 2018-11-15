<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="csns" uri="http://cs.calstatela.edu/csns"%>

<c:set var="section" value="${assignment.section}" />

<script>
	$(function() {
		$("#publishDate").datetimepicker({
			timeFormat : 'HH:mm:ss'
		});
		$("#dueDate").datetimepicker({
			timeFormat : 'HH:mm:ss'
		});
		$("select[name='assignmentType']")
				.click(
						function() {
							if ($(this).val() == "ONLINE")
								window.location.href = "../online/create?sectionId=${section.id}";
							else if ($(this).val() == "RUBRIC")
								window.location.href = "../rubric/assignment/create?sectionId=${section.id}";
							else if ($(this).val() == "PASSIGNMENT")
								window.location.href = "../assignment/programming/create?sectionId=${section.id}";
						});
		$(".res").hide();
		if ($("#description\\.type").val() != "None")
			$("#res" + $("#description\\.type").val()).show();
		$("#description\\.type").click(function() {
			$(".res").hide();
			$("#res" + $(this).val()).show();
		});
		$("textarea").each(function() {
			CKEDITOR.replace($(this).attr("id"), {
				toolbar : "Default"
			});
		});
		$("div.help").dialog({
			autoOpen : false,
			modal : true
		});
	});
	function help(name) {
		$("#help-" + name).dialog("open");
	}
</script>

<ul id="title">
	<li><a class="bc"
		href="<c:url value='/section/taught#section-${section.id}' />">${section.course.code}
			- ${section.number}</a></li>
	<li>Create Assignment <select name="assignmentType"
		style="margin-left: 2em;">
			<option value="REGULAR">Regular</option>
			<option value="ONLINE">Online</option>
			<option value="RUBRIC">Rubric</option>
			<option value="PASSIGNMENT">Programming Assignment</option>
	</select>
	</li>
	<li class="align_right">
		<form action="search" method="get">
			<input name="text" type="text" size="40" /> <input name="sectionId"
				type="hidden" value="${param.sectionId}" /> <input name="search"
				type="submit" value="Search" class="subbutton" />
		</form>
	</li>
</ul>
<form>
	<div class="form-group">
		<table class="general">
			<tr>
				<th class="shrink">Name</th>
				<td><input type="email" class="form-control leftinput"
					id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="">
				</td>
			</tr>
			<tr>
				<th>Description</th>
				<td><textarea class="form-control leftinput" rows="4" cols="4"></textarea>
				</td>
			</tr>

			<tr>
				<th><a href="#" data-toggle="tooltip" data-placement="top" title="The alias of an assignment is a shorthand of the assignment name, e.g. 'HW1' for 'Homework 1'. It's used as column title in the grade sheet.">Alias</a></th>
				<td>
					<div class="col-md-4">
						<input type="email" class="form-control leftinput" id="exampleInputEmail1"
							size="10">
					</div>

				</td>
			</tr>

			<tr>
				<th>Upload Test Case</th>
				
				<td>
				<div class="col-md-4">
				<input type="file" class="form-control leftinput">
				</div>
				</td>
			</tr>
			<tr>
				<th>Number of times to run test</th>
				<td>
					<div class="col-md-1">
						<input type="number" class="form-control leftinput" id="exampleInputEmail1"
							size="10">
					</div>

				</td>
			</tr>
			<tr>
				<th>Total Points</th>
				<td>
					<div class="col-md-4">
						<input type="text" class="form-control leftinput" id="exampleInputEmail1"
							size="10">
					</div>

				</td>
			</tr>
			<tr>
				<th>Allowed File Extension</th>
				<td>
					<div class="col-md-4">
						<input type="text" class="form-control leftinput" id="exampleInputEmail1"
							size="10">
					</div>

				</td>
			</tr>
			<tr>
				<th>Published Date</th>
				<td>
					<div class="col-md-4">
						<input type="text" id="dueDate" class="form-control leftinput"
							id="exampleInputEmail1" size="10">
					</div>

				</td>
			</tr>
			<tr>
				<th>Due Date</th>
				<td>
					<div class="col-md-4">
						<input type="text" id="publishDate" class="form-control leftinput"
							id="exampleInputEmail1" size="10">
					</div>

				</td>
			</tr>
			<tr>
				<th>Available after due date</th>
				<td>
				<input type="checkbox"/>
				</td>
			</tr>
			<tr><th></th><td><input class="subbutton" type="submit" value="Create" /></td></tr>


		</table>
	</div>
</form>

