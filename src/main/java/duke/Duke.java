package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected static List<Task> todoList;
    protected Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        todoList = new ArrayList<>();
        try {
            storage.createFile();
            storage.download();
        } catch (IOException e) {
            e.printStackTrace();
            todoList = new ArrayList<>();
        }
    }

    public void run() {
        Ui.printHello();
        Command.process();
    }

    public void finish() {
        try {
            this.storage.upload();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
        duke.run();
        duke.finish();
    }
}
