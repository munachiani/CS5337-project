package csns.web.controller;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import csns.util.FileIO;
import csns.web.editor.CalendarPropertyEditor;



@Controller
@SessionAttributes("assignment")
public class ProgrammingAssignmentControllerS {
	
	
//	@Autowired
//    private FileIO fileIO;
//
//    private static final Logger logger = LoggerFactory.getLogger( AssignmentControllerS.class );

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( Calendar.class,
            new CalendarPropertyEditor() );
    }
	
	
	@RequestMapping(value = "assignment/programming/create", method = RequestMethod.GET)
    public String create()
    {
		
		return "programming/create";
    }

}
