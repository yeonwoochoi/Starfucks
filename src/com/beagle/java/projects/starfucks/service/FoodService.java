package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.repository.FoodRepository;

import java.io.*;


/**
 * class with method to read food data in foodRepository.txt
 */

public class FoodService {

    FoodRepository foodRepository = new FoodRepository();

    /**
     * Method that returns all data in FoodRepository.txt as string
     * @return String menu
     */
    public String showMenuList() {

        String beverageStr = "== beverage ==\n\n";
        String dessertStr = "== dessert ==\n\n";

        String foodStr = foodRepository.readAllFoodData();
        String[] foodArr = foodStr.split(";");
        String[] beverageArr;
        String[] dessertArr;
        for (int i = 0 ; i < 10; i++) {
            beverageArr = foodArr[i].split("/");
            for (int j = 0 ; j < beverageArr.length; j++) {
                beverageStr += beverageArr[j] + " ";
            }
            beverageStr += "\n";
        }
        beverageStr += "\n\n";

        for (int i = 10; i < foodArr.length; i++) {
            dessertArr = foodArr[i].split("/");
            for (int j = 0 ; j < dessertArr.length; j++) {
                dessertStr += dessertArr[j] + " ";
            }
            dessertStr += "\n";
        }
        dessertStr += "\n\n";


        String output = beverageStr + dessertStr;

        return output;
    }


    /**
     * Method that returns food data as an array and returns the row corresponding to the input index
     * @param index
     * @return (String[]) Return an array of data about the corresponding row
     */
    public String[] readFoodRow(int index) {

        String foodStr = foodRepository.readAllFoodData();
        String[] foodArr = foodStr.split(";");

        String[] eachFoodArr;
        String[] eachFoodRowArr = new String[foodArr.length];


        for (int i = 0; i < foodArr.length; i++) {
            eachFoodArr = foodArr[i].split("/");
            eachFoodRowArr[i] = eachFoodArr[index];
        }

        return eachFoodRowArr;
    }

    /**
     * Method to read data corresponding to input data through file path
     * @param content
     * @return (String[]) Return an array of food data for the corresponding column
     */
    public String[] readFoodColumn(String content) {
        String filePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\FoodRepository.txt";
        String output = "";
        String[] outputArray = new String[4];
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            String[] stringArr;

            while ((line = bufferedReader.readLine()) != null) {
                stringArr = line.split(";");
                for (int i = 0 ; i < stringArr.length; i++) {
                    if (stringArr[i].contains(content)) {
                        output += stringArr[i];
                    }
                }
            }
            bufferedReader.close();

            outputArray = output.split("/");


            return outputArray;
        } catch (FileNotFoundException e) {
            outputArray[0] = String.valueOf(e);
        } catch (IOException e) {
            outputArray[0] = String.valueOf(e);
        }
        return outputArray;
    }

}
