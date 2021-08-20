package duke.views.cli;

import java.util.List;
import java.util.Scanner;

import duke.constants.Constants;
import duke.interfaces.PrintableMixin;
import duke.views.cli.strategies.RespondWith;

/**
 * Encapsulates the command line interface. Listens to and generates response to
 * user input via the terminal.
 */
public class CLI implements PrintableMixin {
    private Loader loader;
    private Greeter greeter;
    private Scanner sc;
    private RespondWith responder;

    /**
     * Creates an instance equipped with a responder.
     * 
     * @param responder A parser of user input and generates a suitable response
     *                  when fed input.
     */
    public CLI(RespondWith responder) {
        loader = new Loader();
        greeter = new Greeter();
        sc = new Scanner(System.in);
        this.responder = responder;
    }

    public void init() {
        List<String> dataList = loader.loadRelative(Constants.Storage.PERSISTENCE_LOCATION);
        responder.load(dataList);
    }

    /**
     * Begins listening to user input.
     */
    public void listen() {

        greeter.greet();

        String query = "";
        do {
            query = sc.nextLine();
            print(responder.formatResponse(query));
        } while (!responder.shouldEnd(query));

    }

    /**
     * Runs before the application terminates.
     */
    public void end() {
        sc.close();
        loader.storeRelative(responder.persistToStore(), Constants.Storage.PERSISTENCE_LOCATION);
    }

    /**
     * Main method. Initializes, runs and ends the application gracefully.
     */
    public void main() {
        init();
        listen();
        end();
    }

}
