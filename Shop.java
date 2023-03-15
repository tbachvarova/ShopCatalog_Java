package com.CITB408_2021;

import com.CITB408_2021.Exeptions.NotEnoughMoneyToPay;
import com.CITB408_2021.Exeptions.NotEnoughNumberOfGoods;
import com.CITB408_2021.Goods.Goods;
import com.CITB408_2021.Goods.GoodsCategory;
import com.CITB408_2021.Goods.GoodsList;
import com.CITB408_2021.Goods.GoodsListPrice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    // fields
    private String name;
    private int numberOfDaysUntilExpirationWarranty; // broi dni do garanciqta
    private int percentOfDaysUntilExpirationWarranty; // % otstypka ot cenata

    private List<ShopGoodsCategory> goodsCategoryMarkupList; // spisak % po kategorii
    private List<Cashier> cashiersList; // spisak ksaieri
    private List<CashRegister> cashRegistersList; // spisak kasi
    private List<CashReceipt> cashReceiptsList; // spisak kasovi belejki
    private List<GoodsList> availableGoodsLists; //spisak na nalichnite stoki
    private List<GoodsListPrice> deliveredGoodsList; // spisak dostavki
    private List<GoodsListPrice> soldGoodsList; // spisak dostavki

    private static int shopCounter;

    // constructors ------------------------------------------------
    public Shop(String name) {
        shopCounter++;

        this.name = name;
        this.numberOfDaysUntilExpirationWarranty = 0;
        this.percentOfDaysUntilExpirationWarranty = 0;

        this.goodsCategoryMarkupList = new ArrayList();
        this.cashiersList = new ArrayList();
        this.cashReceiptsList = new ArrayList();
        this.cashRegistersList = new ArrayList();
        this.availableGoodsLists = new ArrayList();
        this.deliveredGoodsList = new ArrayList();
        this.soldGoodsList = new ArrayList();

    }

    public Shop(String name, int numberOfDaysUntilExpirationWarranty, int percentOfDaysUntilExpirationWarranty) {
        shopCounter++;
        this.name = name;
        this.numberOfDaysUntilExpirationWarranty = numberOfDaysUntilExpirationWarranty;
        this.percentOfDaysUntilExpirationWarranty = percentOfDaysUntilExpirationWarranty;

        this.goodsCategoryMarkupList = new ArrayList();
        this.cashiersList = new ArrayList();
        this.cashReceiptsList = new ArrayList();
        this.cashRegistersList = new ArrayList();
        this.availableGoodsLists = new ArrayList();
        this.deliveredGoodsList = new ArrayList();
        this.soldGoodsList = new ArrayList();

    }

    // getters ------------------------------------------------

    public String getName() {
        return name;
    }

    public int getNumberOfDaysUntilExpirationWarranty() {
        return numberOfDaysUntilExpirationWarranty;
    }

    public int getPercentOfDaysUntilExpirationWarranty() {
        return percentOfDaysUntilExpirationWarranty;
    }

    public List<ShopGoodsCategory> getGoodsCategoryMarkupList() {
        return goodsCategoryMarkupList;
    }

    public List<Cashier> getCashiersList() {
        return cashiersList;
    }

    public List<CashReceipt> getCashReceiptsList() {
        return cashReceiptsList;
    }

    public List<GoodsList> getAvailableGoodsLists() {
        return availableGoodsLists;
    }

    public List<GoodsListPrice> getDeliveredGoodsList() {
        return deliveredGoodsList;
    }

    public List<GoodsListPrice> getSoldGoodsList() {
        return soldGoodsList;
    }

    public List<CashRegister> getCashRegistersList() {
        return cashRegistersList;
    }

    public static int getShopCounter() {
        return shopCounter;
    }

    // setters ------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfDaysUntilExpirationWarranty(int numberOfDaysUntilExpirationWarranty) {
        this.numberOfDaysUntilExpirationWarranty = numberOfDaysUntilExpirationWarranty;
    }

    public void setPercentOfDaysUntilExpirationWarranty(int percentOfDaysUntilExpirationWarranty) {
        this.percentOfDaysUntilExpirationWarranty = percentOfDaysUntilExpirationWarranty;
    }


    // custom functions ------------------------------------------------

    // br na kasierite
    public int getCashiersCount() {
        return cashiersList.size();
    }

    // add kasov bon
    protected void addCashReceipt(CashReceipt cashReceipt){
        if( !this.cashReceiptsList.contains(cashReceipt) ){
            this.cashReceiptsList.add(cashReceipt);
        }

    }

    // dobavqme % za kategoriq kam magazina
    public void addGoodsCategoryMarkup(ShopGoodsCategory g)
    {
        boolean ifExist = false;

        for (ShopGoodsCategory gc: goodsCategoryMarkupList ) {
            //System.out.println( gc.getGoodsCategory() + " <->" + g.getGoodsCategory()  );
            if( gc.getGoodsCategory() == g.getGoodsCategory() )
            {
                ifExist = true; break;
            }
        }

        if( !ifExist)
        {
            this.goodsCategoryMarkupList.add(g);
        }
    }

    // moje i metod za promqna na nadcenkata za kategoriq ...

    private void addToDeliveryList(GoodsListPrice d)
    {
        this.deliveredGoodsList.add(d);
    }

    // dali stokata q ima izobshto
    private synchronized boolean isGoodAvailable(Goods g){
        boolean isAvailable = false;

        for (GoodsList gl : this.availableGoodsLists ) {
            if( gl.getGood().equals(g)  ){
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }

    private synchronized BigDecimal getAvailableNumberOfGoods(Goods g){
        BigDecimal currentNumberOfGoods = BigDecimal.ZERO;

        for (GoodsList gl : this.availableGoodsLists ) {
            if( gl.getGood().equals(g)  ){
               currentNumberOfGoods = gl.getNumberOfGoods();
            }
        }

        return currentNumberOfGoods;
    }


    // ako nqma dostatachno kolichestvo da hvarlq izkluchenie
    public synchronized boolean isEnoughGoods(Goods g, BigDecimal kolichestvo) throws NotEnoughNumberOfGoods {
        boolean isEnoughGood = false;
        BigDecimal availabNumbGoods = BigDecimal.ZERO;

        if( isGoodAvailable(g) ) // nalichna li e?
        {
            availabNumbGoods = getAvailableNumberOfGoods(g); // kolko ima

            if( availabNumbGoods.compareTo(kolichestvo) >= 0 ){ // check kolichestvo OK
                isEnoughGood = true;
            }
            else
            {
                throw new NotEnoughNumberOfGoods(g, kolichestvo.subtract(availabNumbGoods) );
            }

        }
        return isEnoughGood;
    }

    // dobavqne na nalichnost
    private void addToAvailableList(GoodsList goodsList)
    {
        boolean ifExist = false;
        for (GoodsList gl: availableGoodsLists ) {
            if( gl.getGood().equals(goodsList.getGood()) ) // ako savpadat
            {
                // uvelichavame avail
                gl.setNumberOfGoods(gl.getNumberOfGoods().add(goodsList.getNumberOfGoods())  );
                ifExist = true;
                break;
            }
        }

        if(!ifExist){
            this.availableGoodsLists.add(goodsList);
        }
    }

    // namalqvane na available stoka
    private synchronized void changeOfAvailableGoods(Goods g, BigDecimal quantity) throws NotEnoughNumberOfGoods {

        if( isEnoughGoods(g, quantity)){
            for (GoodsList gl: availableGoodsLists ) {
                if( gl.getGood().equals(g) ) // ako savpadat
                {   // namalqvame available
                    gl.setNumberOfGoods(gl.getNumberOfGoods().subtract(quantity)  );
                    break;
                }
            }
        }
    }

    // izpisvane na stoka ot nalichni
    private synchronized void removingGoodsFromAvailable( List<GoodsList> glAll ) throws NotEnoughNumberOfGoods {
        for (GoodsList gl : glAll) {
            changeOfAvailableGoods(gl.getGood(), gl.getNumberOfGoods());
        }
    }


    // add kasier
    public void addCashier(Cashier c){
        if( !this.cashiersList.contains(c) ){
            this.cashiersList.add(c);
        }
    }

    public void addCashRegister( CashRegister  kasa){
        if( !this.cashRegistersList.contains(kasa) )
        { this.cashRegistersList.add(kasa); }

    }

    public void removeCashier(Cashier c){
        this.cashiersList.remove(c);
    }


    // dostavka
    public void deliveryToShop(Goods good, BigDecimal quantity){
        // GoodsListPrice
        if( quantity.compareTo(BigDecimal.ZERO) > 0 ){ // ok kolichestvo

            if( good.fitForSale() ) // ok e garanciqta
            {
                // dobavqme v dostavkata
                this.addToDeliveryList( new GoodsListPrice(good, quantity ) );
                this.addToAvailableList( new GoodsList(good, quantity) );

            }
            else {
                System.out.println("Стоката е с изтекла гаранция !");
            }
        }
        else{
            System.out.println("Доставката трябва да бъде с количество > 0 !");
        }
    }

    // vzima % nadcenka za kategoriq stoki
    public int getCategoryPercentMarkup(GoodsCategory goodsCategory){
        for (ShopGoodsCategory sgc: this.goodsCategoryMarkupList ) {
            if (sgc.getGoodsCategory().equals(goodsCategory))
            {
                return sgc.getPercent();
            }
        }
        return 0;

    }

    // calc nadcenka - samo stoinostta na nadcenkata
    private BigDecimal calcPriceMarkup(Goods g)
    {
        return g.getDeliveryPrice().divide(BigDecimal.valueOf(100)).multiply( BigDecimal.valueOf(getCategoryPercentMarkup(g.getGoodsCategory())) );
    }

    // check trqbva li da ima doscount
    public boolean checkHaveADiscount(Goods g){
        if( this.numberOfDaysUntilExpirationWarranty > 0 )
        {
            if( g.daysToExpire() <= this.numberOfDaysUntilExpirationWarranty ){
                return true;
            }
            return false;
        }
        else
            return false;
    }


    // izchislqva prodajnata cena na stokata
    public BigDecimal calcGoodsSellingPrice(Goods g)
    {
        BigDecimal currentPricePlusMarkup = g.getDeliveryPrice().add( calcPriceMarkup(g));

        if( checkHaveADiscount(g) )
        {
            // tuk moje da se dopylni s parametar dali da pada pod dostavnata cena...
            return currentPricePlusMarkup.subtract( currentPricePlusMarkup.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf( this.percentOfDaysUntilExpirationWarranty )) );
        }
        else
            return currentPricePlusMarkup;

    }

    // sumirame total na spisak s ceni
    public BigDecimal calcTotalListAmount(List<GoodsListPrice> glAll){
        BigDecimal suma = BigDecimal.ZERO;

        for (GoodsListPrice gl : glAll ) {
            suma = suma.add( gl.getTotalPrice() );
        }

        return suma;
    }

    public List<GoodsListPrice> generateCustomerGoodsPriceList(List<GoodsList> glAll) throws NotEnoughNumberOfGoods {
        List<GoodsListPrice> availGoodsPlusPrices = new ArrayList();
        BigDecimal sellingPrise = BigDecimal.ZERO;

        for (GoodsList gl : glAll) { // za vsqka edna ot stokite na klienta
            if( isEnoughGoods(gl.getGood(), gl.getNumberOfGoods()) ){
                sellingPrise = calcGoodsSellingPrice(gl.getGood());
                availGoodsPlusPrices.add( new GoodsListPrice(gl.getGood(), gl.getNumberOfGoods(), sellingPrise )); // dobavqme v spisaka
            }
        }
        return availGoodsPlusPrices;
    }

    // dobavqne v spisak s prodadeni stoki
    private void addToSoldList( List<GoodsListPrice> soldGoodsListClient ){
        this.soldGoodsList.addAll(soldGoodsListClient);
    }



    // SAMATA PRODAJBA  ------------------------------------------------------------ !!!!!!!!!!!!!
    public void sellGoodsToCustomers( Client client, CashRegister cashRegister ) throws NotEnoughNumberOfGoods, NotEnoughMoneyToPay
    {
        // sumirame vsichki produkti
        List<GoodsList> uniqueGoodList = new ArrayList();
        List<GoodsList> copyList = new ArrayList();
        for (GoodsList item : client.getGoodsLists() ) {
            copyList.add(new GoodsList(item.getGood(), item.getNumberOfGoods()));
        }

       uniqueGoodList =  GoodsList.mergeGoodsList(copyList);
       // System.out.println("SUMIRANA GoodList" + uniqueGoodList);

        // spisak stoki s ceni na klienta
        List<GoodsListPrice> availGoodsPlusPrices = generateCustomerGoodsPriceList( uniqueGoodList );
       // System.out.println("Sumitana + ceni: "+availGoodsPlusPrices);
        // calc na krainata suma
        BigDecimal finalPaymentAmount = calcTotalListAmount(availGoodsPlusPrices);

        if( client.isEnoughToPay(finalPaymentAmount) ) // ako moje da si plati
        {
            // prispadame stokata
            removingGoodsFromAvailable(uniqueGoodList);

            // soldGoodsList
            addToSoldList(availGoodsPlusPrices);

            // puskane na kasova belejka
            cashRegister.endCashRegisterSelling(availGoodsPlusPrices, client);
            // add na kasoviq bon
          //  this.addCashReceipt(kasovaBelejka);

        }
        else
        {
            throw new NotEnoughMoneyToPay(client, finalPaymentAmount.subtract(client.getWallet()) );
        }

    }

    // proces na prodajba na kasite
    public void startSelling(){
        for (CashRegister cr : this.cashRegistersList ) {
                Runnable runnable = () -> cr.sellingGoods();

             Thread thread = new Thread(runnable, "Проаджба на каса: ");

           System.out.println("Продажба на Kasa " + cr.getNumber() + " при Касиер: " + cr.getCashier().getName() );
           // System.out.println("Starting Thread ...");
            thread.start();
        }
    }

    //
    public void printBroiKasoviBelejki(){
        System.out.println("Общо касови бележки в магазина: " + this.cashReceiptsList.size());

    }


    // razhod za zaplati na kasierite
    private BigDecimal calcSalaryExpense()
    {
        BigDecimal total = BigDecimal.ZERO;

        for (Cashier c : cashiersList)
        {
           total = total.add(c.getSalary());
        }

        return total;
    }


    //calc razhod dostavka na stoki
    private BigDecimal calcTotalDeliveryCost(){
        BigDecimal costs = BigDecimal.ZERO;

        for (GoodsListPrice glp: this.deliveredGoodsList ) {
            //System.out.println(glp.getTotalPrice());
            costs = costs.add( glp.getTotalPrice() );
        }

        return costs;
    }

    // calc prihodi ot prodadeni stoki
    private BigDecimal calcOborot(){
        BigDecimal totalOborot = BigDecimal.ZERO;

        for (CashReceipt receipt : this.cashReceiptsList ) {
           // System.out.println("suma za oborota b-"+receipt.getId()+" = "+receipt.getTotalCost());
            totalOborot = totalOborot.add( receipt.getTotalCost() );
        }
       // System.out.println("total oborot = "+totalOborot);
        return totalOborot;
    }

    // calc pechlbata = prihodite - dostavkata - zaplatite
    private BigDecimal calcStoreIncome()
    {
        return this.calcOborot().subtract( this.calcTotalDeliveryCost() ).subtract(this.calcSalaryExpense());
    }


    public void printTotalOborot(){
        System.out.println("Оборот на магазина = " + calcOborot());

    }

    public void printDeliveryCosts(){
        System.out.println("Общо разходи за доставка: "+ this.calcTotalDeliveryCost());
    }

    public void printSalaryExpense(){
        System.out.println("Общо разходи за заплати: "+ this.calcSalaryExpense());
    }

    public void printTotalShopIncome(){
        System.out.println("Приход на магазин " + this.getName()+ ": "+this.calcStoreIncome());
    }

    public void printAvailableGoodsLists()
    {
        System.out.println("Налични стоки в магазина след продажбата:");
        for (GoodsList gl : this.availableGoodsLists) {
            System.out.print(gl.printShort());
        }

    }


    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +"\n"+
                ", numberOfDaysUntilExpirationWarranty=" + numberOfDaysUntilExpirationWarranty +"\n"+
                ", percentOfDaysUntilExpirationWarranty=" + percentOfDaysUntilExpirationWarranty +"\n"+
                ", goodsCategoryMarkupList=" + goodsCategoryMarkupList + "\n"+
                ", cashiersList=" + cashiersList +"\n"+
                ", cashReceiptsList=" + cashReceiptsList +"\n"+
                ", availableGoodsLists=" + availableGoodsLists +"\n"+
                ", deliveredGoodsList=" + deliveredGoodsList +"\n"+
                ", soldGoodsList=" + soldGoodsList +
                '}';
    }
}
