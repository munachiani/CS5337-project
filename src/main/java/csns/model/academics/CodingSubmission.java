/*
 * This file is part of the AGOPA extension of the 
 * CSNetwork Services (CSNS) project.
 * 
 * Copyright 2018, Weronika Cwir (wcwir@calstatela.edu).
 * 
 */

package csns.model.academics;

import csns.model.academics.Submission;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue("CODING")
public class CodingSubmission extends Submission implements Serializable {

	
	private static final long serialVersionUID = 1L;

    @Column(name = "test_runs", nullable = false)
    protected int numTestRuns;
	
	public CodingSubmission() 
	{
		super();
		
		numTestRuns = 0;
	}

	public int getNumTestRuns() 
	{
		return numTestRuns;
	}

	public void setNumTestRuns( int numTestRuns ) 
	{
		this.numTestRuns = numTestRuns;
	}
	
	public void incrementTestRuns()
	{
		numTestRuns++;
	}
   
}
