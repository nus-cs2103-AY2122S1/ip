import java.io.IOException;

public class Parser {
    String command;
    Ui ui;
    Storage storage;
    TaskList tasks;

    enum Activity {
        TODO, DONE, EVENT, DELETE, DEADLINE, BYE, LIST, NORMAL
    }

    public Parser (String command, Ui ui, Storage storage, TaskList tasks) {
        this.command = command;
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    public void parseCommand() throws DukeException, DeleteException, IOException {

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
                    throw new DukeException("todo");
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
                    throw new DukeException("event");
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
                    throw new DukeException("deadline");
                }

                int escapeIndex = command.lastIndexOf("/");
                String dateAndTime = command.substring(escapeIndex + 4);
                Deadline deadline = new Deadline(command.substring(9, escapeIndex - 1), dateAndTime);
                tasks.add(deadline);
                ui.showTaskMessage(deadline, tasks);
                storage.save(tasks);
                break;
            }
            default: {
                ui.showUnknownMessage();
                break;
            }
        }
    }
}
