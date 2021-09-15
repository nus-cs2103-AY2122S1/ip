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

        Hashtable<String, Consumer<String>> table = new Hashtable<>();

        table.put("list", (x) -> Ui.printTasks(taskBank.getTasks(x)));
        table.put("event", (x) -> taskBank.addTask(x, Event::create));
        table.put("deadline", (x) -> taskBank.addTask(x, Deadline::create));
        table.put("todo", (x) -> taskBank.addTask(x, ToDo::create));
        table.put("done", (x) -> taskBank.markTask(x));
        table.put("delete", (x) -> taskBank.deleteTask(x));
        table.put("find", (x) -> Ui.printTasks(taskBank.searchTasks(x)));
        table.put("bye", (x) -> System.exit(0));

        return table;
    }

    private static void parseInput(String input) {
        String keyword = input.split(" ", 2)[0];
        if (!commandTable.containsKey(keyword)) {
            Ui.print("I cannot understand you");
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
        //Command table should be initialised at this point
        assert(commandTable.keySet().size() > 0);
        Ui.setInputHandler(Duke::parseInput);
        Application.launch(Ui.class);
    }
}
