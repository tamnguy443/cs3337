package appli;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodMenu {

	String description;
	String nameOfFood;
	int carbs;
	int protein; //every gram of protein and crabs is 4 calories
	int fat;
	double portionSize;
	String foodPic;
	
	//Food menu
	protected ArrayList<FoodMenu> menuArr = parseFromList();

	public FoodMenu(String nameOfFood, int carbs, int protein, int fat, double portionSize, String description) {
		super();
		this.nameOfFood = nameOfFood;
		this.description = description;
	}

	public void main(String[] args) {

	}

	//read from foodList.csv
	@SuppressWarnings("null")
	public ArrayList<FoodMenu> parseFromList() {
		//import files
		String file = "foodList.csv";
		ArrayList<FoodMenu> list = null;

		File inFile = new File(file);
		Scanner in = null;
		
		//try catch for reading the file
		try {
			in = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//parse FOOLIST.CSV into FoodMenu arraylist
		while (in.hasNextLine()) {
			String nextLine = in.nextLine();
			String[] token = nextLine.split(",");

			//add all the food in the list to menuArr
			if (nextLine.length() > 0) {
				FoodMenu food = new FoodMenu(token[0], Integer.parseInt(token[1]),
						Integer.parseInt(token[2]), Integer.parseInt(token[3]),
						Double.parseDouble(token[3]), "description");
				list.add(food);
			}
		}
		return list;
	}

	public String getFoodPic() {
		return "\\foodPics\\" + this.foodPic + ".jpg";
	}

	public void setFoodPic(String foodPic) {
		this.foodPic = foodPic;
	}
}

