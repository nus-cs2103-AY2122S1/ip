package duke;

// import duke packages
import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

// import java packages
import java.util.Scanner;

/**
 * A chatbot that stores tasks given by a user.
 */
public class Duke {
    private Storage storage;
    private TaskList cmdList;
    private Ui ui;

    /**
     * Creates a Duke chatbot.
     *
     * @param filePath Filepath to save tasks and load tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            cmdList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            cmdList = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        ui.sayHello();
        int count = 0 + cmdList.getLength();

        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (Parser.isNotBye(cmd)) {
            try {
                if (Parser.isList(cmd)) {       // when command given is "list"
                    ui.printList(cmdList);
                } else if (Parser.isDone(cmd)) {      // when command given is "done"
                    int itemNo = Parser.parseDoneCmd(cmd);

                    // Make sure itemNo is within limit
                    if ((itemNo <= count) && (itemNo > 0)) {
                        cmdList.markDone(itemNo - 1);
                        storage.saveTasks(cmdList.getTasks());
                    } else {        // throw exception when itemNo not within limit
                        throw new DukeException("i cant seem to find the task you're looking for");
                    }
                    ui.printTaskDone(cmdList, itemNo - 1);
                } else if (Parser.isValidTask(cmd)) {
                    String task = Parser.getTaskName(cmd);
                    String desc = Parser.getDesc(cmd);
                    if (Parser.isMissingArg(cmd)) {       // make sure desc is not empty
                        // case when only a whitespace follows a command
                        throw new DukeException("the description of a " + task + " cannot be empty");
                    } else {
                        // Separate into cases
                        switch (task) {
                        case "todo":
                            cmdList.add(new Todo(desc));
                            break;
                        case "deadline":
                            String DlInfo = Parser.getDlInfo(desc);
                            String DlDue = Parser.getDlDue(desc);
                            cmdList.add(new Deadline(DlInfo, DlDue));
                            break;
                        case "event":
                            String EvInfo = Parser.getEvInfo(desc);
                            String EvDue = Parser.getEvDue(desc);
                            cmdList.add(new Event(EvInfo, EvDue));
                            break;
                        }
                        storage.saveTasks(cmdList.getTasks());
                        ui.printTaskAdded(cmdList, count);
                        count++;
                    }
                } else if (Parser.isDelete(cmd)) {
                    int itemNo = Parser.parseDeleteCmd(cmd);

                    // Make sure itemNo is within limit
                    if ((itemNo <= count) && (itemNo > 0)) {
                        // Remove item from lists
                        ui.printTaskDeleted(cmdList, itemNo - 1);
                        cmdList.remove(itemNo - 1);
                        storage.saveTasks(cmdList.getTasks());
                        count--;
                    } else {    // throw exception when itemNo not within limit
                        throw new DukeException("i cant seem to find the task you're looking for");
                    }
                } else {         // throw exception when command not found
                    // Error handling for todo, deadline & event
                    if (Parser.isEmptyTask(cmd)) {
                        throw new DukeException("the description of a " + Parser.getTaskName(cmd) + " cannot be empty");
                    } else {
                        throw new DukeException("im sorry, but i dont know what that means :(");
                    }
                }
                cmd = sc.nextLine();
            } catch (DukeException e) {
                ui.showDukeError(e);
                cmd = sc.nextLine();
            }
        }

        // when command given is "bye"
        ui.sayGoodbye();
    }

    /**
     * Starts the Duke chatbot.
     *
     * @param args Arguments to be passed to main function.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Converts Duke to string format.
     *
     * @return Duke as a string.
     */
    @Override
    public String toString() {
        return "im a chatbot ☺ - calico";
    }
}
