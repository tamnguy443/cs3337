package appli;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodMenu {

	protected String description;
	protected String nameOfFood;
	protected int carbs;
	protected int protein; // every gram of protein and crabs is 4 calories
	protected double fat;
	protected double portionSize;
	protected String foodPic;

	// Food menu
	protected ArrayList<FoodMenu> menuArr;

	public FoodMenu() {
		menuArr = new ArrayList<>();
		this.parseFromList();
	}

	public FoodMenu(String nameOfFood, int carbs, int protein, double fat, double portionSize, String description) {
		this.description = description;
		this.nameOfFood = nameOfFood;
		this.carbs = carbs;
		this.protein = protein;
		this.fat = fat;
		this.portionSize = portionSize;
	}

	public void main(String[] args) {

	}

	// read from foodList.csv
	public void parseFromList() {
		// import files
		File inFile = new File("foodList.csv");
		Scanner in = null;

		// try catch for reading the file
		try {
			in = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// parse FOOLIST.CSV into FoodMenu arraylist
		while (in.hasNextLine()) {
			String nextLine = in.nextLine();
			String[] token = nextLine.split(",");

			// add all the food in the list to menuArr
			if (nextLine.length() > 0) {
				FoodMenu food = new FoodMenu(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2]),
						Double.parseDouble(token[3]), Double.parseDouble(token[3]), "description");
				this.menuArr.add(food);
			}
		}
		in.close();
	}
	
	public ArrayList<FoodMenu> getMenuArr() {
		return menuArr;
	}

	public void setMenuArr(ArrayList<FoodMenu> menuArr) {
		this.menuArr = menuArr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameOfFood() {
		return nameOfFood;
	}

	public void setNameOfFood(String nameOfFood) {
		this.nameOfFood = nameOfFood;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public double getPortionSize() {
		return portionSize;
	}

	public void setPortionSize(double portionSize) {
		this.portionSize = portionSize;
	}

	public String getFoodPic() {
		return "foodPics\\\\" + this.nameOfFood.replaceAll("\\s+","") + ".jpg";
	}

	public void setFoodPic(String foodPic) {
		this.foodPic = foodPic;
	}

	@Override
	public String toString() {
		return "FoodMenu [description=" + description + ", nameOfFood=" + nameOfFood + ", carbs=" + carbs + ", protein="
				+ protein + ", fat=" + fat + ", portionSize=" + portionSize + ", foodPic=" + foodPic + ", menuArr="
				+ menuArr + "]";
	}

}
