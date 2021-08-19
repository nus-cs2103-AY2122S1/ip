/**
 * WelcomeUser is an Action that greets the User.
 */
public class WelcomeUser implements Action {
    /**
     * Welcomes the user to the application.
     * @return The Response.
     */
    public Response execute() {
        return new Response(new String[]{
                "Hello! I'm Duke",
                "What can I do for you?",
        });
    }
}
