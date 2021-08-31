package duke.action;

import duke.Response;

public class GoodbyeUser implements Action {
    /**
     * Greets farewell to the user when the user requests to exit the application.
     * @return The duke.Response.
     */
    public Response execute() {
        return new Response(new String[]{
            "Bye. Hope to see you again soon!",
        });
    }
}
