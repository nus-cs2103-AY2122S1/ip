package duke.views.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import duke.constants.Constants;
import duke.shared.StringHelpers;
import duke.views.Response;
import duke.views.strategies.commands.ByeCommand;
import duke.views.strategies.commands.Command;

/**
 * A template class for responders that gives a string response upon receiving
 * string input.
 */
public abstract class RespondWith {

    /**
     * A mapping storing commandName, function pairs. The function will be called
     * when the given input matches the command.
     */
    protected Map<String, Command> commands = new HashMap<>();
    protected String endMessage = "bye";

    public RespondWith() {
        commands.put("bye", new ByeCommand());
    }


    /**
     * Returns a string responding to an incoming input
     *
     * @param query Incoming input.
     * @return Response text.
     */
    public String respond(String query) {

        Command f = commands.get(query.split(" ")[0]);
        if (f != null) {
            return f.produce(query);
        }
        return null;

    }

    /**
     * Returns a Response object that not only encapsulates a text message but also
     * additional metadata.
     *
     * @param query Incoming input.
     * @return Response object.
     */
    public Response respondWithMetadata(String query) {
        Command f = commands.get(query.split(" ")[0]);
        if (f != null) {
            return Response.withMessage(f.produce(query));
        }
        return null;
    }

    /**
     * Returns a prettier formatted form of the given string.
     *
     * @param response Raw response text.
     * @return Formatted response text.
     */
    public String formatResponse(String response) {
        String formattedString = System.lineSeparator()
                + StringHelpers.wrap(respond(response), Constants.Display.BREAKLINE);
        return StringHelpers.indent(formattedString);
    }

    /**
     * Returns a boolean indicating if the chatbot program should terminate.
     *
     * @param query Incoming input.
     * @return Whether program should terminate.
     */
    public boolean shouldEnd(String query) {
        return query.equals(endMessage);
    }

    public Object rehydrateFromString(String s) {
        return null;
    }

    public String persistToStore() {
        return "";
    }

    public void load(List<String> dataList) {

    }
}
