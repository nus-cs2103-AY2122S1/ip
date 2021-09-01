package duke;

import duke.task.*;

/**
 * Wrapper of Duke's implementation.
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Initializes duke with default data save path.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        try {
            storage.initialize();
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Initializes duke with given data save path.
     * @param pathStr string of save path, ending with the name of save file
     */
    public Duke(String pathStr) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        try {
            storage.initialize(pathStr);
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Starts interacting with user. Exit the loop when detecting {@link duke.Parser#WORD_EXIT Parser.WORD_EXIT}.
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;

        try {
            if (!storage.isEmpty()) {
                taskList.load(storage.getFileContents());
            }
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }

        // Echo loop till exit word is entered
        while (!isExit) {
            String userInput = ui.readCommand();

            try {
                Ui.printDividerLine();

                if (userInput.contains("|")) {
                    throw new DukeException(ExceptionType.PIPE_SYMBOL);
                }

                DukeAction dukeAction = Parser.stringToDukeAction(userInput, taskList.size());

                switch (dukeAction) {
                case EXIT:
                    Ui.printGoodbyeMessage();
                    isExit = true;
                    break;
                case PRINT_LIST:
                    Ui.printList(taskList);
                    break;
                case MARK_DONE:
                    onTaskDone(Parser.parseMarkString(userInput));
                    break;
                case DELETE:
                    onTaskRemoved(Parser.parseDeleteString(userInput));
                    break;
                case FIND:
                    onFindTask(Parser.parseFindString(userInput));
                    break;
                case TODO:
                    ToDo toDo = new ToDo(userInput.substring(5));
                    onNewTaskAdded(toDo);
                    break;
                case DEADLINE:
                    String[] strArr = Parser.parseDeadlineString(userInput);
                    Deadline deadline = new Deadline(strArr[0], strArr[1]);
                    onNewTaskAdded(deadline);
                    break;
                case EVENT:
                    String[] strArr_ = Parser.parseEventString(userInput);
                    Event event = new Event(strArr_[0], strArr_[1]);
                    onNewTaskAdded(event);
                    break;
                }
                Ui.printDividerLine();
            } catch (DukeException e) {
                Ui.printErrorMessage(e, userInput);
                Ui.printDividerLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    // Handlers

    private void onFindTask(String keyWord) {
        Ui.printFoundTasks(taskList.stream()
                .filter(t -> t.getDescription().contains(keyWord))
                .map(Task::toString)
                .toArray(String[]::new)
        );
    }

    private <T extends Task> void onNewTaskAdded(T t) throws DukeException{
        taskList.add(t);
        Ui.printNewTask(t.toString());
        Ui.printTaskCount(taskList.size());
        storage.writeLine(t.populateSaveData() + System.lineSeparator());
    }

    private void onTaskRemoved(int index) throws DukeException {
        storage.removeLine(index);
        Ui.printRemoveTask(taskList.get(index).toString());
        taskList.remove(index);
        Ui.printTaskCount(taskList.size());
    }

    private void onTaskDone(int index) throws DukeException {
        Task task = taskList.get(index);
        storage.setLine(index, task.toString());
        task.setStatus(true);
        Ui.printMarkDone(task.toString());
    }
}
