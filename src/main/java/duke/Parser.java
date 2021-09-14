package duke;

/**
 * Parser class handles input messages from the user
 */
public class Parser {

    private TaskList list;
    private Storage storage;

    /**
     * Adds a task list to the parser class
     *
     * @param list Task list of tasks
     */
    public void addTaskList(TaskList list) {
        this.list = list;
    }

    /**
     * Adds a storage to allow the parser to save
     *
     * @param storage Storage containing the file to write to
     */
    public void addStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Handles each command the user inputs. Different commands have different actions.
     *
     * @param input Input string by user
     * @return True or false. If false, program ends. If true, program continues.
     */
    public String parse(String input) {
        assert list != null : "TaskList is null";
        assert storage != null : "Storage is null";
        String message = "";
        String[] inputArr = input.split(" ", 2);
        try {
            if (inputArr.length < 1) {
                throw new DukeException("You didn't put any commands.");
            }
            switch (inputArr[0]) {
            case "todo":
                message = addTodo(inputArr);
                break;
            case "deadline":
                message = addDeadline(inputArr);
                break;
            case "event":
                message = addEvent(inputArr);
                break;
            case "done":
                message = markDone(inputArr);
                break;
            case "list":
                message = list.listItems();
                break;
            case "remove":
                message = removeTask(inputArr);
                break;
            case "save":
                message = storage.save(list);
                break;
            case "find":
                message = findInTaskList(inputArr);
                break;
            case "tag":
                message = tagItem(inputArr);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException de) {
            message = de.getMessage();
        }
        return message;
    }

    private String addTask(Task t) {
        String message = "     Got it. I've added this task: \n";
        list.add(t);
        message += "      " + t.toString() + "\n";
        message += "     Now you have " + list.getSize() + " tasks in the list.";
        return message;
    }

    private String addTodo(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return addTask(new Todo(inputArr[1]));
    }

    private String addDeadline(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] messageArr = inputArr[1].split(" /by ", 2);
        if (messageArr.length < 2) {
            throw new DukeException("Deadline needs a /by clause after the description.");
        }
        return addTask(new Deadline(messageArr[0], messageArr[1]));
    }

    private String addEvent(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] messageArr = inputArr[1].split(" /at ", 2);
        return addTask(new Event(messageArr[0], messageArr[1]));
    }

    private String markDone(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please specify which task to delete.");
        }
        int number = Integer.parseInt(inputArr[1]);
        if (list.get(number - 1) == null) {
            throw new DukeException("This task doesn't exist");
        }
        String message = " Nice! I've marked this task as done: \n";
        list.get(number - 1).markedAsDone();
        message += list.get(number - 1).toString();
        return message;
    }

    private String removeTask(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please specify which task to delete.");
        }
        int removeIndex = Integer.parseInt(inputArr[1]);
        if (list.get(removeIndex - 1) == null) {
            throw new DukeException("This task doesn't exist");
        }
        String message = "Noted. I've removed this task: \n";
        message += list.get(removeIndex - 1).toString() + "\n";
        list.remove(removeIndex - 1);
        message += "Now you have " + list.getSize() + " tasks in the list.";
        return message;
    }

    private String findInTaskList(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("The description of a find cannot be empty.");
        }
        return list.find(inputArr[1]);
    }

    private String tagItem(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please specify which task to tag.");
        }
        String[] messageArr = inputArr[1].split(" /tag ", 2);
        if (messageArr.length < 2) {
            throw new DukeException("Please specify the tag with /tag.");
        }
        int tagIndex = Integer.parseInt(messageArr[0]) - 1;
        list.get(tagIndex).setTag(messageArr[1]);
        return "Successfully task " + tagIndex + " set tag to " + messageArr[1];
    }
}
