package views.cli.strategies;

import constants.Constants;
import helpers.StringHelpers;

public abstract class RespondWith {

    abstract public String respond(String query);

    protected String endMessage = "bye";

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String formatResponse(String query) {
        String formattedString = System.lineSeparator()
                + StringHelpers.wrap(respond(query), Constants.Display.BREAKLINE);
        return StringHelpers.indent(formattedString);
    }

    public boolean shouldEnd(String query) {
        return query.equals(endMessage);
    }
}
