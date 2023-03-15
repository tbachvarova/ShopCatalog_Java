package com.CITB408_2021;

import com.CITB408_2021.Goods.GoodsCategory;

public class ShopGoodsCategory {
    private GoodsCategory goodsCategory;
    private int percent;

    public ShopGoodsCategory(GoodsCategory goodsCategory, int percent) {
        this.goodsCategory = goodsCategory;
        this.percent = checkPositivePercent(percent);
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public int getPercent() {
        return percent;
    }

    // proverqvame dali % e polojitelen, ako ne go pravim
    private int checkPositivePercent(int percent){
        if(percent > 0)
        {
            return percent;
        }
        else
            return percent * -1; // pravim go polojitelno

    }


    @Override
    public String toString() {
        return "ShopGoodsCategory{" +
                "goodsCategory=" + goodsCategory +
                ", percent=" + percent +
                '}';
    }
}
