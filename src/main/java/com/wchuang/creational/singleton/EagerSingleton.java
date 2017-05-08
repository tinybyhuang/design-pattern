package com.wchuang.creational.singleton;

/**
 * 模式概述: 确保某一个类只有一个实例, 而且自行实例化并向整个系统提供这个实例, 这个类称为单例类, 他提供全局访问的方法, 单例模式是一种对象创建型模式
 *
 * 饿汉式单例模式
 *
 * @author coderhuang
 * @time 2017/5/2 11:56
 */
public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){}

    public EagerSingleton getInstance(){
        return instance;
    }
}
