package test;

public class SomeServiceImpl implements SomeService {
    @Override
    public String doSome() {
        System.out.println("执行方法doSome()");
        return "abcd";
    }
}
