package appli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Profile extends BodyType {

	private String name;
	private int weightPounds;
	private int heightFeet;
	private int heightInches;
	private int age;
	private String gender;
	private double bMI;
	private Schedule sched;
	private BodyType bodyType;
	protected ArrayList<FoodMenu> selectedFood = new ArrayList<>(); //The foods the user selected
	
	public Profile() {
		super();
		this.readInfo();
		this.getCaloriesIntake();
	}

	protected void getCaloriesIntake() {
		bodyType.setCalcCaloriesIntake(this.gender, this.toKg(), this.toCm(), this.age,0);	
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	
	public String getSched() {
		return "";
	}

	public void setSched(Schedule sched) {
		this.sched = sched;
	}

	private double toKg() {
		return this.weightPounds * 0.453592;
	}
	
	private double toCm() {
		return this.heightFeet * 30.48 + this.heightInches * 2.54;
	}
	
	private void setBMI(int pounds, int feet, int inches) {
		/*
		 * age bmi-The formula is BMI = kg/m2 where kg is a person's weight in kilograms
		 * and m2 is their height in metres squared.
		 */
		this.bMI = (0.453592 / pounds) / Math.pow(((0.3048 / feet) + (0.0254 / inches)), 2);
	}

	protected void readInfo() {
		/*
		 * get info from text file and make fill in data fields
		 */
		File fil = new File("info.txt");
		Scanner in = null;
		
		try {
			in = new Scanner (fil);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.name = in.nextLine();
		this.age = Integer.parseInt(in.nextLine());
		this.gender = in.nextLine();
		this.weightPounds = Integer.parseInt(in.nextLine());
		this.heightFeet = Integer.parseInt(in.nextLine());
		this.heightInches = Integer.parseInt(in.nextLine());
		this.sched = new Schedule(in.nextLine());
		this.bodyType = new BodyType(in.nextLine(),this.sched.sched);
		// next lines are food choices for selectedFood
		while (in.hasNextLine()) {
			String foodLine = in.nextLine();
			boolean isIn = false;
			for(int i = 0; i < this.selectedFood.size(); i++) {
				if(this.selectedFood.get(i).getNameOfFood().equals(foodLine)) {
					isIn = true;
					break;
				}
			}
			if(!isIn) {
				FoodMenu ins = new FoodMenu();
				for(int i = 0; i < ins.getMenuArr().size(); i++) {
					if(ins.getMenuArr().get(i).getNameOfFood().equals(foodLine)) {
						this.selectedFood.add(ins.getMenuArr().get(i));
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Profile [name=" + name + ", weightPounds=" + weightPounds + ", heightFeet=" + heightFeet
				+ ", heightInches=" + heightInches + ", bMI=" + bMI + ", sched=" + sched + ", bodyType=" + bodyType
				+ "]";
	}

	protected static void writeFil() {
		/*
		 * write user info into a file after they finish taking first time quiz
		 */
		File fil = new File("info.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(fil);
			fw.write("dab");
			fw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
	
	public static void main(String[] args) {
		
	}

}
