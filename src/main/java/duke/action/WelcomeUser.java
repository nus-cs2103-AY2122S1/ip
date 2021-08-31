package duke.action;

import duke.Response;

/**
 * duke.action.WelcomeUser is an Action that greets the User.
 */
public class WelcomeUser implements Action {
    /**
     * Welcomes the user to the application.
     * @return The duke.Response.
     */
    public Response execute() {
        return new Response(new String[]{
            "Hello! I'm Duke",
            "What can I do for you?",
        });
    }
}
