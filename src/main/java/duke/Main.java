package duke;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
//        if (args.length > 0 && args[0].equals("cli")) {
//            new Duke(false).startCli();
//        } else {
            Application.launch(Duke.class, args);
//        }
    }
}
