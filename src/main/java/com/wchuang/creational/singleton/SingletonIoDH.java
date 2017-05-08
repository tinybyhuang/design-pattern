package com.wchuang.creational.singleton;

/**
 * 通过内部类构建单例模式, 而且内部类不随主类一起加载, 只有在调用的时候才加载
 *
 * @author coderhuang
 * @time 2017/5/2 14:07
 */
public class SingletonIoDH {

    private SingletonIoDH(){}

    private static class HolderClass {

        private final static SingletonIoDH instance = new SingletonIoDH();
    }
    public static SingletonIoDH getInstance() {
        return HolderClass.instance;
    }

}

class Client1 {
    public static void main(String[] args){
        SingletonIoDH s1, s2;
        s1 = SingletonIoDH.getInstance();
        s2 = SingletonIoDH.getInstance();
        System.out.println(s1==s2);
    }
}
