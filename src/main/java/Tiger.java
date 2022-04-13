import java.util.Scanner;

import tiger.app.Ui;

public class Tiger {

    private Ui ui;

    public Tiger() {
        this.ui = new Ui();
    }

    /**
     * Starts the interaction with {@code Ui}.
     *
     * @return the {@code Ui} response.
     */
    public String start() {
        ui.start();
        return ui.getResponse();
    }

    /**
     * Gets the response of the {@code Ui} after one iteration.
     *
     * @return the {@code Ui} response.
     */
    public String getResponse(String input) {
        ui.iterateOnce(input);
        return ui.getResponse();
    }

    /**
     * Check if the user has exited the app.
     *
     * @return whether the user has exited the app.
     */

    public boolean isExited() {
        return ui.isExited();
    }

    /**
     * Starts a new CLI session, will not render a window.
     *
     * @param args java default args.
     */

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
