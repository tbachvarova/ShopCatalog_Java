package com.CITB408_2021.Goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Goods implements Serializable {
    private int id;
    private String name;
    private BigDecimal deliveryPrice;
    private GoodsCategory goodsCategory;
    private LocalDate expireDate;
    private static int goodsCounter = 0;


    public Goods(String name, BigDecimal deliveryPrice, GoodsCategory goodsCategory, LocalDate expireDate) {
        goodsCounter++;
        this.id = goodsCounter;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.goodsCategory = goodsCategory;
        this.expireDate = expireDate;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public static int getGoodsCounter() {
        return goodsCounter;
    }


    // proverqvame godnostta na stokata
    public boolean fitForSale(Goods good)
    {
        // good.getExpireDate().isAfter(LocalDate.now()) -- ne stava zaradi ==
        // ako datata e == to pak stava za prodajba realno
        if( good.getExpireDate().compareTo(LocalDate.now()) >= 0   )
        {
            return true;
        }
        else return false;
    }

    public boolean fitForSale()
    {
        if( this.getExpireDate().compareTo(LocalDate.now()) >= 0   ) { return true;  }
        else return false;
    }

    // kolko dni ostavat do garanciaqta
    public int daysToExpire(Goods g)
    {
        return (int) ChronoUnit.DAYS.between( LocalDate.now(), g.getExpireDate() );
    }

    public int daysToExpire()
    {
        return (int) ChronoUnit.DAYS.between( LocalDate.now(), this.getExpireDate() );
    }


    public void printNumberOfGoods()
    {
        System.out.println(goodsCounter);
    }


    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", expireDate=" + expireDate +
                '}';
    }
}
