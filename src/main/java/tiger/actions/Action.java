package tiger.actions;

import tiger.app.AppState;

/**
 * The abstract {@code Action} class is responsible for executing the command the user specifies.
 */

public abstract class Action {
    public abstract AppState run();
}
