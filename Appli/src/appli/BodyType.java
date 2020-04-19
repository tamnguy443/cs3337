package appli;


public class BodyType {
	private double bType;
	private double CaloricIntake; //2000.0 default
	// bulk
	// shredding
	// maintain
	// data field of routein is different based on costructors output
	// 100% to maintain, shred 87%, bulk 113%
	//1-3 times a week = 1.37505, 4-5 = 1.465, 6-everyday = 1.5503
	protected BodyType(String bType, String sched) {
		if (bType.equals("bulk")) {
			this.bType = 1.13;
		} else if (bType.equals("shred")) {
			this.bType = 0.87;
		} else if (bType.equals("maintain")) {
			this.bType = 1;
		}

		if (sched.equals("1-3")) {
			this.CaloricIntake = 1.37505;
		} else if (bType.equals("4-5")) {
			this.CaloricIntake = 1.465;
		} else if (bType.equals("6-e")) {
			this.CaloricIntake = 1.5503;
		}
		
	}
	
	protected BodyType() {
		
	}
	
	private double mifflinStJeor(String gender, double w, double h, double a, double f) {
		if(gender.equals("m")) {
			return (10*w) + (6.25*h) - (5*a) + 5;
		} else {
			return (10*w) + (6.25*h) - (5*a) -161;
		}
	}
	
	private double harrisBenedict(String gender, double w, double h, double a, double f) {
		if(gender.equals("m")) {
			return (13.397*w) + (4.799*h) - (5.677*a) + 88.362;
		} else {
			return (9.247*w) + (3.098*h) - (4.330*a) + 447.593;
		}
	}
	
	private double katchMcArdle(String gender, double w, double h, double a, double f) {
		return 370 + 21.6*(1 - f)*w;
	}
	
	protected double setCalcCaloriesIntake(String gender, double w, double h, double a, double f) {
		//getCaloriesIntake in profile class
		return this.mifflinStJeor(gender, w, h, a, f) * this.CaloricIntake;	
	}
	
	protected double getbType() {
		return this.bType;
	}
	
	protected void setbType(double bType) {
		this.bType = bType;
	}
	
}
