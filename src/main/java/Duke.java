import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum SPECIAL_TASK {
        bye,
        done,
        list,
        todo,
        deadline,
        event,
        delete
    }

    private Storage storage;
    private Ui ui;

    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
    }

    public void run() {
        ArrayList<Task> tasks = Storage.Load();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals(SPECIAL_TASK.bye.name())) {
            String[] splitInput = input.split(" ", 2);
            if (splitInput[0].equals(SPECIAL_TASK.done.name())) {
                int index = Integer.parseInt(splitInput[1]) - 1;
                String returnString = tasks.get(index).markDone();
                ui.PrintMessage(returnString);
            } else if (input.equals(SPECIAL_TASK.list.name())) {
                ui.PrintList(tasks);
            } else if (splitInput[0].equals(SPECIAL_TASK.todo.name())) {
                try {
                    if (splitInput.length < 2 || splitInput[1].equals("") || splitInput[1].equals(" ")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(splitInput[1]));
                    ui.PrintSpecialTasks(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals(SPECIAL_TASK.deadline.name())) {
                try {
                    if (splitInput.length < 2) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    String[] furtherSplits = splitInput[1].split("/by", 2);
                    if (furtherSplits.length < 2 || furtherSplits[0].equals("")) {
                        throw new DukeException("The description  of a deadline cannot be empty.\n" +
                                "Don't forget to use /by to indicate the deadline.");
                    } else if (furtherSplits[1].equals("") || furtherSplits[1].equals(" ")) {
                        throw new DukeException("Deadline must come with a input date/time for the deadline.");
                    }
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
                    LocalDateTime by = LocalDateTime.parse(furtherSplits[1].stripLeading(), df);
                    tasks.add(new Deadline(furtherSplits[0], by));
                    ui.PrintSpecialTasks(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals(SPECIAL_TASK.event.name())) {
                try {
                    if (splitInput.length < 2) {
                        throw new DukeException("The description of a event cannot be empty.");
                    }
                    String[] furtherSplits = splitInput[1].split("/at", 2);
                    if (furtherSplits.length < 2 || furtherSplits[0].equals("")) {
                        throw new DukeException("The description of a event cannot be empty.\n" +
                                "Don't forget to use /at to indicate the event time.");
                    } else if (furtherSplits[1].equals("") || furtherSplits[1].equals(" ")) {
                        throw new DukeException("Event must come with a event date/time.");
                    }
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
                    LocalDateTime at = LocalDateTime.parse(furtherSplits[1].stripLeading(), df);
                    tasks.add(new Event(furtherSplits[0], at));
                    ui.PrintSpecialTasks(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals(SPECIAL_TASK.delete.name())) {
                try {
                    if (splitInput.length < 2) {
                        throw new DukeException("We don't know what to delete!");
                    }
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    if (index >= tasks.size() || index <= 0) {
                        throw new DukeException("Task number does not exist!");
                    }
                    Task toDelete = tasks.get(index);
                    tasks.remove(index);
                    ui.PrintDelete(toDelete.toString(), tasks.size());
                } catch (DukeException e1) {
                    ui.PrintMessage(e1.getMessage());
                } catch (NumberFormatException e2) {
                    DukeException newException = new DukeException("Please input a number!");
                    ui.PrintMessage(newException.getMessage());
                }
            } else {
                try {
                    if (input.equals("blah")) {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    tasks.add(new Task(input));
                    ui.PrintMessage(String.format("added: %s", input));
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            }
            storage.Save(tasks);
            input = sc.nextLine();
        }
        ui.PrintMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
