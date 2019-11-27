package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.utils.Utils;

import java.io.*;

public class OrderRepository {

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
        OrderRepository orderRepository = new OrderRepository();
        Utils utils = new Utils();
        String orderNumber = orderRepository.givingOrderNumber();
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
