package com.CITB408_2021;

import com.CITB408_2021.Exeptions.NotEnoughMoneyToPay;
import com.CITB408_2021.Exeptions.NotEnoughNumberOfGoods;
import com.CITB408_2021.Goods.Goods;
import com.CITB408_2021.Goods.GoodsCategory;
import com.CITB408_2021.Goods.GoodsList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args)  {

        Shop shop1 = new Shop("Shop-1", 7, 5);

        // Markup determination
        ShopGoodsCategory markup1 = new ShopGoodsCategory(GoodsCategory.NUTRITIONAL, 100);
        ShopGoodsCategory markup2 = new ShopGoodsCategory(GoodsCategory.NON_NUTRITIONAL, 50);

        shop1.addGoodsCategoryMarkup(markup1);
        shop1.addGoodsCategoryMarkup(markup2);

        // add Cashiers
        Cashier kasierIvan = new Cashier("Ivan", BigDecimal.valueOf(100));
        Cashier kasierMaria = new Cashier("Maria", BigDecimal.valueOf(100));

        shop1.addCashier(kasierIvan);
        shop1.addCashier(kasierMaria);

        CashRegister kasa1 = new CashRegister(shop1, kasierIvan);
        CashRegister kasa2 = new CashRegister(shop1, kasierMaria);

        // Goods
        Goods g1 = new Goods("Tomato", BigDecimal.valueOf(1.50), GoodsCategory.NUTRITIONAL, LocalDate.of(2023, 7,25));
        Goods g2 = new Goods("Onion", BigDecimal.valueOf(0.70), GoodsCategory.NUTRITIONAL, LocalDate.of(2022, 7,20));
        Goods g3 = new Goods("Cucumber", BigDecimal.valueOf(1), GoodsCategory.NUTRITIONAL, LocalDate.of(2023, 8,10));
        Goods g4 = new Goods("Pepper", BigDecimal.valueOf(2.20), GoodsCategory.NUTRITIONAL, LocalDate.of(2023, 9,10));
        Goods g5 = new Goods("Shoes", BigDecimal.valueOf(29.50), GoodsCategory.NON_NUTRITIONAL, LocalDate.of(2022, 9,10));
        Goods g6 = new Goods("Jacket", BigDecimal.valueOf(80.20), GoodsCategory.NON_NUTRITIONAL, LocalDate.of(2023, 9,10));

        // Deliveries
        shop1.deliveryToShop(g1, BigDecimal.valueOf(50) );
        shop1.deliveryToShop(g1, BigDecimal.valueOf(65) );
        shop1.deliveryToShop(g2, BigDecimal.valueOf(60) );
        shop1.deliveryToShop(g1, BigDecimal.valueOf(30) );
        shop1.deliveryToShop(g3, BigDecimal.valueOf(30) );
        shop1.deliveryToShop(g4, BigDecimal.valueOf(75) );
        shop1.deliveryToShop(g5, BigDecimal.valueOf(15) );
        shop1.deliveryToShop(g6, BigDecimal.valueOf(10) );


        // Client creation
        Client client1 = new Client("Test Client1", BigDecimal.valueOf(500));
        Client client2 = new Client("Test Client2", BigDecimal.valueOf(500));
        Client client3 = new Client("Test Client3", BigDecimal.valueOf(200+150));
        Client client4 = new Client("Test Client4", BigDecimal.valueOf(200+100));
        Client client5 = new Client("Test Client5", BigDecimal.valueOf(2000));


        // Shopping list
        GoodsList sl1 = new GoodsList(g1, BigDecimal.valueOf(15));
        GoodsList sl2 = new GoodsList(g2, BigDecimal.valueOf(10));
        GoodsList sl3 = new GoodsList(g3, BigDecimal.valueOf(5));
        GoodsList sl4 = new GoodsList(g4, BigDecimal.valueOf(20));
        GoodsList sl5 = new GoodsList(g1, BigDecimal.valueOf(100));
        GoodsList sl6 = new GoodsList(g5, BigDecimal.valueOf(2));
        GoodsList sl7 = new GoodsList(g6, BigDecimal.valueOf(1));
        GoodsList sl8 = new GoodsList(g6, BigDecimal.valueOf(8));

       // List<GoodsList> shopingList1 = Arrays.asList(sl1, sl1, sl2, sl2, sl3);
       // shopingList1.addAll(shopingList1);
       List<GoodsList> shopingList1 = new ArrayList<>();
        shopingList1.add(sl1); shopingList1.add(sl1); shopingList1.add(sl2); shopingList1.add(sl2); shopingList1.add(sl3);shopingList1.add(sl4);

        //List<GoodsList> shopingList2 = Arrays.asList(sl1, sl4, sl2, sl7, sl3);
        //shopingList1.addAll(shopingList2);
        List<GoodsList> shopingList2 = new ArrayList<>();
        shopingList2.add(sl1); shopingList2.add(sl4); shopingList2.add(sl2); shopingList2.add(sl3);shopingList2.add(sl4);

       // List<GoodsList> shopingList3 = Arrays.asList(sl6, sl7);
       // shopingList1.addAll(shopingList3);
        List<GoodsList> shopingList3 = new ArrayList<>();
        shopingList3.add(sl6); shopingList3.add(sl7);

        List<GoodsList> shopingList4 = new ArrayList<>();
        shopingList4.add(sl5); shopingList3.add(sl7);

        List<GoodsList> shopingList5 = new ArrayList<>();
        shopingList5.add(sl8); //shopingList5.add(sl1);


        client1.addShoppingList(shopingList1);
        client2.addShoppingList(shopingList2);
        client3.addShoppingList(shopingList3);
        client4.addShoppingList(shopingList4);
        client5.addShoppingList(shopingList5);

        kasa1.addClient(client1);
        kasa1.addClient(client3);
        kasa2.addClient(client2);
        kasa2.addClient(client4);
        kasa2.addClient(client5);


      //  System.out.println(shop1.getCashRegistersList());


      //  System.out.println("Before: " + shop1);
      //  shop1.sellGoodsToCustomers(client1, kasa1);

       // System.out.println("shopingList1" + shopingList1);
      //  System.out.println("shopingList2" + shopingList2);

     //   System.out.println("\n \n After1: " + shop1);

     //   shop1.sellGoodsToCustomers(client2, kasa2);
     //   System.out.println("\n \n After2: " + shop1);

        // glavoto
        shop1.startSelling();


        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shop1.printTotalOborot();
        shop1.printDeliveryCosts();
        shop1.printSalaryExpense();
        shop1.printTotalShopIncome();

        shop1.printAvailableGoodsLists();




        //shop1.printBroiKasoviBelejki();
        //CashReceipt.printNumberOfAll();

        // print na belejka ot fail
        CashReceipt.printSavedCashReceipt(1);
       // CashReceipt.printSavedCashReceipt(2);

       // shop1.getAvailableNumberOfGoods(g1);
        //shop1.isEnoughGoods(g1, BigDecimal.valueOf(100));
     //   System.out.println(shop1.isGoodAvailable(g5));
      //  System.out.println(shop1.getAvailableNumberOfGoods(g5));

       // shop1.printDeliveryCosts();
        //shop1.printSalaryExpense();

    }
}
