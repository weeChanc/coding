package com.wc.uitls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Created by 铖哥 on 2018/4/23.
 */


public class U implements InvocationHandler {

    private Object obj;

    public U(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object out ;
        long startTime = System.currentTimeMillis();
        out = method.invoke(obj,args);
        if(out != null)
            System.out.println(method.getName()+"返回值: "+ out.toString());
        System.out.println(obj.getClass().getName()+"  " + method.getName()+"耗时: "+(System.currentTimeMillis()-startTime)+"ms");
        return out;
    }

    public static Solution p(Solution obj) {
        U handler = null;
        handler = new U(obj);
        return (Solution) Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }

    public static Solution p(Class<? extends Solution> clazz) throws IllegalAccessException, InstantiationException {
        Solution obj = clazz.newInstance();
        U handler = new U(obj);
        return (Solution) Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}
