public class Duke {
    private Ui ui;
    private Storage storage;
    protected TaskList taskList;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
    }
    
    public Duke(String pathStr) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(pathStr);
    }
    

    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;

        if (!storage.isEmpty()) {
            taskList.add(storage.getFileContents());
        }

        // Echo loop till exit word is entered
        while (!isExit) {
            String userInput = ui.readCommand();

            try {
                Ui.printDividerLine();

                if (userInput.contains("|")) {
                    throw new DukeException("OOPS!!! Duke cannot identify the symbol \"|\". Please do not include it " +
                            "within your input :)");
                }

                DukeAction dukeAction = Parser.stringToDukeAction(userInput, taskList.size());

                switch (dukeAction) {
                case EXIT:
                    Ui.printGoodbyeMessage();
                    isExit = true;
                    break;
                case PRINT_LIST:
                    Ui.printList(taskList.toStringArr());
                    break;
                case MARK_DONE:
                    onTaskDone(Parser.parseMarkString(userInput));
                    break;
                case DELETE:
                    onTaskRemoved(Parser.parseDeleteString(userInput));
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

    private <T extends Task> void onNewTaskAdded(T t) {
        taskList.add(t);
        storage.writeLine(t.populateSaveData() + System.lineSeparator());
        Ui.printNewTask(t);
        Ui.printTaskCount(taskList.size());
    }

    private void onTaskRemoved(int index) {
        Ui.printWithIndent("Noted. I've removed this task: ");
        Ui.printWithIndent("  " + taskList.get(index));
        taskList.remove(index);
        storage.removeLine(index);
        Ui.printTaskCount(taskList.size());
    }

    private void onTaskDone(int index) {
        Task task = taskList.get(index);
        task.isDone = true;
        storage.setLine(index, task.toString());
        Ui.printWithIndent("Nice! I've marked this task as done: ");
        Ui.printWithIndent("  " + task);
    }
}
