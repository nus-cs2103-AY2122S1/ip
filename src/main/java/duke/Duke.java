package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.util.DukeException;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {

    private static TaskBank taskBank = new TaskBank("data/duke.txt");

    private static Hashtable<String, Consumer<String>> initCommandTable() {

        Hashtable<String, Consumer<String>> ret = new Hashtable<>();

        ret.put("list", (x) -> UI.printTasks(taskBank.getTasks()));
        ret.put("event", (x) -> taskBank.addTask(x, Event::create));
        ret.put("deadline", (x) -> taskBank.addTask(x, Deadline::create));
        ret.put("todo", (x) -> taskBank.addTask(x, ToDo::create));
        ret.put("done", (x) -> taskBank.markTask(x));
        ret.put("delete", (x) -> taskBank.deleteTask(x));

        return ret;
    }

    public static void main(String[] args) {

        UI.welcomeMessage();
        UI.printDivider();

        Scanner sc=new Scanner(System.in);

        Hashtable<String, Consumer<String>> commandTable = initCommandTable();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;

            String keyword = input.split(" ", 2)[0];
            if (!commandTable.containsKey(keyword)) {
                UI.print("Oops, I'm not sure what you mean :o");
                continue;
            }

            try {
                commandTable.get(keyword).accept(input);
            } catch (DukeException e) {
                UI.print(e.toString());
            }

            UI.printDivider();
        }

        UI.goodbyeMessage();
    }
}