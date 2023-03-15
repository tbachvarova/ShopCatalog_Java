package com.CITB408_2021;

import com.CITB408_2021.Goods.GoodsList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Client {
    //fields
    private String name;
    private BigDecimal wallet;
    private List<GoodsList> goodsLists;
    private static int clientCounter;

    //constructors
    public Client(String name, BigDecimal wallet) {
        clientCounter++;
        this.name = name;
        this.wallet = wallet;
        this.goodsLists = new ArrayList();
    }

    // methods
    public String getName() {
        return name;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public List<GoodsList> getGoodsLists() {
        return goodsLists;
    }

    public static int getClientCounter() {
        return clientCounter;
    }

    public void addShoppingList(GoodsList toShopList){
        this.goodsLists.add(toShopList);
    }

    public void addShoppingList( List<GoodsList>  toShopList){
        this.goodsLists.addAll(toShopList);
    }

    // dali moje da si plati
    public boolean isEnoughToPay(BigDecimal suma){
        if( this.wallet.compareTo(suma) >= 0 ){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                ", goodsLists=" + goodsLists +
                '}';
    }
}
