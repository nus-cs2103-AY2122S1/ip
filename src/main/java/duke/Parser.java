package duke;

import java.io.IOException;
import java.util.List;

/**
 * Parser class to parse the input commands by the user.
 */
public class Parser {
    private String command;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private List<Storage> storageList;
    private List<Ui> uiList;
    private List<TaskList> tasksList;
    private Duke duke;

    enum Activity {
        TODO, DONE, EVENT, DELETE, DEADLINE, BYE, LIST, NORMAL, FIND, UNDO, HELP
    }

    /**
     * Constructs a Parser.
     *
     * @param command The input command of the user.
     * @param ui The Ui to return messages to the user.
     * @param storage To load or save tasks by the user.
     * @param tasks The list of tasks input by the user.
     * @param duke Current Duke.
     */
    public Parser(String command, Ui ui, Storage storage, TaskList tasks, List<Storage> storageList
            , List<Ui> uiList, List<TaskList> tasksList, Duke duke) {
        this.command = command;
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
        this.storageList = storageList;
        this.uiList = uiList;
        this.tasksList = tasksList;
        this.duke = duke;
    }

    /**
     * Parses the user command and inputs the task accordingly.
     *
     * @return String to be printed on GUI.
     * @throws DukeException If task input by user is incomplete.
     * @throws DeleteException If delete is incomplete.
     * @throws IOException If an input or output operation is failed or interpreted.
     * @throws FindException If Find is incomplete.
     * @throws DoneException If Done is incomplete.
     */
    public String parseCommand() throws DukeException, DeleteException, IOException, FindException,
            DoneException, UndoException, CloneNotSupportedException {

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
        } else if (command.startsWith("delete")) {
            activity = Activity.DELETE;
        } else if (command.startsWith("find")) {
            activity = Activity.FIND;
        } else if (command.startsWith("undo")) {
            activity = Activity.UNDO;
        } else if (command.startsWith("help")){
            activity = Activity.HELP;
        } else {
            activity = Activity.NORMAL;
        }

        switch (activity) {
        case BYE: {
            storage.setExit();
            return ui.goodbyeMessageToString();
        }
        case DONE: {
            int index;
            try {
                index = Integer.parseInt(command.substring(5)) - 1;
            } catch (NumberFormatException e) {
                return "Please input a correct number";
            }
            setNewLists(storage, tasks, ui);
            return new Done(index, tasks, storage, ui).execute();
        }
        case LIST: {
            return ui.listMessageToString(tasks);
        }
        case TODO: {
            ToDo todo = new ToDo(command, storage, tasks, ui);
            setNewLists(storage, tasks, ui);
            return todo.execute();
        }
        case EVENT: {
            Event event = new Event(command, storage, tasks, ui);
            setNewLists(storage, tasks, ui);
            return event.execute();
        }
        case DELETE: {
            int index;
            try {
                index = Integer.parseInt(command.substring(7)) - 1;
            } catch (NumberFormatException e) {
                return "Please input a correct number";
            }

            setNewLists(storage, tasks, ui);
            return new Delete(index, tasks, storage, ui).execute();
        }
        case DEADLINE: {
            Deadline deadline = new Deadline(command, storage, tasks, ui);
            setNewLists(storage, tasks, ui);
            return deadline.execute();
        }
        case FIND: {
            TaskList matchingTasks = new TaskList();

            String desc = command.substring(4);
            if (desc.isEmpty()) {
                throw new FindException();
            }
            assert desc.substring(1).length() > 0 : "Description should be present";
            for (Task t : tasks) {
                if (t.toString().contains(desc)) {
                    matchingTasks.add(t);
                }
            }
            if (matchingTasks.isEmpty()) {
                return ui.nothingFoundMessageToString();
            } else {
                return ui.listMessageToString("matching", matchingTasks);
            }
        }
        case UNDO: {
            return new Undo(storageList, uiList, tasksList, duke).execute();
        }
        case HELP: {
            return new Help().execute();
        }
        default: {
            return ui.unknownMessageToString();
        }
        }
    }

    /**
     * Adds a new Storage, TaskList and Ui to the current Lists for undo command.
     * @param storage Current Storage to be appended.
     * @param tasks Current TaskList to be appended.
     * @param ui Current Ui to be appended.
     * @throws CloneNotSupportedException If Cloning fails.
     */
    public void setNewLists(Storage storage, TaskList tasks, Ui ui) throws CloneNotSupportedException {
        this.storageList.add(storage.clone());
        this.tasksList.add((TaskList) tasks.clone());
        this.uiList.add(ui);
    }
}
