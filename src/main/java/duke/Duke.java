package duke;

import java.util.Hashtable;
import java.util.function.Consumer;

import javafx.application.Application;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.util.DukeException;

/**
 * Main class which runs Duke's main loop.
 */
public class Duke {

    private static TaskBank taskBank = new TaskBank("data/duke.txt");
    private static Hashtable<String, Consumer<String>> commandTable = initCommandTable();

    private static Hashtable<String, Consumer<String>> initCommandTable() {

        Hashtable<String, Consumer<String>> ret = new Hashtable<>();

        ret.put("list", (x) -> Ui.printTasks(taskBank.getTasks()));
        ret.put("event", (x) -> taskBank.addTask(x, Event::create));
        ret.put("deadline", (x) -> taskBank.addTask(x, Deadline::create));
        ret.put("todo", (x) -> taskBank.addTask(x, ToDo::create));
        ret.put("done", (x) -> taskBank.markTask(x));
        ret.put("delete", (x) -> taskBank.deleteTask(x));
        ret.put("find", (x) -> Ui.printTasks(taskBank.searchTasks(x)));
        ret.put("bye", (x) -> System.exit(0));

        return ret;
    }

    private static void parseInput(String input) {
        String keyword = input.split(" ", 2)[0];
        if (!commandTable.containsKey(keyword)) {
            Ui.print("Oops, I'm not sure what you mean");
            return;
        }

        try {
            commandTable.get(keyword).accept(input);
        } catch (DukeException e) {
            Ui.print(e.toString());
        }
    }

    /**
     * Duke's main loop.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Ui.setInputHandler(Duke::parseInput);
        Application.launch(Ui.class);
    }
}
