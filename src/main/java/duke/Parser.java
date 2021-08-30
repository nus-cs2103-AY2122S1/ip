package duke;

public class Parser {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a Parser that processes the user's inputs.
     *
     * @param ui Ui object which interacts with the users.
     * @param taskList TaskList object which stores the list of tasks.
     * @param storage Storage object which saves and retrieves files.
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Checks whether user input is a bye command.
     *
     * @param str User input.
     * @return True if bye command. False if not bye command.
     */
    private boolean byeChecker(String str) {
        boolean isBye = false;
        if (str.length() >= 3) {
            isBye = str.equals("bye");
        }
        return isBye;
    }

    /**
     * Checks whether user input is a list command.
     *
     * @param str User input.
     * @return True if list command. False if not list command.
     */
    private boolean listChecker(String str) {
        boolean isList = false;
        if (str.length() >= 4) {
            isList = str.equals("list");
        }
        return isList;
    }

    /**
     * Checks whether user input is a check command.
     *
     * @param str User input.
     * @return True if check command. False if not check command.
     */
    private boolean doneChecker(String str) {
        boolean isDone = false;
        if (str.length() >= 4) {
            isDone = str.substring(0,4).equals("done");
        }
        return isDone;
    }

    /**
     * Checks whether user input is a To Do command.
     *
     * @param str User input.
     * @return True if To Do command. False if not To Do command.
     */
    private boolean todoChecker(String str) {
        boolean isTodo = false;
        if (str.length() >= 4) {
            isTodo = str.substring(0,4).equals("todo");
        }
        return isTodo;
    }

    /**
     * Checks whether user input is a Deadline command.
     *
     * @param str User input.
     * @return True if Deadline command. False if not Deadline command.
     */
    private boolean deadlineChecker(String str) {
        boolean isDeadLine = false;
        if (str.length() >= 8) {
            isDeadLine = str.substring(0,8).equals("deadline");
        }
        return isDeadLine;
    }

    /**
     * Checks whether user input is a Event command.
     *
     * @param str User input.
     * @return True if Event command. False if not Event command.
     */
    private boolean eventChecker(String str) {
        boolean isEvent = false;
        if (str.length() >= 5) {
            isEvent = str.substring(0,5).equals("event");
        }
        return isEvent;
    }

    /**
     * Checks whether user input is a delete command.
     *
     * @param str User input.
     * @return True if delete command. False if not delete command.
     */
    private boolean deleteChecker(String str) {
        boolean isDelete = false;
        if (str.length() >= 6) {
            isDelete = str.substring(0,6).equals("delete");
        }
        return isDelete;
    }

    private boolean findChecker(String str) {
        boolean isFind = false;
        if (str.length() >= 4) {
            isFind = str.substring(0,4).equals("find");
        }
        return isFind;
    }

    /**
     * Checks what command is the user input.
     *
     * @param input User input.
     * @return Case number of command.
     */
    public int caseChecker(String input) {
        int caseNum = 0;

        if (byeChecker(input)) {
            caseNum = 1;
        } else if (listChecker(input)) {
            caseNum = 2;
        } else if (doneChecker(input)) {
            caseNum = 3;
        } else if (todoChecker(input)) {
            caseNum = 4;
        } else if (deadlineChecker(input)) {
            caseNum = 5;
        } else if (eventChecker(input)) {
            caseNum = 6;
        } else if (deleteChecker(input)) {
            caseNum = 7;
        } else if (findChecker(input)) {
            caseNum = 8;
        }
        return caseNum;
    }

    private void listInput(TaskList taskList) throws InputError {
        try {
            if (taskList.size() == 0) {
                throw new InputError("No items in list");
            }
            taskList.printList();
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void doneInput(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("No task indicated");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            taskList.doneItem(indexNum);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void findInput(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("No task indicated");
            }
            String searchWord = str.substring(5);
            TaskList foundList = taskList.findTasks(searchWord);
            if (foundList.currList().isEmpty()) {
                throw new InputError("No such tasks found");
            }
            System.out.println("We found these for you boss:");
            foundList.printList();
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void todoInput(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 4) {
                throw new InputError("Description Please!");
            }
            taskList.addTodo(str);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void deadlineInput(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 8) {
                throw new InputError("Description Please!");
            }
            taskList.addDeadline(str);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void eventInput(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 5) {
                throw new InputError("Description Please!");
            }
            taskList.addEvent(str);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    private void deleteInput(String str, TaskList taskList) throws InputError {
        try {
            if (str.length() == 5) {
                throw new InputError("No Task to delete");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            taskList.deleteItem(indexNum);
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Handles the logic for different command cases.
     *
     * @param caseNum Case Number of the command.
     * @param input Current user input.
     * @param taskList Current TaskList being used.
     * @throws InputError If user input is invalid or unrecognised.
     */
    public void caseHandler(int caseNum, String input, TaskList taskList) throws InputError {
        switch (caseNum) {
            case 2:
                listInput(taskList);
                break;
            case 3:
                doneInput(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 4:
                todoInput(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 5:
                deadlineInput(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 6:
                eventInput(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 7:
                deleteInput(input, taskList);
                storage.fileSaver(taskList.currList());
                break;
            case 8:
                findInput(input, taskList);
                break;
            default:
                ui.invalidInput();
        }
    }
}
