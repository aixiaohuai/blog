package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandle implements InvocationHandler {
    private Object target;

    public MyInvocationHandle() {
    }

    public MyInvocationHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke----------------------");
        Object result=null;
        result = method.invoke(target,args);
        String s=null;
        if(result!=null){
            String res = (String)result;
            s = res.toUpperCase();
        }
        return s;
    }
}
