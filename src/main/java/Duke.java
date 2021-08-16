import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * The list of tasks that are inputted by the user.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * The number of tasks.
     */
    private static int noOfTasks = 0;

    /**
     * All the recognised commands.
     */
    protected enum Command {
        DONE("done"),
        BYE("bye"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private String name;

        Command(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println("__________________________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    private static void greetUser() {
        printLine();
        System.out.println("Greetings! I am Duke.");
        System.out.println("How can I assist you?");
        printLine();
    }

    /**
     * Prints the farewell message.
     */
    private static void farewellUser() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }

    /**
     * Adds the task to the list and prints the added task.
     *
     * @param task the task that will be added to the list
     */
    private static void addToList(Task task) {
        taskList.add(task);
        noOfTasks++;
        System.out.printf("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.%n",
            task, noOfTasks);
    }

    /**
     * Prints all the tasks in the list in order.
     */
    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.get(i));
        }
    }

    /**
     * Marks the task with taskNo specified and prints the task completed.
     *
     * @param splitInput the user input split by space.
     * @throws NoTaskNumException if no task number for done is inputted.
     * @throws InvalidTaskNumException if invalid task number is inputted.
     */
    private static void markTaskAsDone(String[] splitInput) throws NoTaskNumException, InvalidTaskNumException {
        try {
            // If splitInput length is 1, that means no number is inputted.
            if (splitInput.length == 1) {
                throw new NoTaskNumException();
            }
            int taskNo = Integer.parseInt(splitInput[1]);
            Task task = taskList.get(taskNo - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("  %s%n", task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // When non numeric or out of bounds task number is inputted.
            throw new InvalidTaskNumException();
        }
    }

    /**
     * Creates todo when given userInput.
     *
     * @param userInput the input by the user.
     * @return the todo inputted by the user.
     * @throws NoDescriptionException if no description is inputted.
     */
    public static Todo createTodo(String userInput) throws NoDescriptionException {
        String description = userInput.substring(4).trim();
        if (description.length() == 0) {
            throw new NoDescriptionException(Command.TODO);
        }
        return new Todo(description);
    }

    /**
     * Creates deadline when given userInput.
     *
     * @param userInput the input by the user.
     * @return the deadline inputted by the user.
     * @throws NoDateTimeInputException if no day of deadline is inputted.
     * @throws NoDescriptionException if no description is inputted.
     */
    public static Deadline createDeadline(String userInput) throws NoDateTimeInputException, NoDescriptionException {
        String[] splitByBy = userInput.split(" /by ");
        if (splitByBy.length == 1) {
            throw new NoDateTimeInputException(Command.DEADLINE);
        }
        String description = splitByBy[0].substring(8).trim();
        if (description.length() == 0) {
            throw new NoDescriptionException(Command.DEADLINE);
        }
        String by = splitByBy[1];
        return new Deadline(description, by);
    }

    /**
     * Creates event when given userInput.
     *
     * @param userInput the input by the user.
     * @return the event inputted by the user.
     * @throws NoDateTimeInputException if no date and time of event is inputted.
     * @throws NoDescriptionException if no description is inputted.
     */
    public static Event createEvent(String userInput) throws NoDateTimeInputException, NoDescriptionException {
        String[] splitByAt = userInput.split(" /at ");
        if (splitByAt.length == 1) {
            throw new NoDateTimeInputException(Command.EVENT);
        }
        String description = splitByAt[0].substring(5).trim();
        if (description.length() == 0) {
            throw new NoDescriptionException(Command.EVENT);
        }
        String dayTime = splitByAt[1];
        return new Event(description, dayTime);
    }

    /**
     * Interacts with the user.
     */
    public static void interactWithUser() {
        greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] splitInput = userInput.split(" ");
        while (!userInput.equalsIgnoreCase("bye")) {
            try {
                printLine();
                if (userInput.equals("list")) {
                    printTasks();
                } else {
                    switch (splitInput[0]) {
                        case "done":
                            markTaskAsDone(splitInput);
                            break;
                        case "todo":
                            addToList(createTodo(userInput));
                            break;
                        case "deadline":
                            addToList(createDeadline(userInput));
                            break;
                        case "event":
                            addToList(createEvent(userInput));
                            break;
                        default:
                            throw new UnknownCommandException(splitInput[0]);
                    }
                }
            } catch (NoTaskNumException e) {
                System.out.println("☹ OOPS!!! The task number cannot be empty.");
            } catch (InvalidTaskNumException e) {
                System.out.println("☹ OOPS!!! The task number inputted is invalid.");
            } catch (NoDateTimeInputException e) {
                switch (e.command) {
                    case DEADLINE:
                        System.out.println("☹ OOPS!!! The day of a deadline cannot be empty.");
                        break;
                    case EVENT:
                        System.out.println("☹ OOPS!!! The date and time of a event cannot be empty.");
                        break;
                }
            } catch (NoDescriptionException e) {
                System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.%n", e.command);
            } catch (UnknownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            printLine();
            userInput = scanner.nextLine();
            splitInput = userInput.split(" ");
        }
        scanner.close();

        farewellUser();
    }

    /**
     * Starts Duke.
     *
     * @param args array of strings.
     */
    public static void main(String[] args) {
        interactWithUser();
    }
}
