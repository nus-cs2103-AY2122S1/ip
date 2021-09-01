package duke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {
    
    private final Ui target;
    private String latestResponse;
    
    DynamicInvocationHandler(Ui ui) {
        this.target = ui;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        this.latestResponse = ((args == null) || (args.length == 0)) ? "" : args[0].toString();
        return method.invoke(target, args);
    }
    
    public String getLatestResponse() {
        return this.latestResponse;
    }
}
