package com.wchuang.creational.singleton;

/**
 * 单例模式, 任务管理器
 *
 * @author coderhuang
 * @time 2017/5/2 11:29
 */
public class TaskManager {

    private static TaskManager taskManager = null;

    private TaskManager() {
    }

    public void displayProcesses() {
        System.out.println("显示进程");
    }

    public void displayServices() {
        System.out.println("显示服务");
    }

    public static TaskManager getInstance() {
        //存在并发问题
        if(taskManager == null){
            taskManager = new TaskManager();
        }
        return taskManager;
    }
}

class client {
    public static void main(String[] args){
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.displayProcesses();
        taskManager.displayServices();
    }
}
