/*
 * This file is part of the AGOPA extension of the 
 * CSNetwork Services (CSNS) project.
 * 
 * by Weronika Cwir (wcwir@calstatela.edu).
 * 
 */
package csns.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import csns.model.academics.CodingAssignment;
import csns.model.academics.CodingSubmission;
import csns.model.academics.dao.AssignmentDao;
import csns.model.academics.dao.SubmissionDao;
import csns.model.core.User;
import csns.security.SecurityUtils;

@Controller
public class CodingSubmissionController extends SubmissionController {
	
	@Autowired
    private AssignmentDao assignmentDao;

    @Autowired
    private SubmissionDao submissionDao;
    
    private static final Logger logger = LoggerFactory.getLogger( CodingSubmissionController.class );

    @RequestMapping("/submission/coding/view")
    public String view( @RequestParam Long assignmentId, ModelMap models )
    {
        CodingAssignment assignment = (CodingAssignment) assignmentDao.getAssignment( assignmentId );
        if( !assignment.isPastDue() )
            return "redirect:/submission/coding/edit?assignmentId="
                + assignmentId;

        User student = SecurityUtils.getUser();
        CodingSubmission submission = (CodingSubmission) submissionDao.getSubmission(
            student, assignment );
        if( submission != null && !submission.isPastDue() )
            return "redirect:/submission/coding/edit?assignmentId="
                + assignmentId;

        if( submission == null )
        {
            submission = new CodingSubmission( );
            submission = (CodingSubmission) submissionDao.saveSubmission( submission );
        }

        models.put( "submission", submission );
        return "submission/coding/view";
    }

}
