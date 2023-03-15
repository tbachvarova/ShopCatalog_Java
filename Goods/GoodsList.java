package com.CITB408_2021.Goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/*
* Spisak na stoki s broi
* */

public class GoodsList implements Serializable {
    // fields
    private Goods good;
    private BigDecimal numberOfGoods; //broi

    // constructors
    public GoodsList(Goods goods, BigDecimal numberOfGoods) {
        this.good = goods;
        this.numberOfGoods = checkGoodsCount(numberOfGoods);
    }

    // getters

    public Goods getGood() {
        return good;
    }

    public BigDecimal getNumberOfGoods() {
        return numberOfGoods;
    }

    // setters

    public void setNumberOfGoods(BigDecimal numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }


    // methods

    public BigDecimal checkGoodsCount(BigDecimal broi)
    {
        if( broi.compareTo(BigDecimal.ZERO) < 0) // broi < 0
        {
            System.out.println("Броят е коригиран на "+broi.negate()+"! ");
            return broi.negate(); // * -1 :)
        }
        else if(broi.equals(BigDecimal.ZERO)){
            System.out.println("Броят трябва да е > 0! ");
            return BigDecimal.ONE;
        }
        else
            return broi;
    }

    public static List<GoodsList> mergeGoodsList( List<GoodsList> listToBeMerged){
        List<GoodsList> newUniqueList = new ArrayList<>();
        List<GoodsList> copyedList = new ArrayList<>();
        boolean isOk = false;

        for (GoodsList item : listToBeMerged ) {
            copyedList.add( new GoodsList(item.getGood(), item.getNumberOfGoods()) );
        }
       // System.out.println("SOPopyedList" + copyedList);

        for (GoodsList gl : copyedList) {
            isOk = false;
            for (GoodsList tmpGl : newUniqueList)
            {
                if (tmpGl.getGood().equals(gl.getGood())  )
                {
                 //   System.out.println("Good ima" + tmpGl.getGood().getName() + "+ "+gl.getNumberOfGoods());
                    tmpGl.setNumberOfGoods( tmpGl.getNumberOfGoods().add( gl.getNumberOfGoods() ) );
                    isOk = true;
                    break;
                }
            }

            if( !isOk ){
                newUniqueList.add( gl );
            }

        }

       // System.out.println("AAAAAAAAAA" + newUniqueList);
        return newUniqueList;
    }



    public String printShort() {
        return "  -> {" +
                "id=" + good.getId() +
                ", good: " + good.getName() +
                ", numberOfGoods=" + numberOfGoods +
                '}'+"\n";
    }

    @Override
    public String toString() {
        return "\n GoodsList{" +
                "good=" + good +
                ", numberOfGoods=" + numberOfGoods +
                '}';
    }
}
