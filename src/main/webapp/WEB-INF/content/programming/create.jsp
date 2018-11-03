<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="csns" uri="http://cs.calstatela.edu/csns" %>

<c:set var="section" value="${assignment.section}"/>

<script>
$(function(){
    $("#publishDate").datetimepicker({
        timeFormat: 'HH:mm:ss'
    });
    $("#dueDate").datetimepicker({
        timeFormat: 'HH:mm:ss'
    });
    $("select[name='assignmentType']").click(function(){
       if( $(this).val() == "ONLINE" )
           window.location.href = "online/create?sectionId=${section.id}";
       else if( $(this).val() == "RUBRIC" )
    	   window.location.href = "../rubric/assignment/create?sectionId=${section.id}";
       else if($(this).val() == "PASSIGNMENT")
    	   window.location.href = "../assignment/programming/create?sectionId=${section.id}";
    });
    $(".res").hide();
    if($("#description\\.type").val() != "None")
        $("#res"+$("#description\\.type").val()).show();
    $("#description\\.type").click(function(){
        $(".res").hide();
        $("#res"+$(this).val()).show();
    });
    $("textarea").each(function(){
        CKEDITOR.replace( $(this).attr("id"), {
          toolbar : "Default"
        });
    });
    $("div.help").dialog({
        autoOpen: false,
        modal: true
    });
});
function help( name )
{
    $("#help-"+name).dialog("open");
}
</script>

<ul id="title">
<li><a class="bc" href="<c:url value='/section/taught#section-${section.id}' />">${section.course.code} - ${section.number}</a></li>
<li>Create Assignment
  <select name="assignmentType" style="margin-left: 2em;">
    <option value="REGULAR">Regular</option>
    <option value="ONLINE">Online</option>
    <option value="RUBRIC">Rubric</option>
    <option value="PASSIGNMENT">Programming  Assignment</option>
  </select>
</li>
<li class="align_right">
  <form action="search" method="get">
    <input name="text" type="text" size="40" />
    <input name="sectionId" type="hidden" value="${param.sectionId}" />
    <input name="search" type="submit" value="Search" class="subbutton" />
  </form>
</li>
</ul>
<form:form  enctype="multipart/form-data">
<table class="general">
  <tr>
    <th class="shrink">Name</th>
    <td>
     
    </td>
  </tr>


</table>
</form:form>




<div id="help-alias" class="help">The <em>alias</em> of an assignment is a
shorthand of the assignment name, e.g. "HW1" for "Homework 1". It's used
as column title in the grade sheet.</div>

<div id="help-filext" class="help">Use space to separate the file extensions
allowed for this assignment, e.g. <span class="tt">txt java html</span>. If
this field is left empty, the students may upload files with any extension.</div>

<div id="help-pubdate" class="help">
<em>Publish Date</em> controls when the assignment is made available to the
students. For example, if you want to conduct a quiz from 9pm to 9:30pm on
3/3/2010, you can set the publish date to be 3/3/2010 21:00 and the due date
to be 3/3/2010 21:30.</div>

<div id="help-aadd" class="help">
<p><em>Viewable After Due Date</em> determines whether the students can view
the assignment and their own solutions after the due date. If you do not want
the students to see the assignment after the due date (e.g. you may want to
recycle the assignment questions later), then uncheck this option.</p>
<p>Note that this option can be changed any time, which means that you can give
the students temporary access to the assignment after the due date by enabling
it and then disabling it later.</p></div>
