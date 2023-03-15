package com.CITB408_2021.Exeptions;

import com.CITB408_2021.Goods.Goods;

import java.math.BigDecimal;

public class NotEnoughNumberOfGoods extends Exception {
    private Goods goods;
    private BigDecimal insufficientQuantity;


    public NotEnoughNumberOfGoods(Goods goods, BigDecimal insufficientQuantity) {
        this.goods = goods;
        this.insufficientQuantity = insufficientQuantity;
    }

    @Override
    public String toString() {
       return "Количество от: "+ insufficientQuantity +"  " + goods.getName() + " не достигат";
    }
}
