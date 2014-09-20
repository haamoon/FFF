package edu.emory.fff.parser.foodparser;

import edu.emory.fff.parser.Event;

public class FoodEvent extends Event {
	private String foodType;

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	
}
