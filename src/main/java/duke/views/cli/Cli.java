package duke.views.cli;

import java.util.Scanner;

import duke.interfaces.PrintableMixin;
import duke.utils.Storage;
import duke.views.Greeter;
import duke.views.InteractionLayer;
import duke.views.strategies.RespondWith;

/**
 * Encapsulates the command line interface. Listens to and generates response to
 * user input via the terminal.
 */
public class Cli implements InteractionLayer, PrintableMixin {
    protected Storage storage;
    protected Greeter greeter;
    protected RespondWith responder;
    protected Scanner sc;

    /**
     * Creates an instance equipped with a responder.
     *
     * @param responder A parser of user input and generates a suitable response
     *                  when fed input.
     */
    public Cli(RespondWith responder) {
        storage = new Storage();
        greeter = new Greeter();
        this.responder = responder;
        sc = new Scanner(System.in);
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
    public void listen() {
        print(greeter.greet());

        String query = "";
        do {
            query = sc.nextLine();
            print(getResponse(query));
        } while (!responder.shouldEnd(query));

    }

    /**
     * Runs before the application terminates.
     */
    @Override
    public void end() {
        sc.close();
    }
}
