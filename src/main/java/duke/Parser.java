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
        String message = "";
        String[] inputArr = input.split(" ", 2);
        try {
            if (inputArr.length < 1) {
                throw new DukeException("You didn't put any commands.");
            }
            String[] messageArr;
            switch (inputArr[0]) {
            case "todo":
                if (inputArr.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                message = addTask(new Todo(inputArr[1]));
                break;
            case "deadline":
                if (inputArr.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                messageArr = inputArr[1].split(" /by ", 2);
                if (messageArr.length < 2) {
                    throw new DukeException("duke.Deadline needs a /by clause after the description.");
                }
                message = addTask(new Deadline(messageArr[0], messageArr[1]));
                break;
            case "event":
                if (inputArr.length < 2) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                messageArr = inputArr[1].split(" /at ", 2);
                message = addTask(new Event(messageArr[0], messageArr[1]));
                break;
            case "done":
                if (inputArr.length < 2) {
                    throw new DukeException("Please specify which task to delete.");
                }
                int number = Integer.parseInt(inputArr[1]);
                if (list.get(number - 1) == null) {
                    throw new DukeException("This task doesn't exist");
                }
                message += "    Nice! I've marked this task as done: \n";
                list.get(number - 1).markedAsDone();
                message += "      " + list.get(number - 1).toString();
                break;
            case "list":
                message = list.listItems();
                break;
            case "remove":
                if (inputArr.length < 2) {
                    throw new DukeException("Please specify which task to delete.");
                }
                int removeIndex = Integer.parseInt(inputArr[1]);
                if (list.get(removeIndex - 1) == null) {
                    throw new DukeException("This task doesn't exist");
                }
                message += "    Noted. I've removed this task: \n";
                message += "      " + list.get(removeIndex - 1).toString() + "\n";
                list.remove(removeIndex - 1);
                message += "     Now you have " + list.getSize() + " tasks in the list.";
                break;
            case "save":
                message = storage.save(list);
                break;
            case "find":
                if (inputArr.length < 2) {
                    throw new DukeException("The description of a find cannot be empty.");
                }
                message = list.find(inputArr[1]);
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

}
