package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Parser extends Application {

    static final int BYE_LENGTH = 3;
    static final int LIST_LENGTH = 4;
    static final int DONE_LENGTH = 4;
    static final int TODO_LENGTH = 4;
    static final int DEADLINE_LENGTH = 8;
    static final int EVENT_LENGTH = 5;
    static final int DELETE_LENGTH = 6;
    static final int FIND_LENGTH = 4;
    static final int INVALID_INPUT = 0;
    static final int BYE_INPUT = 1;
    static final int LIST_INPUT = 2;
    static final int DONE_INPUT = 3;
    static final int TODO_INPUT = 4;
    static final int DEADLINE_INPUT = 5;
    static final int EVENT_INPUT = 6;
    static final int DELETE_INPUT = 7;
    static final int FIND_INPUT = 8;
    private final Ui ui;
    private final Storage storage;
    /**
     * Creates a Parser that processes the user's inputs.
     *
     * @param ui Ui object which interacts with the users.
     * @param storage Storage object which saves and retrieves files.
     */
    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
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
        if (str.length() == BYE_LENGTH) {
            isBye = str.startsWith("bye");
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
        if (str.length() == LIST_LENGTH) {
            isList = str.startsWith("list");
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
        if (str.length() >= DONE_LENGTH) {
            isDone = str.startsWith("done");
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
        if (str.length() >= TODO_LENGTH) {
            isTodo = str.startsWith("todo");
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
        if (str.length() >= DEADLINE_LENGTH) {
            isDeadLine = str.startsWith("deadline");
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
        if (str.length() >= EVENT_LENGTH) {
            isEvent = str.startsWith("event");
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
        if (str.length() >= DELETE_LENGTH) {
            isDelete = str.startsWith("delete");
        }
        return isDelete;
    }

    private boolean findChecker(String str) {
        boolean isFind = false;
        if (str.length() >= FIND_LENGTH) {
            isFind = str.startsWith("find");
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
        int caseNum = INVALID_INPUT;

        if (byeChecker(input)) {
            caseNum = BYE_INPUT;
        } else if (listChecker(input)) {
            caseNum = LIST_INPUT;
        } else if (doneChecker(input)) {
            caseNum = DONE_INPUT;
        } else if (todoChecker(input)) {
            caseNum = TODO_INPUT;
        } else if (deadlineChecker(input)) {
            caseNum = DEADLINE_INPUT;
        } else if (eventChecker(input)) {
            caseNum = EVENT_INPUT;
        } else if (deleteChecker(input)) {
            caseNum = DELETE_INPUT;
        } else if (findChecker(input)) {
            caseNum = FIND_INPUT;
        }
        return caseNum;
    }

    private String listInput(TaskList taskList) throws InputError {
        String response = "";
        try {
            if (taskList.size() == 0) {
                throw new InputError("No items in list");
            }
            response = taskList.printList();
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    private String doneInput(String str, TaskList taskList) throws InputError {
        String response = "";
        try {
            if (str.length() == DONE_LENGTH) {
                throw new InputError("No task indicated");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            if (indexNum <= 0 || indexNum > taskList.size()) {
                throw new InputError("Invalid Number");
            }
            response = taskList.doneTask(indexNum);
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    private String findInput(String str, TaskList taskList) throws InputError {
        String response = "";
        try {
            if (str.length() == FIND_LENGTH) {
                throw new InputError("No task indicated");
            }
            String searchWord = str.substring(FIND_LENGTH + 1);
            TaskList foundList = taskList.findTasks(searchWord);
            if (foundList.currList().isEmpty()) {
                throw new InputError("No such tasks found");
            }
            response = "We found these for you boss:\n" + foundList.printList();
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    private String todoInput(String str, TaskList taskList) throws InputError {
        String response = "";
        try {
            if (str.length() == TODO_LENGTH) {
                throw new InputError("Description Please!");
            }
            response = taskList.addTodo(str);
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    private String deadlineInput(String str, TaskList taskList) throws InputError {
        String response = "";
        try {
            if (str.length() == DEADLINE_LENGTH) {
                throw new InputError("Description Please!");
            }
            response = taskList.addDeadline(str);
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    private String eventInput(String str, TaskList taskList) throws InputError {
        String response = "";
        try {
            if (str.length() == EVENT_LENGTH) {
                throw new InputError("Description Please!");
            }
            response = taskList.addEvent(str);
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    private String deleteInput(String str, TaskList taskList) throws InputError {
        String response = "";
        try {
            if (str.length() == DELETE_LENGTH) {
                throw new InputError("No Task to delete");
            }
            int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            if (indexNum <= 0 || indexNum > taskList.size()) {
                throw new InputError("Invalid Number!");
            }
            response = taskList.deleteTask(indexNum);
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    /**
     * Handles the logic for different command cases.
     *
     * @param caseNum Case Number of the command.
     * @param input Current user input.
     * @param taskList Current TaskList being used.
     * @throws InputError If user input is invalid or unrecognised.
     */
    public String caseHandler(int caseNum, String input, TaskList taskList) throws InputError {
        String response = "";
        switch (caseNum) {
        case BYE_INPUT:
            response = ui.byeMessage();
            Platform.exit();
            break;
        case LIST_INPUT:
            response = listInput(taskList);
            break;
        case DONE_INPUT:
            response = doneInput(input, taskList);
            storage.fileSaver(taskList.currList());
            break;
        case TODO_INPUT:
            response = todoInput(input, taskList);
            storage.fileSaver(taskList.currList());
            break;
        case DEADLINE_INPUT:
            response = deadlineInput(input, taskList);
            storage.fileSaver(taskList.currList());
            break;
        case EVENT_INPUT:
            response = eventInput(input, taskList);
            storage.fileSaver(taskList.currList());
            break;
        case DELETE_INPUT:
            response = deleteInput(input, taskList);
            storage.fileSaver(taskList.currList());
            break;
        case FIND_INPUT:
            response = findInput(input, taskList);
            break;
        default:
            response = ui.invalidInput();
        }
        return response;
    }

    /**
     * Empty start method to extend the Application class.
     *
     * @param stage Method is not used.
     */
    @Override
    public void start(Stage stage) {
    }
}
