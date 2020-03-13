package appli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Profile extends BodyType {

	private String name;
	private int weightPounds;
	private int heightFeet;
	private int heightInches;
	private double bMI;
	private Schedule sched;
	private BodyType bodyType;

	private Profile(String bType, String name, String weightPounds, String heightInches, String heigthFeet, Schedule sched) {
		super(bType);
		this.name = name;
		this.sched = sched;
		this.setBMI(Integer.parseInt(weightPounds), Integer.parseInt(heightInches), Integer.parseInt(heigthFeet));
	}
	
	public Profile() {
		super();
		this.readInfo();
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
	
	public Schedule getSched() {
		return sched;
	}

	public void setSched(Schedule sched) {
		this.sched = sched;
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
		this.weightPounds = Integer.parseInt(in.nextLine());
		this.heightFeet = Integer.parseInt(in.nextLine());
//		this.heightInches = Integer.parseInt(in.nextLine());
		this.sched = new Schedule(in.nextLine());
		this.bodyType = new BodyType(in.nextLine());
	
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
