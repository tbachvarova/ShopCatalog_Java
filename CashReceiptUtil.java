package com.CITB408_2021;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashReceiptUtil { // save + read to file

    public static void serializeCashReceipt(String fileName, CashReceipt receipt){
        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos); ){
            oos.writeObject(receipt);
        } catch (FileNotFoundException e){
            Logger.getLogger(CashReceiptUtil.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e){
            Logger.getLogger(CashReceiptUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static CashReceipt deserializeCashReceipt(String fileName){
        try(FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            return (CashReceipt) ois.readObject();
        } catch (FileNotFoundException e){
            Logger.getLogger(CashReceiptUtil.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException | ClassNotFoundException e){
            Logger.getLogger(CashReceiptUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;

    }

}
