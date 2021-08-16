import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    /**
     * Simple string array to store inputs
     **/
    private final Task[] list;
    /**
     * Simple index to keep track of the current element in the list
     **/
    private int index;

    /**
     * Basic constructor to initialise the list
     **/
    public Duke() {
        this.list = new Task[100];
        this.index = 0;
    }

    /**
     * Private method to append items to the back of the list.
     *
     * @param task The text to be appended.
     */
    private void append(Task task) {
        this.list[index] = task;
        this.index++;
    }

    /**
     * Simple method that iterates through the task list and prints out all the entries
     */
    public void list() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.index; i++) {
            // i + 1 is used to start indexing from 1
            output.append(i + 1).append(". ");
            output.append(this.list[i].toString());
            // ensure the newline is not added on the last element
            if (i != this.index - 1) {
                output.append("\n");
            }
        }
        this.printMsg(String.format("Here are the tasks in your list:\n%s", output.toString()));
    }

    /**
     * Method to be called when the first argument to the input is "done".
     * Parses the second argument as an integer and checks if it is a valid index.
     * If valid, marks the task that is currently stored in memory to be complete.
     *
     * @param text Command line argument string
     */
    private void done(String text) throws DukeException {
        // check for the existence of the second argument and that it can be parsed to an int for lookup
        String[] splitted = text.split(" ");
        if (splitted.length < 2) {
            throw new DukeException("Command is missing an argument 'index'.");
        }

        String dirtyInt = splitted[1];


        // ensure that it is defined
        if (dirtyInt != null) {
            // try to parse it into an int
            try {
                int lookup = Integer.parseInt(dirtyInt);
                if (lookup < 1) {
                    throw new DukeException(String.format("The specified task number %d does not exist. Enter a number that is at least 1.", lookup));
                } else if (lookup < this.index + 1) {
                    Task task = this.list[lookup - 1];
                    task.markAsDone();
                    this.printMsg(String.format("Nice! I've marked this task as done:\n  %s", task));
                } else {
                    throw new DukeException(String.format("The task number %s is invalid, please enter a valid " +
                            "number.", lookup));
                }
            } catch (NumberFormatException e) {
                throw new DukeException(String.format("The specified task number %s, is not a valid integer, please " +
                        "enter a valid numeric task number.", dirtyInt));
            }
        } else {
            System.out.println(Arrays.toString(splitted));
        }


    }

    public Deadline deadline(String text) throws DukeException {
        String[] splitted = text.split("\\s");
        if (splitted.length < 2) {
            throw new DukeException("Command is missing an argument 'deadline description'.");
        } else {
            int index = this.linearScan(splitted, "/by");
            if (index < 0) {
                throw new DukeException("Command is missing the keyword /by.");
            } else if (index == 1) {
                throw new DukeException("The description body cannot be empty!");
            } else {
                if (splitted.length - 1 == index) {
                    throw new DukeException("The deadline cannot be empty!");
                }
                String description = String.join(" ", Arrays.copyOfRange(splitted, 1, index));
                String by = String.join(" ", Arrays.copyOfRange(splitted, index + 1, splitted.length));
                Deadline newDeadline = new Deadline(description, by);
                this.append(newDeadline);
                return newDeadline;
            }
        }
    }

    public Event event(String text) throws DukeException {
        String[] splitted = text.split("\\s");
        if (splitted.length < 2) {
            throw new DukeException("Command is missing an argument 'index'.");
        } else {
            int index = this.linearScan(splitted, "/at");
            if (index < 0) {
                throw new DukeException("Command is missing the keyword /at.");
            } else if (index == 1) {
                throw new DukeException("The description body cannot be empty!");
            } else {
                if (splitted.length - 1 == index) {
                    throw new DukeException("The deadline cannot be empty!");
                }
                String description = String.join(" ", Arrays.copyOfRange(splitted, 1, index));
                String at = String.join(" ", Arrays.copyOfRange(splitted, index + 1, splitted.length));
                Event newEvent = new Event(description, at);
                this.append(newEvent);
                return newEvent;
            }
        }
    }

    public Todo todo(String text) throws DukeException {
        String[] splitted = text.split("\\s");
        if (splitted.length < 2) {
            throw new DukeException("The description body cannot be empty!");
        } else {
            String description = String.join(" ", Arrays.copyOfRange(splitted, 1, splitted.length));
            Todo newTodo = new Todo(description);
            this.append(newTodo);
            return newTodo;
        }
    }

    private <T> int linearScan(T[] array, T finder) {
        for (int i = 0; i < array.length; i++) {
            if (finder.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Simple formatting tool to be used when printing commands
     *
     * @param text The text to be printed.
     */
    private void printMsg(String text) {
        String bar = "===============================================";
        System.out.printf("%s\n%s\n%s\n", bar, text, bar);
    }

    private void printAdded(String text) {
        String count = String.format("Now you have %d %s in the list", this.index, this.index > 1 ? "tasks" : "task");
        this.printMsg(String.format("Got it. I've added this task:\n  %s\n%s.",
                text, count));
    }

    /**
     * Method that listens and scans for user input.
     * Program will exit when the command "bye" is given to the scanner.
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);
        this.printMsg("Hello! I'm Duke\nWhat can I do for you?");
        boolean terminate = false;
        while (!terminate && scanner.hasNextLine()) {
            String scannedLine = scanner.nextLine();
            String text = scannedLine.toLowerCase();
            // get the first word of the text
            String command = text.split(" ")[0];
            switch (command.toLowerCase()) {
                // break out of the while loop
                case "bye": {
                    this.printMsg("Bye. Hope to see you again soon!");
                    scanner.close();
                    terminate = true;
                }
                // list out the items in the list if "list" is called
                case ("list"): {
                    this.list();
                    break;
                }

                case ("done"): {
                    try {
                        this.done(text);
                    } catch(DukeException e) {
                        this.printMsg(e.toString());
                    }
                    break;
                }

                case ("event"): {
                    try {
                        Event event = this.event(text);
                        this.printAdded(event.toString());
                    } catch (DukeException e) {
                        this.printMsg(e.toString());
                    }
                    break;
                }

                case ("deadline"): {
                    try {
                        Deadline deadline = this.deadline(text);
                        this.printAdded(deadline.toString());
                    } catch (DukeException e) {
                        this.printMsg(e.toString());
                    }
                    break;
                }

                case ("todo"): {
                    try {
                        Todo todo = this.todo(text);
                        this.printAdded(todo.toString());
                    } catch (DukeException e) {
                        this.printMsg(e.toString());
                    }
                    break;
                }

                default: {
                    this.printMsg("Sorry, I don't know what that command means.");
                    break;
                }
            }
        }
    }


    /**
     * Enum for handling user command
     **/
    public enum Commands {
        BYE, LIST, DONE
    }

}
