/*
 * This file is part of the AGOPA extension of the 
 * CSNetwork Services (CSNS) project.
 * 
 * Copyright 2018, Weronika Cwir (wcwir@calstatela.edu).
 * 
 */


package csns.model.academics;

import csns.model.academics.Assignment;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@DiscriminatorValue("CODING")
public class CodingAssignment extends Assignment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

//	@Column(name = "p_language", nullable = false)
//    protected String programmingLanguage;
    
    @Column(name = "unit_test", nullable = false)
    protected String unitTestPath;

    @Column(name = "test_runs", nullable = false)
    protected int numTestRunsAllowed;

	public CodingAssignment() 
	{
		super();
		
        publishDate = null;
        availableAfterDueDate = false;
	}
	
    @Override
    public CodingAssignment clone()
    {
        CodingAssignment assignment = new CodingAssignment();

        assignment.name = name;
        assignment.alias = alias;
        assignment.totalPoints = totalPoints;
        assignment.dueDate = null;
        assignment.availableAfterDueDate = availableAfterDueDate;
//        assignment.programmingLanguage = programmingLanguage;
        assignment.numTestRunsAllowed = numTestRunsAllowed;
        assignment.unitTestPath = unitTestPath;

        return assignment;
    }
    
   /* public String getProgrammingLanguage() 
    {
		return programmingLanguage;
	}

	public void setProgrammingLanguage( String programmingLanguage ) 
	{
		this.programmingLanguage = programmingLanguage;
	}
*/
    @Override
    public boolean isCoding() {
    	return true;
    }
	public String getUnitTestPath() 
	{
		return unitTestPath;
	}

	public void setUnitTestPath( String unitTestPath ) 
	{
		this.unitTestPath = unitTestPath;
	}

	public int getNumTestRunsAllowed() 
	{
		return numTestRunsAllowed;
	}

	public void setNumTestRunsAllowed( int numTestRunsAllowed ) 
	{
		this.numTestRunsAllowed = numTestRunsAllowed;
	}

   
}
