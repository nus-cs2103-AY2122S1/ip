package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.Scanner;

public class Parser {
    Scanner sc;
    Storage storage;
    TaskList taskList;
    Ui ui;

    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.sc = new Scanner(System.in);
    }

    /**
     * Parses input continously and executes the commands provided.
     * Type 'bye' to exit the program.
     */
    public void parse() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    taskList.listTasks();
                } else if (input.startsWith("done ")) {
                    taskList.completeTask(input.substring(5));
                } else if (input.startsWith("todo ")) {
                    taskList.addTask(new ToDo(input.substring(5)));
                } else if (input.startsWith("deadline ")) {
                    taskList.addTask(new Deadline(input.substring(9)));
                } else if (input.startsWith("event ")) {
                    taskList.addTask(new Event(input.substring(6)));
                } else if (input.startsWith("delete ")) {
                    taskList.deleteTask(input.substring(7));
                } else {
                    throw new DukeException(DukeException.DEFAULT);
                }
                storage.save(taskList);
            } catch (DukeException e) {
                ui.display(e);
            }
        }
    }
}
