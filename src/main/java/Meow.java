import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chat bot Meow who will be performing different tasks
 * according to user input.
 */
public class Meow {
    private boolean isExit = false;
    private List<Task> tasksList = new ArrayList<>();
    enum Command {
        BYE,
        LIST,
        DONE,
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * A public constructor to initialize a Meow object.
     */
    public Meow() {

    }

    /**
     * Print the greeting message from the chat bot cat Meow.
     *
     * @return A boolean indicating whether the user wants to exit to
     * chat bot or nor.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Print the greeting message from the chat bot cat Meow.
     */
    public void greet() {

        System.out.println(
                "------------------------------------------------------------------------------\n" +
                "Meow: Hi human, I'm your cat Meow~ What can I do for you?\n" +
                "------------------------------------------------------------------------------"
        );
    }


    /**
     * Print the goodbye message from the chat bot cat Meow.
     */
    private void exit() {

        System.out.println(
                "------------------------------------------------------------------------------\n" +
                        "Meow: Bye human, see you soon! Your cat Meow is going to sleep now~\n" +
                        "------------------------------------------------------------------------------"
        );
    }


    private void displayList() throws NoItemInTheListException {
        int len = tasksList.size();
        if (len > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < len; i++) {
                Task task = tasksList.get(i);
                System.out.println(i + 1 + ". " + task.toString());
            }
        } else {
            throw new NoItemInTheListException();
        }
    }

    /**
     * Check whether the task is able to be marked as done, 0 indicating that
     * this is an invalid task, Integer.MAX_VALUE indicating that this task is
     * not in the task list, any number other than 0 or Integer.MAX_VALUE indicating
     * the task number to be marked as done.
     *
     * @param index The task number that the user wants to mark as done.
     * @return An integer indicating which task to be marked as done.
     */
    private void completeTask(String index) throws MeowException{
        try {
            int taskNumber = Integer.parseInt(index);
            if (taskNumber <= tasksList.size() && taskNumber > 0) {
                Task completedTask = tasksList.get(taskNumber - 1);
                completedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.getDescription());
            } else {
                throw new InvalidTaskIndex();
            }
        } catch (NumberFormatException exception) {
            // String cannot be parsed to integer
           throw new NotSuchTaskFoundException();
        }

    }

    private void addTodo(String todo) {
        Todo newTodo = new Todo(todo);
        tasksList.add(newTodo);
        int taskListLength = tasksList.size();
        String task = taskListLength <= 1 ? " task " : " tasks ";
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + taskListLength + task + "in the list.");
    }

    private void addDeadline(String deadline, String by) {
        Deadline newDeadline = new Deadline(deadline, by);
        tasksList.add(newDeadline);
        int taskListLength = tasksList.size();
        String task = taskListLength <= 1 ? " task " : " tasks ";
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + taskListLength + task + "in the list.");
    }

    private void addEvent(String event, String at) {
        Event newEvent = new Event(event, at);
        tasksList.add(newEvent);
        int taskListLength = tasksList.size();
        String task = taskListLength <= 1 ? " task " : " tasks ";
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + taskListLength + task + "in the list.");
    }

    private String getTask(String input, Command typeOfTask) throws MeowException {
        try {
            return input.trim().substring(typeOfTask.toString().length() + 1);
        } catch (StringIndexOutOfBoundsException exception) {
            if (typeOfTask.equals(Command.TODO)) {
                throw new EmptyTodoDescriptionException();
            } else if (typeOfTask.equals(Command.DEADLINE)) {
                throw new EmptyDeadlineDescriptionException();
            } else {
                throw new EmptyEventDescriptionException();
            }

        }

    }

    private String[] getTaskAndDate(String task, Command typeOfTask) {
        String[] split;
        if (typeOfTask.equals(Command.DEADLINE)) {
            split = task.split(" /by ");
        } else {
            split = task.split(" /at ");
        }
        return split;
    }

    /**
     * The chat bot will perform different tasks according to
     * the command that the user has entered.
     *
     * @param input The input command from the user.
     * @throws MeowException If the user input is invalid.
     */
    public void checkCommand(String input) throws MeowException {
        try {
            String[] commandWord = input.split(" ");
            Command userCommand = Command.valueOf(commandWord[0].trim().toUpperCase());
            switch (userCommand) {
                case BYE: {
                    exit();
                    isExit = true;
                    break;
                }
                case LIST: {
                    displayList();
                    break;
                }
                case DONE: {
                    completeTask(commandWord[1].trim());
                    break;
                }
                case TODO: {
                    String task = getTask(input, userCommand);
                    addTodo(task);
                    break;
                }
                case EVENT: {
                    String task = getTask(input, userCommand);
                    String[] taskAndDate = getTaskAndDate(task, userCommand);
                    try {
                        addEvent(taskAndDate[0], taskAndDate[1]);
                        break;
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        throw new EmptyEventTimeException();
                    }
                }
                case DEADLINE: {
                    String task = getTask(input, userCommand);
                    String[] taskAndDate = getTaskAndDate(task, userCommand);
                    try {
                        addDeadline(taskAndDate[0], taskAndDate[1]);
                        break;
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        throw new EmptyDeadlineTimeException();
                    }

                }
                default: {
                    throw new InvalidInputException();
                }
            }
        } catch (IllegalArgumentException exception) {
            throw new InvalidInputException();
        }

    }


}
