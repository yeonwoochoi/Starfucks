package com.beagle.java.projects.starfucks;

import com.beagle.java.projects.starfucks.controller.BaristaController;
import com.beagle.java.projects.starfucks.controller.FoodController;
import com.beagle.java.projects.starfucks.controller.UserController;

import com.beagle.java.projects.starfucks.utils.Utils;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Required method preload
        Scanner scanner = new Scanner(System.in);


        // classes related controller
        BaristaController baristaController = new BaristaController();
        FoodController foodController = new FoodController();
        UserController userController = new UserController();


        // class related utils
        Utils utils = new Utils();



        // main function
        boolean run = true;

        while (run) {

            // show process list
            Main main = new Main();
            System.out.println(main.printMenu());
            int inputInt = scanner.nextInt();

            if (inputInt == 1) {

                String[] orderName;
                String[] orderPrice;
                String[] orderCount;


                String eachFoodName = "";
                String eachFoodPrice = "";
                String eachFoodCount = "";


                // get all orders
                boolean run2 = true;

                while (run2) {

                    boolean run3 = true;

                    // get each order
                    while (run3) {

                        boolean run4 = true;
                        boolean isMatch = false;

                        // get food number and check input number is between 1 and 20.
                        while (run4) {

                            System.out.println("== 메뉴 ==\n" +
                                    foodController.showMenuList() +
                                    "음식 번호 >> ");
                            String inputOrderStr = scanner.next();
                            boolean isCorrectFoodNumber = foodController.checkInputFoodNumber(inputOrderStr);
                            if (isCorrectFoodNumber) {

                                // set each food name and each food price
                                String[] foodDataArr = foodController.findFoodData(inputOrderStr);
                                eachFoodName += foodDataArr[0] + "/";
                                eachFoodPrice += foodDataArr[1] + "/";
                                isMatch = true;
                                run4 = false;

                            } else {
                                System.out.println("1에서 20사이의 숫자를 입력해주세요.\n음식 번호 >> ");
                            }

                        }

                        // after getting correct food number, get quantity of food
                        if (isMatch) {
                            // Enter quantity and check adequacy
                            System.out.println("\n주문 수량 >> ");

                            String inputOrderCount = scanner.next();
                            boolean isCorrectQuantity = foodController.checkInputQuantity(inputOrderCount);

                            // check Correct quantity
                            boolean run5 = true;
                            while (run5) {
                                if (isCorrectQuantity) {

                                    // set each food quantity
                                    eachFoodCount += inputOrderCount + "/";
                                    run5 = false;

                                } else {
                                    System.out.println("잘못된 수량입니다.");
                                }
                            }
                            run3 = false;
                        }
                    }


                    // determine whether getting more orders or not
                    boolean run6 = true;
                    while (run6) {
                        System.out.println("1. 주문 계속하기\n" +
                                "2. 그만하기\n" +
                                "숫자 입력 >> ");
                        int getMoreOrders = scanner.nextInt();
                        if (getMoreOrders == 2) {
                            run2 = false;
                            run6 = false;
                        } else if (getMoreOrders == 1) {
                            run2 = true;
                            run6 = false;
                        } else {
                            System.out.println("1,2 중 하나만 입력해 주십시오.");
                            run6 = true;
                        }
                    }
                }
                // Save orders received
                orderName = utils.removeNullValue(eachFoodName.split("/"));
                orderPrice = utils.removeNullValue(eachFoodPrice.split("/"));
                orderCount = utils.removeNullValue(eachFoodCount.split("/"));


                // result of creating order
                String[] createOrderResult = userController.getOrder(orderName, orderPrice, orderCount);

                String orderNumberStr = createOrderResult[0];
                String waitingTimeStr = createOrderResult[1];
                String receipt = createOrderResult[2];

                // give orders to barista
                String baristaIndex = baristaController.getOrder(waitingTimeStr);

                // receipt
                System.out.println("== 영수증 ==\n\n" +
                        receipt);



            } else if (inputInt == 2) {

                // Show Menu list
                System.out.println("== 메뉴 ==\n");
                System.out.println(foodController.showMenuList() + "\n");


            } else if (inputInt == 3) {

                // Show order history in progress
                System.out.println("== 주문 목록 ==\n");
                System.out.println(userController.showOrderList() + "\n");


            } else if (inputInt == 4) {

                // When customer leave the cafe, the data is deleted from the CustomerRepository.txt.
                System.out.println("== 카페 나가기 ==\n" +
                        "주문 번호 >> ");
                String orderNumber = scanner.next();
                int checkUserLocation = userController.checkUserLocation(orderNumber);
                if (checkUserLocation == 0) {
                    boolean run2 = true;
                    while (run2) {

                        System.out.println("아직 주문한 음료가 나오지 않았습니다. 그래도 나가시겠습니까?\n" +
                                "1. 나간다\n" +
                                "2. 기다린다\n" + "숫자 선택 >> ");
                        int inputNumber = scanner.nextInt();
                        if (inputNumber == 1) {
                            run2 = false;
                            System.out.println("안녕히 가십시오\n");
                            userController.leavingCafe(orderNumber);
                        } else if (inputNumber == 2) {
                            run2 = false;
                        } else {
                            System.out.println("1,2 중 하나만 입력해 주십시오.");
                        }
                    }
                } else if (checkUserLocation == 1) {
                    System.out.println("안녕히 가십시오\n");
                    userController.leavingCafe(orderNumber);
                } else {
                    System.out.println("이미 나가셨습니다.");
                }
            } else if (inputInt == 5) {
                System.out.println("== 프로그램 종료 ==\n");
                run = false;
            } else {
                System.out.println("1,2,3,4,5 중 하나만 입력하시요.");
            }
        }
    }


    public String printMenu() {
        String str;
        str = "== Welcome to StarFucks ==\n" +
                "1. 주문하기\n" +
                "2. 메뉴 보기\n" +
                "3. 주문 목록 보기\n" +
                "4. 카페 나가기\n" +
                "5. 프로그램 종료\n" +
                "---------------------------------\n" +
                "입력 >> ";

        return str;
    }
}
