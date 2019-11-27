package com.beagle.java.projects.starfucks.service;


import com.beagle.java.projects.starfucks.repository.BaristaRepository;
import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * update BaristaRepository.txt when order is accepted or is finished
 */

public class BaristaService {

    BaristaRepository baristaRepository = new BaristaRepository();
    Utils utils = new Utils();

    /**
     * A method that returns the barista number to execute the order received when creating a new Order.
     * @return (int) Barista number to fulfill the received order
     */
    public int giveOrderToBarista() {

        // variable declaration
        String output;


        // Declared with the last declared barista String array
        String[] objects = baristaRepository.readAllBaristaData();
        String lastStr = objects[objects.length-1];
        String[] lastArr = lastStr.split("/");

        int lastArrCount = utils.StringToInt(lastArr[1]);
        if (lastArrCount >= 10) {
            // When you're doing 10 things until the last barista

            output = baristaRepository.createNewBarista();
        } else {
            // When there is a barista who can still afford

            output = baristaRepository.updateBarista();
        }
        int outputInt = utils.StringToInt(output);
        return outputInt;
    }


    /**
     * substrate order count of barista in BaristaRepository.txt
     * @param baristaIndexStr
     * @return (boolean) success
     */
    public boolean reduceOrderCount(String baristaIndexStr) {


        // read All Barista Data from BaristaRepository.txt
        String[] strArr = baristaRepository.readAllBaristaData();
        String[] eachArr;
        String[] eachArr2;
        String eachStr = "";
        String indexStr = "";
        String countStr = "";
        String isWorkingStr = "";


        // find barista data that should be updated by baristaIndex
        int baristaIndex;
        for (int i = 0; i < strArr.length; i++) {
            eachArr = strArr[i].split("/");
            baristaIndex = utils.StringToInt(baristaIndexStr);
            if (eachArr[0].equals(utils.intToString(baristaIndex))) {
                eachStr = strArr[i];
            }
        }
        eachArr2 = eachStr.split("/");
        indexStr = eachArr2[0];
        countStr = eachArr2[1];
        isWorkingStr = eachArr2[2];


        // update orderCount
        int index = utils.StringToInt(indexStr);
        int count = utils.StringToInt(countStr);

        String inputIndex = "";
        String inputCount = "";
        String inputIsWorking = "";
        if (count > 1) {
            inputIndex = utils.intToString(index);
            inputCount = utils.intToString(count-1);
            inputIsWorking = "1";
        } else if (count == 1) {
            inputIndex = utils.intToString(index);
            inputCount = utils.intToString(count-1);
            inputIsWorking = "0";
        }

        // update BaristaRepository.txt
        String oldStr3 = indexStr + "/" + countStr + "/" + isWorkingStr + ";";
        String newStr3 = inputIndex + "/" + inputCount + "/" + inputIsWorking + ";";
        boolean success = baristaRepository.updateBaristaRepository(oldStr3, newStr3);


        return success;
    }



    /**
     * Method that returns barista data as an array and returns the row corresponding to the input index
     * @param index
     * @return (String[]) Return an array of data about the corresponding row
     */
    public String[] readBaristaRow(int index) {

        String[] baristaArr = baristaRepository.readAllBaristaData();

        String[] eachBaristaArr;
        String[] eachBaristaRowArr = new String[baristaArr.length];


        for (int i = 0; i < baristaArr.length; i++) {
            eachBaristaArr = baristaArr[i].split("/");
            eachBaristaRowArr[i] = eachBaristaArr[index];
        }

        return eachBaristaRowArr;
    }



    /**
     * Method to read data corresponding to input data through file path
     * @param content
     * @return (String[]) Return an array of barista data for the corresponding column
     */
    public String[] readBaristaColumn(String content) {
        String filePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\BaristaRepository.txt";
        String output = "";
        String[] outputArray = new String[3];
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
