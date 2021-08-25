package duke;

import java.io.IOException;

/**
 * Parser class to parse the input commands by the user.
 */
public class Parser {
    String command;
    Ui ui;
    Storage storage;
    TaskList tasks;

    enum Activity {
        TODO, DONE, EVENT, DELETE, DEADLINE, BYE, LIST, NORMAL, FIND
    }

    /**
     * Public constructor for Parser.
     *
     * @param command The input command of the user.
     * @param ui The Ui to return messages to the user.
     * @param storage To load or save tasks by the user.
     * @param tasks The list of tasks input by the user.
     */
    public Parser(String command, Ui ui, Storage storage, TaskList tasks) {
        this.command = command;
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Returns a boolean, whether a program should end or not.
     *
     * @return True if program should still run, false otherwise.
     */
    public boolean getRun() {
        return !storage.isExit();
    }

    /**
     * Parses the user command and inputs the task accordingly.
     *
     * @throws DukeException If task input by user is incomplete.
     * @throws DeleteException If delete is incomplete.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    public void parseCommand() throws DukeException, DeleteException, IOException, FindException {

        Activity activity;
        if (command.equals("bye")) {
            activity = Activity.BYE;
        } else if (command.equals("list")) {
            activity = Activity.LIST;
        } else if (command.startsWith("done")) {
            activity = Activity.DONE;
        } else if (command.startsWith("todo")) {
            activity = Activity.TODO;
        } else if (command.startsWith("event")) {
            activity = Activity.EVENT;
        } else if (command.startsWith("deadline")) {
            activity = Activity.DEADLINE;
        } else if (command.startsWith("delete")){
            activity = Activity.DELETE;
        } else if (command.startsWith("find")) {
            activity = Activity.FIND;
        } else {
            activity = Activity.NORMAL;
        }

        switch (activity) {
            case BYE: {
                ui.showGoodbyeMessage();
                storage.setExit();
                return;
            }
            case DONE: {
                int index = Integer.parseInt(command.substring(5)) - 1;
                Task currentTask = tasks.get(index);
                currentTask.setDone();
                ui.showDoneMessage(currentTask);
                storage.save(tasks);
                break;
            }
            case LIST: {
                ui.showListMessage(tasks);
                break;
            }
            case TODO: {
                String desc = command.substring(4);

                if (desc.isEmpty()) {
                    throw new DukeException("todo", "'todo borrow book'");
                }

                ToDo toDo = new ToDo(command.substring(5));
                tasks.add(toDo);
                ui.showTaskMessage(toDo, tasks);
                storage.save(tasks);
                break;
            }
            case EVENT: {
                String desc = command.substring(5);

                if (desc.isEmpty()) {
                    throw new DukeException("event", "'event project meeting /at Aug 26 2021 19:15'");
                }

                int escapeIndex = command.lastIndexOf("/");
                String dateAndTime = command.substring(escapeIndex + 4);
                Event event = new Event(command.substring(6, escapeIndex - 1), dateAndTime);
                tasks.add(event);
                ui.showTaskMessage(event, tasks);
                storage.save(tasks);
                break;
            }
            case DELETE: {
                int index = Integer.parseInt(command.substring(7)) - 1;

                if (index >= tasks.size()) {
                    throw new DeleteException();
                }

                Task currentTask = tasks.get(index);
                tasks.remove(index);
                ui.showDeleteMessage(currentTask, tasks);
                storage.save(tasks);
                break;
            }
            case DEADLINE: {
                String desc = command.substring(8);

                if (desc.isEmpty()) {
                    throw new DukeException("deadline", "'deadline return book /by 2021-08-27 14:15'");
                }

                int escapeIndex = command.lastIndexOf("/");
                String dateAndTime = command.substring(escapeIndex + 4);
                Deadline deadline = new Deadline(command.substring(9, escapeIndex - 1), dateAndTime);
                tasks.add(deadline);
                ui.showTaskMessage(deadline, tasks);
                storage.save(tasks);
                break;
            }
            case FIND: {
                TaskList matchingTasks = new TaskList();
                String desc = command.substring(4);

                if (desc.isEmpty()) {
                    throw new FindException();
                }

                for (Task t : tasks) {
                    if (t.toString().contains(desc)) {
                        matchingTasks.add(t);
                    }
                }
                if (matchingTasks.isEmpty()) {
                    ui.showNothingFoundMessage();
                } else {
                    ui.showListMessage("matching", matchingTasks);
                }
                break;
            }
            default: {
                ui.showUnknownMessage();
                break;
            }
        }
    }
}
