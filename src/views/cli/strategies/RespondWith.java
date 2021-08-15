package views.cli.strategies;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import constants.Constants;
import helpers.StringHelpers;

public abstract class RespondWith {
    public Map<String, Function<String, String>> commands = new HashMap<>();

    public RespondWith() {
        commands.put("bye", (_query) -> this.bye());
    }

    public String respond(String query) {
        try {
            Function<String, String> f = commands.get(query.split(" ")[0]);
            if (f != null) {
                return f.apply(query);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            return Constants.Display.INVALID_INPUT_RESPONSE;
        }
    }

    protected String endMessage = "bye";

    private String bye() {
        return "~~~You know you can't get enough of me~~~" + System.lineSeparator();
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
