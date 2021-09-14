package duke.views.gui;

import duke.utils.Storage;
import duke.views.Greeter;
import duke.views.InteractionLayer;
import duke.views.strategies.RespondWith;

public class Gui implements InteractionLayer {
    protected Storage storage;
    protected Greeter greeter;
    protected RespondWith responder;

    /**
     * Creates an instance equipped with a responder.
     *
     * @param responder A parser of user input and generates a suitable response
     *                  when fed input.
     */
    public Gui(RespondWith responder) {
        storage = new Storage();
        greeter = new Greeter();
        this.responder = responder;
    }

    @Override
    public Greeter getGreeter() {
        return greeter;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public RespondWith getResponder() {
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
