package tiger.actions;

import tiger.app.AppState;

/**
 * The abstract {@code Action} class is responsible for executing the command the user specifies.
 */

abstract public class Action {
    abstract public AppState run();
}
