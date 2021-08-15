package views.cli.strategies;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import constants.Constants;
import helpers.StringHelpers;

public abstract class RespondWith {
    public Map<String, Function<String, String>> commands = new HashMap<>();

    public String respond(String query) {
        Function<String, String> f = commands.get(query.split(" ")[0]);
        if (f != null) {
            return f.apply(query);
        }
        return null;
    }

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
