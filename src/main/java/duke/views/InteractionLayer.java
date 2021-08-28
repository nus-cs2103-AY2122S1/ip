package duke.views;

import java.util.List;

import duke.constants.Constants;
import duke.views.cli.Greeter;
import duke.views.cli.Loader;
import duke.views.cli.strategies.RespondWith;

public interface InteractionLayer {

    Greeter getGreeter();

    Loader getLoader();

    RespondWith getResponder();

    /**
     * Runs before the application becomes responsive to user input.
     */
    public abstract void init();

    default String getGreeting() {
        return getGreeter().greet();
    }

    /**
     * Runs before the application becomes responsive to user input.
     */
    default void initCallbacks() {
        List<String> dataList = getLoader().loadRelative(Constants.Storage.PERSISTENCE_LOCATION);
        getResponder().load(dataList);
        init();
    }

    /**
     * Begins listening to user input.
     */
    default void listen() {};

    /**
     * Runs before the application terminates.
     */
    public abstract void end();

    default String getResponse(String query) {
        return getResponder().formatResponse(query);
    }

    default String getRawResponse(String query) {
        return getResponder().respond(query);
    }

    /**
     * Runs before the application terminates.
     */
    default void endCallbacks() {
        end();
        getLoader().storeRelative(getResponder().persistToStore(), Constants.Storage.PERSISTENCE_LOCATION);
    }

    /**
     * Main method. Initializes, runs and ends the application gracefully.
     */
    default void main() {
        initCallbacks();
        listen();
        endCallbacks();
    }
}
