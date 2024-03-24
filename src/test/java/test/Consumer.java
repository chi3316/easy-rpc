package test;


import com.easy.rpc.proxy.EasyRpcProxy;
import com.easy.rpc.registry.ServerInfo;
import com.easy.rpc.registry.ZookeeperRegistry;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // 创建ZookeeperRegistr对象
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();

        // 创建代理对象，通过代理调用远端Server
        DemoService demoService = EasyRpcProxy.newInstance(DemoService.class, discovery);
        // 调用sayHello()方法，并输出结果
        String result = demoService.sayHello("hello");
        System.out.println(result);
        // Thread.sleep(10000000L);
    }
}