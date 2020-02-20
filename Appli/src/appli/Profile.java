package profile;

public class Profile extends BodyType{

	private String name;
	private int weightPounds;
	private int heightFeet;
	private int heightInches;
	private double bMI;
	private BodyType bodyType;
	
	

	public Profile(String bType, String name, int weightPounds, int heightInches) {
		super(bType);
		/*
		 * 
		 */
		this.setBMI();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(int weightPounds) {
		this.weightPounds = weightPounds;
	}

	public int getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(int heightFeet) {
		this.heightFeet = heightFeet;
	}

	public int getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}
	/*
	 * age
	 *bmi-The formula is BMI = kg/m2 where kg is a person's weight in kilograms 
	 *and m2 is their height in metres squared.
	 *
	 *
	 * 
	 */
	private void setBMI() {
		this.bMI = (0.453592/this.weightPounds) / Math.pow(((0.3048/this.heightFeet) + (0.0254/this.heightInches)),2);

	}








}
