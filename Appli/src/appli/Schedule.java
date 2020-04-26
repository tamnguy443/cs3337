package appli;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

public class Schedule {
	protected String sched;
	private boolean dayOff = false;
	protected ArrayList<FoodMenu> userSelectedFood;
	protected List<List<FoodMenu>> days;

	public Schedule( ArrayList<FoodMenu> userSelectedFood) {
		this.userSelectedFood = userSelectedFood;
		days = new ArrayList<>();
	}

	public Schedule(String a) {
		this.sched = a;
	}
	
	public Schedule() {

	}

	/*
	 * Monday to Saturday, Sunday off. 3 meals per day snack1 and snack2 breakfast
	 * 8am - 8:30pm lunch: 12:30, snacksOne 3:00pm, snackTwo 5:30
	 */
	
	protected void isDayOff() {
		dayOff = true;
	}
	
	protected void rotateMeal() {
		FoodMenu last = this.userSelectedFood.get(this.userSelectedFood.size() - 1);
		
		for (int i = this.userSelectedFood.size() - 1; i > 0 ; i--) {
			this.userSelectedFood.set(i, this.userSelectedFood.get(i - 1));
		}
		this.userSelectedFood.set(0, last);
	}
}
