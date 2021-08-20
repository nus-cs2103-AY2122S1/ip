package views.cli;

import java.util.Scanner;

import interfaces.PrintableMixin;
import views.cli.strategies.RespondWith;

/**
 * Encapsulates the command line interface. Listens to and generates response to
 * user input via the terminal.
 */
public class CLI implements PrintableMixin {
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
        greeter = new Greeter();
        sc = new Scanner(System.in);
        this.responder = responder;
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
        sc.close();
    }

}
