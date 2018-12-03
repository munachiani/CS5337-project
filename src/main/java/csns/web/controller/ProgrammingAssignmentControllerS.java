package csns.web.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import csns.model.academics.Assignment;
import csns.model.academics.CodingAssignment;
import csns.model.academics.OnlineAssignment;
import csns.model.academics.dao.AssignmentDao;
import csns.model.academics.dao.SectionDao;
import csns.model.core.Resource;
import csns.model.core.ResourceType;
import csns.security.SecurityUtils;
import csns.util.FileIO;
import csns.web.editor.CalendarPropertyEditor;
import csns.web.validator.AssignmentValidator;

@Controller
@SessionAttributes("assignment")
public class ProgrammingAssignmentControllerS {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private AssignmentDao assignmentDao;
	

    @Autowired
    private AssignmentValidator assignmentValidator;
    
	@Autowired
	private FileIO fileIO;

	private static final Logger logger = LoggerFactory.getLogger(AssignmentControllerS.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Calendar.class, new CalendarPropertyEditor());
	}

	@RequestMapping(value = "assignment/programming/create", method = RequestMethod.GET)
	public String create(@RequestParam Long sectionId, ModelMap models) {
		CodingAssignment assignment = new CodingAssignment();
		assignment.setSection(sectionDao.getSection(sectionId));
		models.put("assignment", assignment);
		return "programming/create";
	}
	
	@RequestMapping(value = "assignment/programming/create", method = RequestMethod.POST)
	public String create(@ModelAttribute ("assignment") CodingAssignment assignment,
	        @RequestParam(value = "file", required = false) MultipartFile uploadedFile,
	        BindingResult result, SessionStatus sessionStatus ) {
		 assignmentValidator.validate( assignment, uploadedFile, result );
	        if( result.hasErrors() ) return "programming/create";

	        Resource description = assignment.getDescription();
	        if( description.getType() == ResourceType.NONE )
	            assignment.setDescription( null );
	        else if( description.getType() == ResourceType.FILE )
	            description.setFile( fileIO.save( uploadedFile,
	                SecurityUtils.getUser(), false ) );

	        assignment = (CodingAssignment) assignmentDao.saveAssignment( assignment );
	        sessionStatus.setComplete();

	        logger.info( SecurityUtils.getUser().getUsername()
	            + " created assignment " + assignment.getId() );

	        return "redirect:/section/taught#section-"
	            + assignment.getSection().getId();
	    }
	
	
	 @RequestMapping(value = "/assignment/programming/edit", method = RequestMethod.GET)
	    public String edit( @RequestParam Long id, ModelMap models )
	    {
	        CodingAssignment assignment = (CodingAssignment) assignmentDao.getAssignment( id );
	        if( assignment.getDescription() == null )
	            assignment.setDescription( new Resource( "Assignment Description" ) );
	        models.put( "assignment", assignment );
	        return assignment.isCoding() ? "assignment/programming/edit"
	            : "assignment/edit";
	    }
	 
	 @RequestMapping(value = "/assignment/programming/edit", method = RequestMethod.POST)
	    public String edit(
	        @ModelAttribute Assignment assignment,
	        @RequestParam(value = "file", required = false) MultipartFile uploadedFile,
	        HttpServletRequest request, BindingResult result,
	        SessionStatus sessionStatus )
	    {
	        assignmentValidator.validate( assignment, uploadedFile, result );
	        if( result.hasErrors() )
	            return assignment.isOnline() ? "assignment/online/edit"
	                : "assignment/programming/edit";

	        if( !assignment.isOnline() )
	        {
	            Resource description = assignment.getDescription();
	            if( description.getType() == ResourceType.NONE )
	                assignment.setDescription( null );
	            else if( description.getType() == ResourceType.FILE
	                && uploadedFile != null && !uploadedFile.isEmpty() )
	                description.setFile( fileIO.save( uploadedFile,
	                    SecurityUtils.getUser(), false ) );
	        }

	        assignment = assignmentDao.saveAssignment( assignment );
	        sessionStatus.setComplete();

	        logger.info( SecurityUtils.getUser().getUsername()
	            + " edited assignment " + assignment.getId() );

	        return assignment.isOnline() && request.getParameter( "next" ) != null
	            ? "redirect:/assignment/online/editQuestionSheet?assignmentId="
	                + assignment.getId() : "redirect:/section/taught#section-"
	                + assignment.getSection().getId();
	    }
		
	}


