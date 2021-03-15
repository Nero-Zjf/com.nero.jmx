package com.nero.jmx;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanException, InstanceNotFoundException, ReflectionException, InterruptedException, IOException {
        // write your code here
        // 创建MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // 新建MBean ObjectName, 在MBeanServer里标识注册的MBean
        ObjectName name = new ObjectName("com.nero.jmx:name=TestInfo");

        // 创建MBean,需要实现相关接口
        TestInfo mbean = new TestInfo("nero", "18");

        //注册以后可以通过Jconsole等工具查看
        // 在MBeanServer里注册MBean, 标识为ObjectName(com.dxz.mbean:type=Echo)
        mbs.registerMBean(mbean, name);
        mbs.invoke(name, "helloWorld", new Object[0],new String[0]);


        //开启远程访问，目前只知道可以用jconsole进行远程访问，其他客户端没有测试
        //以下代码开启远程的方式与以下设置JVM的选项参数效果一致
        /*
        -Dcom.sun.management.jmxremote=true
        -Djava.rmi.server.hostname=10.211.55.2
        -Dcom.sun.management.jmxremote.port=8081
        -Dcom.sun.management.jmxremote.ssl=false
        -Dcom.sun.management.jmxremote.authenticate=true 如果需要设置验证访问，则为true，并且设置以下两个属性
        -Dcom.sun.management.jmxremote.access.file=jmxremote.access 注意路径如果采用相对路径则为当前工作路径，目前不知道如何设置类路径下的文件
        -Dcom.sun.management.jmxremote.password.file=jmxremote.password
        */

        //LocateRegistry.createRegistry(8081);
        //JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://10.211.55.2:8081/jmxrmi");
        //JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:8081/jmxrmi");
        //
        //JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        //
        //jcs.start();
        Thread.sleep(Long.MAX_VALUE);
    }
}
