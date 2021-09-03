package duke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {

    private final Ui target;
    private String latestResponse;

    DynamicInvocationHandler(Ui ui) {
        this.target = ui;
    }

    /**
     * Intercepts calls to methods within UiInterface
     *
     * @param proxy Instance of UiInterface implementor
     * @param method Called method
     * @param args Given arguments
     * @return Result of calling the method
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        this.latestResponse = ((args == null) || (args.length == 0)) ? "" : args[0].toString();
        assert target != null;
        return method.invoke(target, args);
    }

    /**
     * Returns the latest response to a user input.
     *
     * @return latest response
     */
    public String getLatestResponse() {
        return this.latestResponse;
    }
}
