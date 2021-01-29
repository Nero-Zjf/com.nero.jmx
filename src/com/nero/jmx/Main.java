package com.nero.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanException, InstanceNotFoundException, ReflectionException, InterruptedException {
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

        Thread.sleep(Long.MAX_VALUE);
    }
}
