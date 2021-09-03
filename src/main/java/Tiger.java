import tiger.app.Ui;
import tiger.exceptions.TigerException;

import java.util.Scanner;


public class Tiger {

    // TODO: implement flags for delete done combination, invalid todo combination
    // features: fallthrough commands
    // priority

    private Ui ui;

    public Tiger() {
        this.ui = new Ui();
    }

    public String start() {
        ui.start();
        return ui.getResponse();
    }

    public String getResponse(String input) {
        ui.iterateOnce(input);
        return ui.getResponse();
    }

    public boolean isExited() {
        return ui.isExited();
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        Scanner scanner = new Scanner(System.in);
        String response;
        response = tiger.start();
        System.out.println(response);
        while (!tiger.isExited()) {
            String userInput = scanner.nextLine();
            response = tiger.getResponse(userInput);
            System.out.println(response);

        }
    }

}
