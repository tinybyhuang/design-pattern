package com.wchuang.creational.factoryMehtod;

/**
 * 工厂方法模式
 *
 * @author coderhuang
 * time 2017/4/28 18:10
 */

//日志记录器接口：抽象产品
public interface Logger {

    void writeLog();
}

//数据库日志记录器：具体产品
class DatabaseLogger implements Logger {

    public void writeLog() {

        System.out.println("数据库日志记录。");
    }
}

//日志记录器工厂接口：抽象工厂
class FileLogger implements Logger {

    public void writeLog() {

        System.out.println("文件日志记录。");
    }
}

//日志记录器工厂接口：抽象工厂
interface LoggerFactory {

    Logger createLogger();
}

//数据库日志记录器工厂类：具体工厂
class DatabaseLoggerFactory  implements LoggerFactory {

    public Logger createLogger() {

        return new DatabaseLogger();
    }
}

//文件日志记录器工厂类：具体工厂
class FileLoggerFactory implements LoggerFactory{

    public Logger createLogger() {

        return new FileLogger();
    }
}

class client {
    public static void main(String[] args){
        LoggerFactory loggerFactory;
        Logger logger;
        loggerFactory = new FileLoggerFactory();
        logger = loggerFactory.createLogger();
        logger.writeLog();
    }
}


abstract class LoggerFactoryMethod {

    public void writeLog(){

        Logger logger = this.createLogger();
        logger.writeLog();
    }

    public abstract Logger createLogger();
}