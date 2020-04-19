# cs3337 be as descriptive as possible, name, date, reason, line of code.
project folder
Feb-13th file created

Feb-19th Healthy Helper started. Profile class was created with data fields of person's info. The bodyType class was also created to be the parent of every profile class. The body type class is to specify what type of routine based on a person body type. We added getters and setters in the profile class and a set BMI method. We put the BMI method in the profile constructor (String bType, String name, int weightPounds, int heightInches).

Feb-25th First two scenes of the UI were implemented. Body type quiz was started. Body type quiz is used to gather the user's data. This quiz will consist of 4 question that will determine the user's workout routine, body mass index(BMI), height and weight, and their body type goal which could be to cut, shred, or simply maintain. 

Mar-4th food class was added, with data fields for macro nutrients inorder for an array list of food to be made in future class

Mar-5th UI was updated to now have 3 scenes starting with the introduction(which is a simple login to get the user's name), scene 2 where the user is asked to input their height and weight, and scene 3 where the user is asked how frequent they exercise. 

Mar-8th the Schedule class was added for future implementation to display for the user eating routine, the info.txt text file was also added to keep track and record information form the user and write future info from the user.

Mar-11th the UI was updated, the quiz format is fully complete for front end and the start of food menu and main menu was also created.

Mar-12th The UI was updated again with working front and back end for the user's starter quiz to record their information. The foodList.txt File was also added for future information keeping of different types of food. The foodPics Folder was added to keep pictures of food.

Mar-22nd The Ui class was updated to have specific scenes for each of the body type options(shred,bulk,maintain). If the user chooses to shred he/she will be directed to a food selection scene where a list of food is displayed along with pictures displaying the food in order from top to bottom. Here the user has the option to choose which meals he/she wants to add to their diet. Once all the meals are added the user must click the "add meals" button and then he/she will be directed to the home screen of the actual app. This process goes the same for the other two options(bulk and maintain) however the food options will be different for each body type goal.

Mar-22nd The FoodMenu class was updated, with a Description String for a description of the food and a string foodPic for the File path to a picture for that particular food. The method that parses from our “foodlist” file stores every individual food in file into a FoodMenu object ArrayList. The getter for food pic allows the UI class to access the file path to a picture of the food.

Mar-27th The Ui class can now make Menu choices for each body type through a method taking a certain food, the pane, and the ordering of row it was meant to be in on the grid pane. The FoodMenu class now parses from a FoodList.csv file to fill in each data-field, and then adding them to an ArrayList of FoodMenu. The setFoodPic method will set the file path of the food to its name followed by the picture extension. Getters, Setters and toString was created for FoodMenu aswell. A layout for schedule class was created making methods for breakfast, lunch and dinner.

April-7th The Ui was updated to have a new scene to ask the user to enter their age and gender. The Ui now contains 3 menu scenes, one  for each of the body type options which are shred, bulk, and maintain. Each menu scene has a variety of meals the user can choose from to create their own personalized diet and each meal has a "view information" button where the user can click to see the macronutrients associated with each meal.

April-9th The Ui was updated to have a macronutrients window provided for each meal choice. The window displays the food picture, a desrciption of the food, and the macros associated with it such as protein, fats, carbs, and the portion size. 

April-12th The foodList.csv file was updated with more food, and foodPics file has a picture of all the new food. 

April-13th The FoodDescription file was created and contains the necessary ingredients and instructions for every food in foodList.csv

April-14th The Ui was updated to have a schedule for the user to see their choosen meals organized on a weekly basis. The schedule button on the main menu displays the times for when each meal(breakfast,lunch,snack1,snack2,dinner) should be eaten.

April-15th The schedule scene was updated to have a new layout. The scene now displays all days of the week on the top of the scene and all the times for each meal on the left hand side of the menu.

April-18th The Profile class now correctly pareses age, and gender, user selected food, also added getCaloriesIntake method passing through user info through a method in bodyType classes to get how much calories needed per day for a person depending on their info. bodyType class now has three equations from https://www.calculator.net/calorie-calculator.html the equation of mifflinStJeor, harrisBenedict, katchMcArdle, and the setCalcCaloriesIntake method taking in info from Profile classes to calculate daily intake of calories based on their bodyType, exercise routine, age, height, weight. The FoodMenu class now calculates how much protein per meal of a single serving. The Ui class now displays meals on the schedule scene and recipe for each meal when the user clicked on the meal. The Ui class now also write to info.txt age, gender, and user selected food.
April-18th The foodList.csv now has a total of 92 meals. For each of those meals, there are separate files that contain a picture of each dish, and that file is called foodPics. There is also a file that contains a picture of the needed ingredients and method to cook the meal, and the folder name is called FoodRecipes. We also implemented snacks, so we have a snack folder called snackList.csv.
