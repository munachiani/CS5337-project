package csns.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import csns.model.academics.Assignment;
import csns.model.academics.dao.AssignmentDao;
import csns.model.academics.dao.SectionDao;
import csns.security.SecurityUtils;

@Controller
public class CodingAssignmentController {
	
	 @Autowired
	    private SectionDao sectionDao;

	    @Autowired
	    private AssignmentDao assignmentDao;

	    private static final Logger logger = LoggerFactory.getLogger( CodingAssignmentController.class );

	    @RequestMapping("/assignment/programming/view")
	    public String view( @RequestParam Long id,
	        @RequestParam(required = false) Integer sectionIndex, ModelMap models )
	    {
	        models.put( "assignment", assignmentDao.getAssignment( id ) );
	        models.put( "sectionIndex", sectionIndex != null ? sectionIndex : 0 );
	        return "assignment/programming/view";
	    }
	    
	    @RequestMapping("/assignment/programming/delete")
	    public String delete( @RequestParam Long id )
	    {
	        Assignment assignment = assignmentDao.getAssignment( id );
	        assignment.setDeleted( true );
	        assignment = assignmentDao.saveAssignment( assignment );

	        logger.info( SecurityUtils.getUser().getUsername()
	            + " deleted assignment " + assignment.getId() );

	        return "redirect:/section/taught#section-"
	            + assignment.getSection().getId();
	    }

}
