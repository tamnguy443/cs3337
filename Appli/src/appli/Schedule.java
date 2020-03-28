package appli;

public class Schedule {
	private String sched;
	private boolean dayOff = false;
	private FoodMenu menu;

	public Schedule(String sched) {
		this.sched = sched;
	}

	/*
	 * Monday to Saturday, Sunday off. 3 meals per day
	 */
	protected FoodMenu breakfast() {
		if (!this.dayOff) {
			return menu;
		} else {
			return menu;
		}
	}

	protected FoodMenu lunch() {
		if (!this.dayOff) {
			return menu;
		} else {
			return menu;
		}
		
	}

	protected FoodMenu dinner() {
		if (!this.dayOff) {
			return menu;
		} else {
			return menu;
		}
		
	}

	protected void dayOff() {
		dayOff = true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

