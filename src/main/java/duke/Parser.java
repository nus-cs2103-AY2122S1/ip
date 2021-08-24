package duke;

import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    Scanner sc;
    TaskList list;
    UI ui;
    Storage storage;

    public Parser(TaskList list, UI ui, Storage storage) {
        sc = new Scanner(System.in);
        this.list = list;
        this.ui = ui;
        this.storage = storage;
    }

    public void readTask() throws IOException {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list.printList();
            } else if (input.startsWith("done")) {
                try {
                    list.markAsDone(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                } catch (NumberFormatException e) {
                    ui.printErrorMessage("Task number is invalid!");
                }
            } else if (input.startsWith("delete")) {
                try {
                    list.deleteTask(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            } else {
                try {
                    list.addTask(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            }
            storage.updateFile(list);
            input = sc.nextLine();
        }

        ui.printByeMessage();
        sc.close();
    }
}
