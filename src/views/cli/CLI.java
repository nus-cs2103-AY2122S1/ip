package views.cli;

import java.util.Scanner;

import interfaces.PrintableMixin;
import views.cli.strategies.RespondWith;

public class CLI implements PrintableMixin {
    private Greeter greeter;
    private Scanner sc;
    private RespondWith responder;

    public CLI(RespondWith responder) {
        greeter = new Greeter();
        sc = new Scanner(System.in);
        this.responder = responder;
    }

    public void listen() {
        greeter.greet();

        String query = "";
        do {
            query = sc.nextLine();
            print(responder.formatResponse(query));
        } while (!responder.shouldEnd(query));
        print(responder.bye());
        sc.close();
    }

}
