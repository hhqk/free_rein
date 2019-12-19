package com.hhqk.common.reflect;

import java.lang.reflect.Method;

public class ReflectUtil {
    // 客户端  -> 接口  -> 服务端的接口实现
    // 根据相应参数通过反射获取类 ,通过网络的对象进行对象序列化
    public void findClass(String className, String method) {
        // 接口类全限定名，请求参数
        try {
            Class<?> aClass = Class.forName(className);
            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class temp : interfaces) {
                Method[] methods = temp.getMethods();

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void findClass(String name, ClassLoader classLoader) {

        try {
            // true该类将被初始化，自定义classloader,从网络上找到类进行加载
            Class.forName(name, true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
