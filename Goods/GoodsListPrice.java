package com.CITB408_2021.Goods;

import com.CITB408_2021.Goods.Goods;
import com.CITB408_2021.Goods.GoodsList;

import java.io.Serializable;
import java.math.BigDecimal;


public class GoodsListPrice extends GoodsList implements Serializable {
    // fields
    private BigDecimal unitPrice; // moje da e dostavna ili prodajna
    private BigDecimal totalPrice;

    // constructors
    public GoodsListPrice(Goods goods, BigDecimal numberOfGoods) {

        super(goods, numberOfGoods);
        this.unitPrice = goods.getDeliveryPrice();
        this.totalPrice = calcTotalPrice(unitPrice, numberOfGoods);
    }

    public GoodsListPrice(Goods goods, BigDecimal numberOfGoods, BigDecimal price) {
        super(goods, numberOfGoods);
        this.unitPrice = price;
        this.totalPrice = calcTotalPrice(price, numberOfGoods);
    }

    // methods


    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal checkPrice(BigDecimal price)
    {
        if( price.compareTo(BigDecimal.ZERO) > 0  ){
            return price;
        }
        else
        {
            System.out.println("Не може цената да е <= 0");
            return BigDecimal.ONE;
        }

    }

    private BigDecimal calcTotalPrice(BigDecimal price, BigDecimal broi)
    {
        return price.multiply(broi);
    }

    @Override
    public String toString() {
        return "GoodsListPrice{" + super.toString()+
                " unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                "} ";
    }
}
