package com.wchuang.creational.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author coderhuang
 * @time 2017/5/2 11:39
 */
public class LoadBalancer {

    private volatile static LoadBalancer loadBalancer = null;

    private List<String> serverList = null;

    private LoadBalancer(){
        serverList = new ArrayList<String>();
    }

    public static LoadBalancer getInstance(){
        if(loadBalancer == null){
            synchronized (LoadBalancer.class){
                //双重检查, 不是所有的jvm都能支持, 1.5JDK之前的无序读入
                if(loadBalancer == null) {
                    loadBalancer = new LoadBalancer();
                }
            }
        }
        return loadBalancer;
    }

    public void addServer(String server){
        serverList.add(server);
    }

    public void removeServer(String server){
        serverList.remove(server);
    }

    public String getServer(){
        Random random = new Random();
        int i = random.nextInt(serverList.size());
        return serverList.get(i);
    }

}

class Client {
    public static void main(String[] args){
        LoadBalancer loadBalancer1, loadBalancer2;
        loadBalancer1 = LoadBalancer.getInstance();
        loadBalancer2 = LoadBalancer.getInstance();
        if(loadBalancer1 == loadBalancer2){
            System.out.println("服务器负载均衡器具有唯一性！");
        }

        loadBalancer1.addServer("Server 1");
        loadBalancer1.addServer("Server 2");
        loadBalancer1.addServer("Server 3");
        loadBalancer1.addServer("Server 4");

        for(int i = 0; i < 10; i++){
            String server = loadBalancer1.getServer();
            System.out.println("分发请求至服务器： " + server);
        }
    }
}