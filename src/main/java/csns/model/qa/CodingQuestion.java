/*
 * November 14th 2008: this was written when the design called for Coding Assignments 
 * to have more than one question. Since the design is now changed so that there is
 * one coding question per coding assignment, the class is not used.
 * 
 * This file is part of the AGOPA extension of the 
 * CSNetwork Services (CSNS) project.
 * 
 * Copyright 2018, Weronika Cwir (wcwir@calstatela.edu).
 * 
 * 
 */

package csns.model.qa;

import csns.model.qa.Question;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@DiscriminatorValue("CODING")
public class CodingQuestion extends Question implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
    @Column(name = "programming_language", nullable = false)
    protected String programmingLanguage;
    
    @Column(name = "unit_test", nullable = false)
    protected String unitTestPath;

    @Column(name = "test_runs", nullable = false)
    protected int numTestRunsAllowed;

	public CodingQuestion() {
		super();
	}

	@Override
	public String getType() {
		return "CODING";
	}

	@Override
	public Answer createAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question clone() {
        CodingQuestion newQuestion = new CodingQuestion();

        newQuestion.description = description;
        newQuestion.pointValue = pointValue;
        newQuestion.programmingLanguage = programmingLanguage;
        newQuestion.unitTestPath = unitTestPath;
        newQuestion.numTestRunsAllowed = numTestRunsAllowed;

        return newQuestion;
	}
   
}
