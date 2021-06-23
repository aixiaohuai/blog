package test;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    private Object target;

    public MyMethodInterceptor(Object target) {
        this.target = target;
    }

    public MyMethodInterceptor() {
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object res = method.invoke(target, objects);
        if(res!=null){
            String s=(String )res;
            res = ((String) res).toUpperCase();
        }
        return res;
    }
}
