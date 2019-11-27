package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.*;


/**
 * Class consisting of methods that CRUD data in baristaRepository.txt based on data from Barista Service
 */
public class BaristaRepository {

    BaristaRepository baristaRepository = new BaristaRepository();
    Utils utils = new Utils();



    /**
     * A method that calls a new barista when all baristas are performing 10 orders.
     * @return (String) Barista index assigned new order
     */
    public String createNewBarista() {

        // variable declaration

        // get all barista info as string array
        String[] strArr = baristaRepository.readAllBaristaData();

        // Get index of last barista
        String lastStr = strArr[strArr.length - 1];
        String[] newLastArrStr = lastStr.split("/");
        int[] newLastArr = utils.StringArrayToIntArray(newLastArrStr);

        // The index of the barista to be created
        int addIndexInt = newLastArr[0] + 1;
        String addIndexStr = utils.intToString(addIndexInt);


        // return the index of the barista to be created
        String input = addIndexStr + "/1/1;";
        if (baristaRepository.saveToBaristaRepository(input)) {
            return utils.intToString(addIndexInt);
        } else {
            return "";
        }
    }


    /**
     * Method to store input data in BaristaRepository
     * @param inputStr
     * @return (boolean) success
     */
    public boolean saveToBaristaRepository(String inputStr) {
        File file = new File("C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\BaristaRepository.txt");
        boolean success;

        try {
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fWriter);

            if (file.isFile() && file.canWrite()) {
                bufferedWriter.write(inputStr);
                bufferedWriter.close();
                success = true;
            } else {
                success = false;
            }


        } catch (FileNotFoundException e) {
            success = false;
        } catch (IOException e) {
            success = false;
        }
        return success;
    }



    /**
     * Method that retrieves data stored in text file and returns as String
     * @return (String) a String of the content stored in the text file.
     */
    public String[] readAllBaristaData() {
        File file = new File("C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\BaristaRepository.txt");
        String output ="";
        String[] outputArr = new String[100];
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                output += line;
            }
            bufferedReader.close();
            outputArr = output.split(";");
            return outputArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputArr;
    }







    /**
     * assigns an order to that barista when there is a free barista
     * @return (String) Barista index assigned new order
     */
    public String updateBarista() {


        // get all barista info as string array
        String[] strArr = baristaRepository.readAllBaristaData();

        // variables declaration
        String[] eachArrStr;
        int[] eachArr;

        int newCount = 0;
        int oldCount = 0;
        String oldCountStr = "";
        String newCountStr = "";

        String oldWorkingStr = "";
        String newWorkingStr = "";

        String newIndexStr = "";


        int i = 0;
        boolean run = true;
        while (i < strArr.length && run) {

            // Declare each barista info as an array of strings
            eachArrStr = strArr[i].split("/");
            eachArr = utils.StringArrayToIntArray(eachArrStr);

            // Each barista has less than 10 orders
            if (eachArr[1] < 10 && eachArr[1] >= 0) {

                // Update the number of things doing
                oldCount = eachArr[1];
                newCount = eachArr[1] + 1;
                oldCountStr = utils.intToString(oldCount);
                newCountStr = utils.intToString(newCount);


                // Changing the value of isWorking
                if (eachArr[2] == 0) {
                    oldWorkingStr = "0";
                    newWorkingStr = "1";
                } else {
                    oldWorkingStr = "1";
                    newWorkingStr = "1";
                }

                // terminate while statement to update
                run = false;

                // index of updated barista
                newIndexStr = utils.intToString(eachArr[0]);
            } else {
                i++;
            }
        }

        // Create new data and old data
        String oldData = newIndexStr + "/" + oldCountStr + "/" + oldWorkingStr + ";";
        String newData = newIndexStr + "/" + newCountStr + "/" + newWorkingStr + ";";


        // update data in BaristaRepository.txt
        baristaRepository.updateBaristaRepository(oldData, newData);


        // return the index of the barista to be updated
        return newIndexStr;
    }






    /**
     * Method to modify file data corresponding to file path
     * @param oldData
     * @param newData
     * @return (boolean) success
     */
    public boolean updateBaristaRepository(String oldData, String newData) {
        String filePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\BaristaRepository.txt";
        String newFilePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\pseudoRepository.txt";
        File pseudoFile = new File(newFilePath);
        boolean success = false;

        try {
            if (pseudoFile.createNewFile()) {
                success = true;
            }
        } catch (IOException e) {
            success = false;
        }  catch (Exception e) {
            success = false;
        }

        if (success) {
            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                br = new BufferedReader(new FileReader(filePath));
                bw = new BufferedWriter(new FileWriter(newFilePath));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(oldData)) {
                        line = line.replace(oldData, newData);
                    }
                    bw.write(line);
                }
                success = true;

            }  catch (Exception e) {
                success = false;
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    success = false;
                }
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    success = false;
                }
            }
        }

        File oldFile = new File(filePath);
        File newFile = new File(newFilePath);

        oldFile.delete();
        newFile.renameTo(oldFile);
        return success;
    }



}
