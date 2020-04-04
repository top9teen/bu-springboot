package com.it.app.model;

public class ChoiceModel {


	private String choiceName;
	private String choiceCriterion;



	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public String getChoiceCriterion() {
		return choiceCriterion;
	}

	public void setChoiceCriterion(String choiceCriterion) {
		this.choiceCriterion = choiceCriterion;
	}

	@Override
	public String toString() {
		return "ChoiceModel [choiceName=" + choiceName + ", choiceCriterion=" + choiceCriterion + "]";
	}

	
	
}
