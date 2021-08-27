package tiger.exceptions.actions;

import tiger.exceptions.TigerException;

public class TigerActionExecutionException extends TigerException {
    public TigerActionExecutionException(String error) {
        super(error);
    }
}
