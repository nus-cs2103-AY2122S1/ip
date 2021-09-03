package duke.views.gui;

import duke.views.InteractionLayer;
import duke.views.cli.Greeter;
import duke.views.cli.Loader;
import duke.views.cli.strategies.RespondWithDelegated;

public class Gui implements InteractionLayer {
    protected Loader loader;
    protected Greeter greeter;
    protected RespondWithDelegated responder;

    /**
     * Creates an instance equipped with a responder.
     *
     * @param responder A parser of user input and generates a suitable response
     *                  when fed input.
     */
    public Gui(RespondWithDelegated responder) {
        loader = new Loader();
        greeter = new Greeter();
        this.responder = responder;
    }

    @Override
    public Greeter getGreeter() {
        return greeter;
    }

    @Override
    public Loader getLoader() {
        return loader;
    }

    @Override
    public RespondWithDelegated getResponder() {
        return responder;
    }

    @Override
    public void init() {}

    /**
     * Begins listening to user input.
     */
    @Override
    public void listen() {}

    /**
     * Runs before the application terminates.
     */
    @Override
    public void end() {}
}
