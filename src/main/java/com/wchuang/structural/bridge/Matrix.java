package com.wchuang.structural.bridge;

/**
 * 桥接模式: 将抽象部分与它的实现部分分离, 是它们都可以独立地变化, 利用关联取代多层继承
 *
 * @author coderhuang
 * @time 2017/5/9 9:47
 */

//像素矩阵类：辅助类，各种格式的文件最终都被转化为像素矩阵，不同的操作系统提供不同的方式显示像素矩阵
public class Matrix {

}

//抽象图像类：抽象类
abstract class Image {

    protected ImageImp imageImp;

    public void setImageImp(ImageImp imageImp){
        this.imageImp = imageImp;
    }

    public abstract void parseFile(String fileName);
}

//抽象操作系统实现类：实现类接口
interface ImageImp {

    public void doPaint(Matrix matrix); //显示像素矩阵m

}

//Windows操作系统实现类：具体实现类
class WindowImp implements ImageImp {

    public void doPaint(Matrix matrix) {
        //调用Windows系统的绘制函数绘制像素矩阵
        System.out.print("在Windows操作系统中显示图像：");
    }
}

//Linux操作系统实现类：具体实现类
class LinuxImp implements ImageImp {

    public void doPaint(Matrix matrix) {
        //调用Linux系统的绘制函数绘制像素矩阵
        System.out.print("在Linux操作系统中显示图像：");
    }
}

//JPG格式图像：扩充抽象类
class JPGImage extends Image {

    public void parseFile(String fileName) {
        //模拟解析JPG文件并获得一个像素矩阵对象m;
        Matrix m = new Matrix();
        imageImp.doPaint(m);
        System.out.println(fileName + "，格式为JPG。");
    }
}

//PNG格式图像：扩充抽象类
class PNGImage extends Image {

    public void parseFile(String fileName) {
        //模拟解析PNG文件并获得一个像素矩阵对象m;
        Matrix m = new Matrix();
        imageImp.doPaint(m);
        System.out.println(fileName + "，格式为PNG。");
    }
}

class Client {

    public static void main(String[] args){
        Image image = new JPGImage();
        ImageImp imageImp = new WindowImp();
        image.setImageImp(imageImp);
        image.parseFile("小龙女");
    }
}