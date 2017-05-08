package com.wchuang.creational.prototype;

import java.io.*;

/**
 * 概述: 使用原型实例指定创建对象的种类, 并且通过拷贝这些原型创建新的对象, 原型模式是一种对象创建型模式
 *
 * 数据类型分成 值类型(int, double, byte, boolean, char 等简单数据类型) 和 引用类型(类, 接口, 数组 等复杂类型)
 * 对象的浅克隆: 引用类型只复制地址, java自带的clone()方法实现浅克隆
 * 对象的深克隆: 引用类型也复制一份, java自带的序列号接口实现深克隆, 将对象写到流中,然后再读取回来
 * @author coderhuang
 * @time 2017/5/3 14:48
 */

//浅克隆
public class WeeklyLog implements Cloneable{

    private String name;
    private String date;
    private String content;
    private Attachment attachment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public WeeklyLog clone(){
        Object object;
        try {
            object = super.clone();
            return (WeeklyLog) object;
        }catch (CloneNotSupportedException e){

            return null;
        }
    }

    @Override
    public String toString() {
        return "WeeklyLog{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}

class Attachment implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download(){

        System.out.println("下载附件，文件名为" + name);
    }
}

class Client {
    public static void main(String[] args){
        WeeklyLog weeklyLog = new WeeklyLog();
        weeklyLog.setName("张三");
        weeklyLog.setDate("第12周");
        weeklyLog.setContent("测试数据");
        Attachment attachment = new Attachment();
        weeklyLog.setAttachment(attachment);
        WeeklyLog log_new = weeklyLog.clone();

//        System.out.println(weeklyLog == log_new);
//        System.out.println(log_new.toString());
        System.out.println(log_new.getName() == weeklyLog.getName());
        System.out.println(log_new.getAttachment() == weeklyLog.getAttachment());

    }
}


/**
 * 深克隆,对象里面的引用类型也复制一份
 */
class DeepCloneWeeklyLog implements Serializable {
    private String name;
    private String date;
    private String content;
    private Attachment attachment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public DeepCloneWeeklyLog deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(this);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        return (DeepCloneWeeklyLog)ois.readObject();
    }
}

class DeepCloneClient {

    public static void main(String[] args){
        DeepCloneWeeklyLog weeklyLog;
        DeepCloneWeeklyLog log_new = null;
        weeklyLog = new DeepCloneWeeklyLog();
        Attachment attachment = new Attachment();
        weeklyLog.setAttachment(attachment);
        try {

            log_new = weeklyLog.deepClone();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("克隆失败！");
        }

        System.out.println(weeklyLog == log_new);

        System.out.println(weeklyLog.getAttachment() == log_new.getAttachment());

    }
}
