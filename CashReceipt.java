package com.CITB408_2021;

import com.CITB408_2021.Goods.GoodsListPrice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CashReceipt implements Serializable { // kasova belejka
    // fields
    private int id;
    private Cashier cashier;
    private Date cashReceiptDateTime;
    private List<GoodsListPrice> spisakStoki;
    private BigDecimal totalCost;
    private static int cashReceiptCounter = 0;

    // kolko sa izdadenite kasovi belejki do momenta
    public static int getCashReceiptCounter() {
        return cashReceiptCounter;
    }

    // constructors
    public CashReceipt(Cashier cashier, List<GoodsListPrice> spisakStoki) {
        cashReceiptCounter++;
        this.id = cashReceiptCounter;
        this.cashier = cashier;
        this.cashReceiptDateTime = new Date();
        this.spisakStoki = spisakStoki;
        this.totalCost = calcFinalAmount();
    }

    // getters --------------------------
    public int getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public Date getCashReceiptDateTime() {
        return cashReceiptDateTime;
    }

    public List<GoodsListPrice> getSpisakStoki() {
        return spisakStoki;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public static void printNumberOfAll(){
        System.out.println("Брой издадени касови бележки изобщо: " + cashReceiptCounter);
    }

    // setters --------------------------


    // custom methods

    // sumirane na stokite
    public BigDecimal calcFinalAmount(){
        BigDecimal suma = BigDecimal.ZERO;

        for (GoodsListPrice gl : this.spisakStoki ) {
            suma = suma.add(gl.getTotalPrice());
        }

        return suma;
    }

    public static void saveCashReceipt(CashReceipt cashReceipt)
    {
        CashReceiptUtil.serializeCashReceipt( String.valueOf(cashReceipt.id)+".ser", cashReceipt);
    }

    public static void printSavedCashReceipt( int id )
    {
        CashReceipt receipt = CashReceiptUtil.deserializeCashReceipt(String.valueOf(id)+".ser");
        System.out.print("\n Принт на касова бележка No "+id);
        System.out.println(receipt);
    }


    @Override
    public String toString() {
        return "\n Kasova belejka {" +
                "id=" + id +
                ", cashier=" + cashier +
                ", cashReceiptDateTime=" + cashReceiptDateTime +
                ", spisakStoki=" + spisakStoki +
                ", totalCost=" + totalCost +
                '}';
    }
}
