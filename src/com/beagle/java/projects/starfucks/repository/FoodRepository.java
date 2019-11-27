package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.*;


/**
 * A class of methods that read stored food data and return mainly in array form
 * Food data should not be created or updated or deleted. So there is no method about 'create', 'update' and 'delete'
 */
public class FoodRepository {

    /**
     * Method that retrieves all food data stored in text file and returns as String
     * @return (String) a String of the content stored in the text file.
     */
    public String readAllFoodData() {
        File file = new File("C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\FoodRepository.txt");
        String output ="";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                output += line;
            }
            bufferedReader.close();
            return output;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            output += String.valueOf(e);
        }
        return output;
    }


    /**
     * Method that returns food data as an array and returns the row corresponding to the input index
     * @param index
     * @return (String[]) Return an array of data about the corresponding row
     */
    public String[] readFoodRow(int index) {
        FoodRepository foodRepository = new FoodRepository();


        String foodStr = foodRepository.readAllFoodData();
        String[] foodArr = foodStr.split(";");

        String[] eachFoodArr;
        String[] eachFoodNameArr = new String[foodArr.length];


        for (int i = 0; i < foodArr.length; i++) {
            eachFoodArr = foodArr[i].split("/");
            eachFoodNameArr[i] = eachFoodArr[index];
        }

        return eachFoodNameArr;
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




    /**
     * Method to calculate total time to prepare ordered menus.
     * @param orderName
     * @param orderCount
     * @return (int) total time taken
     */
    public int calculateOrderTime(String[] orderName, int[] orderCount) {

        // variable declaration
        FoodRepository foodRepository = new FoodRepository();
        Utils utils = new Utils();
        int totalTime = 0;
        int eachCount;
        int eachTotalTime;

        // Get time info for each food from FoodRepository.txt and merge all together
        for (int i = 0; i < orderName.length; i++) {
            eachCount = orderCount[i];

            String[] findArr = foodRepository.readFoodColumn(orderName[i]);

            int eachTime = utils.StringToInt(findArr[3]);

            eachTotalTime = eachTime * eachCount;

            totalTime += eachTotalTime;
        }

        return totalTime;
    }



}
