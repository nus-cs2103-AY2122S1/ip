package duke.views.cli.strategies;

// Level-1

/**
 * A responder that echoes back whatever is fed to it.
 */
public class Echo extends RespondWith {
    @Override
    public String respond(String query) {
        String result = query;
        return result + System.lineSeparator() + System.lineSeparator();
    }

}
