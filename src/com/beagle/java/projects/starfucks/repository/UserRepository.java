package com.beagle.java.projects.starfucks.repository;


import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.*;

/**
 * Class consisting of methods that CRUD data in customerRepository.txt based on data from User Service
 */
public class UserRepository {

    UserRepository userRepository = new UserRepository();
    Utils utils = new Utils();

    /**
     * Method to store input data in CustomerRepository
     * @param inputStr
     * @return (boolean) success
     */
    public boolean saveToCustomerRegistory(String inputStr) {
        File file = new File("C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt");
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
    public String[] readAllUserData() {
        File file = new File("C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt");
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
     * update Customer Data in CustomerRegistory.txt when customer received food.
     * @param oldData
     * @return (boolean) success
     */
    public boolean updateCustomerRegistory(String oldData, String newData) {
        String filePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\customerRepositorys.txt";
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



    /**
     * Method to get order number from OrderNumberRepository.txt and return
     * @return (String) order number.
     */
    public String givingOrderNumber() {
        File file = new File("C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\OrderNumberRepository.txt");
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
     * Method to update order number from OrderNumberRepository.txt and return success
     * @return (boolean) success
     */
    public boolean updateOrderNumber() {
        String orderNumber = userRepository.givingOrderNumber();
        String newOrderNumber = utils.intToString(utils.StringToInt(orderNumber) + 1);

        String filePath = "C:\\Users\\최연우\\IdeaProjects\\Starfucks\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\OrderNumberRepository.txt";
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
                    if (line.contains(orderNumber)) {
                        line = line.replace(orderNumber, newOrderNumber);
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
