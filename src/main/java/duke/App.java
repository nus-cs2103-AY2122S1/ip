package duke;

import duke.exceptions.AuguryException;

/**
 * The {@code App} class starts an instance of Augury.
 */
public class App {

    /**
     * Initializes the {@code Augury} application.
     */
    public static void main(String[] args) throws AuguryException {
        Augury a = new Augury("data/tasks.txt");
        a.init();
        a.greet();
        a.loop();
    }
}
