package duke;

import duke.util.DukeDB;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

//        DukeDB db = new DukeDB("./data/dukeStore.txt");
//        // Start listening to user input through a duke instance
//        Duke duke = new Duke(db);
//        duke.listen();

        Application.launch(DukeGUI.class, args);
    }
}
