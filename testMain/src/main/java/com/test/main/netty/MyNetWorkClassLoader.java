package com.test.main.netty;

/**
 * 自定义类加载器从网络上加载类
 */
public class MyNetWorkClassLoader extends ClassLoader{

    private String classLoadName; //类加载器名 never used

    public MyNetWorkClassLoader(String classLoadName) {
        super(); //默认指定该类加载器的父类加载器为系统类加载器
        this.classLoadName = classLoadName;
    }

    public MyNetWorkClassLoader(ClassLoader classLoader, String classLoadName) {
        super(classLoader); //显式指定该类加载器的父加载器
        this.classLoadName = classLoadName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {
        //load class data from the connectiong --> io
        return null;
    }
}
