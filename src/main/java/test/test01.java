package test;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class test01 {
    public static void main(String[] args) {
        SomeService target=new SomeServiceImpl();
        MyInvocationHandle handle=new MyInvocationHandle(target);

        SomeService someService =(SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handle);
        String s=someService.doSome();
        System.out.println(s);

        Enhancer en = new Enhancer();
        en.setSuperclass(SomeService.class);
        en.setCallback(new MyMethodInterceptor(target));
        SomeService service = (SomeService) en.create();
        String res = service.doSome();
        System.out.println(res);
    }
}
