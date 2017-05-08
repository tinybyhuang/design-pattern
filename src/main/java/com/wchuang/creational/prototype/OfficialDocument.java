package com.wchuang.creational.prototype;

import java.util.Hashtable;

/**
 * @author coderhuang
 * @time 2017/5/3 15:37
 */
public interface OfficialDocument  extends Cloneable{

    public OfficialDocument clone();
    public void display();
}

class FAR implements OfficialDocument {

    public OfficialDocument clone() {
        OfficialDocument far = null;
        try {
            far = (OfficialDocument) super.clone();
        }catch (CloneNotSupportedException e){

            e.printStackTrace();
            System.out.println("不支持复制！");
        }
        return far;
    }

    public void display() {

        System.out.println("《可行性分析报告》");
    }
}

class SRS implements OfficialDocument {

    public OfficialDocument clone() {
        OfficialDocument srs = null;
        try {
            srs = (OfficialDocument) super.clone();
        }catch (CloneNotSupportedException e){

            e.printStackTrace();
            System.out.println("不支持复制！");
        }
        return srs;
    }

    public void display() {

        System.out.println("《软件需求规格说明书》");
    }
}

class PPR implements OfficialDocument{

    public OfficialDocument clone() {
        return null;
    }

    public void display() {

    }
}

class PrototypeManager{
    private Hashtable<String, OfficialDocument> ht = new Hashtable<String, OfficialDocument>();
    private static PrototypeManager pm = new PrototypeManager();

    private PrototypeManager(){
        ht.put("far", new FAR());
        ht.put("srs", new SRS());
        ht.put("ppr", new PPR());
    }

    public void addOfficialDocument(String key, OfficialDocument document){

        ht.put(key, document);
    }

    public OfficialDocument getOfficialDocument(String key) {

        return ht.get(key).clone();
    }

    public static PrototypeManager  getPrototypeManager() {
        return pm;
    }

}

class PrototypeManagerClient {
    public static void main(String[] args){
        PrototypeManager pm = PrototypeManager.getPrototypeManager();
        OfficialDocument doc1, doc2;
        doc1 = pm.getOfficialDocument("far");
        doc1.display();
        doc2 = pm.getOfficialDocument("far");
        doc2.display();

        System.out.println(doc1 == doc2);
    }
}
