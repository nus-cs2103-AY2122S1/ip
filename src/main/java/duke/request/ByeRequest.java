package duke.request;

import duke.action.GoodbyeUser;
import duke.action.Action;

/**
 * duke.request.ByeRequest represents a request from the user to stop the application.
 */
public class ByeRequest extends Request {
    /**
     * Gets the Action the duke.request.ByeRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new GoodbyeUser();
    }
}
