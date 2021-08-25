package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static TaskList taskList;
    static Storage storage;
    static Ui ui;


    static void init() {
        storage = new Storage("data/duke.txt");
        try {
            taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
        ui = new Ui();
    }

    static void reply() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            try {
                Parser.parse(userInput, taskList);
                if (userInput.equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                storage.write(taskList);
            }
        }
    }

    public static void main(String[] args) {
        init();
        ui.greet();
        reply();
    }
}
