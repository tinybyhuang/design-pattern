package com.wchuang.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式: 组合多个对象形成树形结构以表示具有 "整体-部分" 关系的层次结构, 组合模式对单个对象(即叶子对象) 和 组合对象(即容器对象) 的使用具有
 *         一致性, 结构型模式
 *
 * @author coderhuang
 * @time 2017/5/10 14:43
 */

//抽象文件类：抽象构件
public abstract class AbstractFile {

    public abstract void add(AbstractFile file);
    public abstract void remove(AbstractFile file);
    public abstract AbstractFile getChild(int i);
    public abstract void killVirus();
}

//图像文件类：叶子构件
class ImageFile extends AbstractFile{

    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    public void killVirus() {
        //模拟杀毒
        System.out.println("----对图像文件'" + name + "'进行杀毒");
    }
}

//文本文件类：叶子构件
class TextFile extends AbstractFile {

    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    public void killVirus() {
        //模拟杀毒
        System.out.println("----对文本文件'" + name + "'进行杀毒");
    }
}

//文件夹类：容器构件
class Folder extends AbstractFile {

    //定义集合fileList，用于存储AbstractFile类型的成员
    private List<AbstractFile> fileList = new ArrayList<AbstractFile>();
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        fileList.add(file);
    }

    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    public AbstractFile getChild(int i) {

        return fileList.get(i);
    }

    public void killVirus() {
        System.out.println("****对文件夹'" + name + "'进行杀毒");  //模拟杀毒
        for(AbstractFile file : fileList){
            file.killVirus();
        }
    }
}

class Client {

    public static void main(String[] args){
        AbstractFile folder = new Folder("图像文件夹");
        AbstractFile subFolder = new Folder("子文件夹");
        AbstractFile image = new ImageFile("图片文件.jpg");
        AbstractFile text = new TextFile("文本文件.txt");

        folder.add(image);
        folder.add(text);
        folder.add(subFolder);
        subFolder.add(text);
        folder.killVirus();
    }
}
