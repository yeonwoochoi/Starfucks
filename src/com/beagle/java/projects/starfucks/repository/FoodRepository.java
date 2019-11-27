package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.service.FoodService;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.*;


/**
 * A class of methods that read stored food data and return mainly in array form
 * Food data should not be created or updated or deleted. So there is no method about 'create', 'update' and 'delete'
 */
public class FoodRepository {

    FoodService foodService = new FoodService();
    Utils utils = new Utils();

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
     * Method to calculate waiting time to prepare ordered menus.
     * @param orderName
     * @param orderCount
     * @return (int) waiting time taken
     */
    public int calculateOrderTime(String[] orderName, int[] orderCount) {

        // variable declaration

        int waitingTime = 0;
        int eachCount;
        int eachWaitingTimeStr;

        // Get time info for each food from FoodRepository.txt and merge all together
        for (int i = 0; i < orderName.length; i++) {
            eachCount = orderCount[i];

            String[] findArr = foodService.readFoodColumn(orderName[i]);

            int eachTime = utils.StringToInt(findArr[3]);

            eachWaitingTimeStr = eachTime * eachCount;

            waitingTime += eachWaitingTimeStr;
        }

        return waitingTime;
    }



}
