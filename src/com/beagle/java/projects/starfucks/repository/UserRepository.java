package com.beagle.java.projects.starfucks.repository;

import java.io.*;

public class UserRepository {

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


}
