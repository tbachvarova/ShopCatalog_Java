package com.CITB408_2021;

import com.CITB408_2021.Exeptions.NotEnoughMoneyToPay;
import com.CITB408_2021.Exeptions.NotEnoughNumberOfGoods;
import com.CITB408_2021.Goods.GoodsListPrice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashRegister { // kasa
    // fields
    private int number;
    private Cashier cashier;
    private Shop shop;
    private List<Client> clientList;
    private List<CashReceipt> cashReceiptList;
    private static int numberOfCashRegisters = 0;

    // constructors
 /*   public CashRegister(Shop shop) {
        numberOfCashRegisters++;
        this.number = numberOfCashRegisters;
        this.shop = shop;
        this.cashier = null;
        this.clientList = new ArrayList();
        this.cashReceiptList = new ArrayList();
    } */

    public CashRegister(Shop shop, Cashier cashier) {
        numberOfCashRegisters++;
        this.number = numberOfCashRegisters;
        this.cashier = cashier;
        this.shop = shop;
        this.clientList = new ArrayList();
        this.cashReceiptList = new ArrayList();

        shop.addCashRegister(this);
    }

    // getters

    public int getNumber() {
        return number;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<CashReceipt> getCashReceiptList() {
        return cashReceiptList;
    }

    public static int getNumberOfCashRegisters() {
        return numberOfCashRegisters;
    }

    // setters

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    private void addCashReceipt(CashReceipt cashReceipt){
        this.cashReceiptList.add(cashReceipt);
    }

    public void addClient(Client c){
        this.clientList.add(c);
    }

    public void endCashRegisterSelling(List<GoodsListPrice> availGoodsPlusPrices, Client client){

        // kasova belejka
        CashReceipt kasovaBelejka = new CashReceipt(this.cashier, availGoodsPlusPrices);

        this.addCashReceipt(kasovaBelejka);
       // System.out.println(kasovaBelejka);
        System.out.println("Kasa "+this.getNumber()+" - Клиент: "+client.getName()+", Kasova belejka " + kasovaBelejka.getId() + " na stoinost: "+ kasovaBelejka.getTotalCost());

        this.shop.addCashReceipt(kasovaBelejka);

        //export na kasova belejka
        CashReceipt.saveCashReceipt(kasovaBelejka);

    }


    // custom methods
    public void sellingGoods(){
            for (Client client : clientList) {
               try {

                    this.shop.sellGoodsToCustomers(client, this);
             //      System.out.println(Thread.currentThread().getName() + this.getNumber() + ", Kasier: "+ this.getCashier().getName() );
                } catch (NotEnoughNumberOfGoods | NotEnoughMoneyToPay e) {
                    Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, null, e);
                }


            }


    }


    @Override
    public String toString() {
        return "CashRegister{" +
                "number=" + number +
                ", cashier=" + cashier +
                ", shop=" + shop +
                ", clientList=" + clientList +
                ", cashReceiptList=" + cashReceiptList +
                '}';
    }
}
