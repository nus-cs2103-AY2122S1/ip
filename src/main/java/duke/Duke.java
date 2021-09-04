package duke;

// import java packages
import java.util.ArrayList;

// import duke packages
import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A chatbot that stores tasks given by a user.
 */
public class Duke {
    private Storage storage;
    private TaskList commandList;
    private Ui ui;

    /**
     * Creates a Duke chatbot.
     *
     * @param filePath Filepath to save tasks and load tasks.
     */
    public Duke(String filePath) {
        ui = new Ui("", "");
        storage = new Storage(filePath);
        try {
            commandList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            commandList = new TaskList();
        }
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

    /**
     * Generates a response to user input.
     *
     * @param cmd Command from user
     * @return Chatbot response
     */
    public String getResponse(String cmd) {
        int count = 0 + commandList.getLength();

        if (Parser.isNotBye(cmd)) {
            try {
                // when command given is "list"
                if (Parser.isList(cmd)) {
                    return ui.showList(commandList);
                } else if (Parser.isDone(cmd)) {
                    // when command given is "done"

                    int itemNo = Parser.parseDoneCmd(cmd);

                    // Make sure itemNo is within limit
                    if ((itemNo <= count) && (itemNo > 0)) {
                        commandList.markDone(itemNo - 1);
                        storage.saveTasks(commandList.getTasks());
                    } else {
                        // throw exception when itemNo not within limit
                        throw new DukeException("i cant seem to find the task you're looking for");
                    }
                    return ui.showTaskDone(commandList, itemNo - 1);
                } else if (Parser.isValidTask(cmd)) {
                    String task = Parser.getTaskName(cmd);
                    String desc = Parser.getDesc(cmd);
                    if (Parser.isMissingArg(cmd)) {
                        // when desc is not empty
                        // case when only a whitespace follows a command
                        throw new DukeException("the description of a " + task + " cannot be empty");
                    } else {
                        // Separate into cases
                        switch (task) {
                        case "todo":
                            commandList.add(new Todo(desc));
                            break;
                        case "deadline":
                            String deadlineInfo = Parser.getDeadlineInfo(desc);
                            String deadlineDue = Parser.getDeadlineDue(desc);
                            commandList.add(new Deadline(deadlineInfo, deadlineDue));
                            break;
                        case "event":
                            String eventInfo = Parser.getEventInfo(desc);
                            String eventDue = Parser.getEventDue(desc);
                            commandList.add(new Event(eventInfo, eventDue));
                            break;
                        default:
                            // should not happen
                            throw new DukeException("the name of task is not valid");
                        }
                        storage.saveTasks(commandList.getTasks());
                        count++;
                        return ui.showTaskAdded(commandList, count - 1);
                    }
                } else if (Parser.isDelete(cmd)) {
                    int itemNo = Parser.parseDeleteCmd(cmd);

                    // Make sure itemNo is within limit
                    if ((itemNo <= count) && (itemNo > 0)) {
                        // Remove item from lists
                        String deletionMessage = ui.showTaskDeleted(commandList, itemNo - 1);
                        commandList.remove(itemNo - 1);
                        storage.saveTasks(commandList.getTasks());
                        count--;
                        return deletionMessage;
                    } else {
                        // throw exception when itemNo not within limit
                        throw new DukeException("i cant seem to find the task you're looking for");
                    }
                } else if (Parser.isFind(cmd)) {
                    String desc = Parser.getDesc(cmd);
                    if (Parser.isMissingArg(cmd)) {
                        // when desc is not empty
                        // case when only a whitespace follows a command
                        throw new DukeException("the description of a find cannot be empty");
                    }
                    ArrayList<Task> matchingTasks = commandList.find(desc);
                    if (matchingTasks.isEmpty()) {
                        return ui.showNoMatch();
                    } else {
                        return ui.showMatchingTasks(matchingTasks);
                    }
                } else {
                    // throw exception when command not found
                    // Error handling for todo, deadline & event
                    if (Parser.isEmptyTask(cmd)) {
                        throw new DukeException("the description of a " + Parser.getTaskName(cmd) + " cannot be empty");
                    } else {
                        throw new DukeException("im sorry, but i dont know what that means :(");
                    }
                }
            } catch (DukeException e) {
                return ui.showDukeError(e);
            }
        }

        // when command given is "bye"
        return ui.sayGoodbye();
    }

    /**
     * Say hello to user.
     *
     * @return Hello message.
     */
    public String intro() {
        return ui.sayHello();
    }
}
