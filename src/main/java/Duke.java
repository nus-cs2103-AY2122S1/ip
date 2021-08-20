import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final String divider = "____________________________________________________________";


    /**
     * An enum describing the type of task.
     */
    enum TaskType {
        TODO, DEADLINE, EVENT;

        @Override
        public String toString() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        /**
         * Method to parse a given String input and return a TaskType.
         *
         * @param str The given String input.
         * @return The TaskType matching the String input.
         */
        public static TaskType getType(String str) {
            for (TaskType type : TaskType.values()) {
                if (str.contentEquals(type.toString())) return type;
            }
            return null;
        }
    }


    /**
     * A class representing the tasks that the user can create with Duke.
     * Each Task has a description, and is either done or not yet done.
     */
    private static class Task {
        private final String description;
        private boolean isDone;

        protected Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /**
         * A factory method that returns a task based on a given input, and throws a DukeException
         * if the input is invalid.
         *
         * @param input A String input that is split into half by a space, if there is one.
         * @return A Task.
         * @throws DukeException The exception thrown when the input to create a task is invalid.
         */
        public static Task createTask(String[] input) throws DukeException {
            if(input.length <= 0) throw new DukeException();

            TaskType type = TaskType.getType(input[0]);
            if (type == null) throw new DukeException();
            
            switch(type) {
                case TODO: {
                    if (input.length < 2 || input[1] == null) {
                        throw new DukeException.EmptyDescriptionException(type);
                    } else {
                        return new ToDo(input[1]);
                    }
                }
                case DEADLINE: {
                    if (input.length < 2 || input[1] == null) {
                        throw new DukeException.EmptyDescriptionException(type);
                    }

                    String[] tmp = input[1].split(" /by ", 2);

                    if (tmp.length < 2) {
                        throw new DukeException.NoTimeException(type);
                    }

                    return new Deadline(tmp[0], tmp[1]);
                }
                case EVENT: {
                    if (input.length < 2 || input[1] == null) {
                        throw new DukeException.EmptyDescriptionException(type);
                    }

                    String[] tmp = input[1].split(" /at ", 2);

                    if (tmp.length < 2) {
                        throw new DukeException.NoTimeException(type);
                    }

                    return new Event(tmp[0], tmp[1]);
                }
                default: {
                    throw new DukeException();
                }
            }
        }

        /**
         * Returns the status icon indicated whether a task is done.
         *
         * @return A status icon showing whether a task is done.
         */
        private String getStatusIcon() { return (isDone ? "X" : " "); }

        /**
         * Marks the current task as done.
         */
        public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + this);
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }


    /**
     * A Task without any date/time attached to it.
     */
    public static class ToDo extends Task {
        private static final char symbol = 'T';

        public ToDo(String description) {
            super(description); 
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString();
        }
    }


    /**
     * A type of task that needs to be done before a specific date/time.
     */
    public static class Deadline extends Task {
        private static final char symbol = 'D';

        private final String time;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString() + " (by: " + time + ")";
        }
    }



    /**
     * A type of Task that starts at a specific time and ends at a specific date/time.
     */
    public static class Event extends Task {
        private static final char symbol = 'E';

        private final String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString() + " (at: " + time + ")";
        }
    }


    /**
     * A class that represents exceptions unique to Duke.
     * A DukeException is thrown when a given input is not recognised a valid
     * input for Duke.
     */
    private static class DukeException extends Exception {

        @Override
        public String getMessage() {
            return "Sorry, I don't know what that means :(";
        }

        /**
         * An exception which is thrown when the description of a Task is not provided.
         */
        public static class EmptyDescriptionException extends DukeException {
            private final TaskType type;

            public EmptyDescriptionException(TaskType type) {
                this.type = type;
            }

            @Override
            public String getMessage() {
                return "The description of a " + type + " cannot be empty!";
            }
        }

        /**
         * An exception that is thrown when the date/time of a Deadline or Event is not provided.
         */
        public static class NoTimeException extends DukeException {
            private final TaskType type;

            public NoTimeException(TaskType type) {
                this.type = type;
            }

            @Override
            public String getMessage() {
                return "The " + type + " must have a date / time!";
            }
        }
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        // a list of all the tasks created by the user
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?\n");
        System.out.println(divider);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();


        // Duke continuously asks for the user's input until they type "bye"
        while (!s.contentEquals("bye")) {

            System.out.println(divider);

            // split the input into 2 parts, which are divided by a space if any
            String[] input = s.split("\\s+", 2);
            // the first word in the input
            String command = input[0];

            if (input.length == 1 && command.contentEquals("list")) {

                // list command, that prints out all the tasks in the taskList.
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }

            } else if (command.contentEquals("done")) {

                // done command
                if (input.length > 1) {
                    try {
                        int listIndex = Integer.parseInt(input[1]);
                        if (listIndex <= 0 || listIndex > taskList.size()) {
                            // number given is out of bounds of the taskList
                            System.out.println("Invalid Argument: Index " + listIndex + " is out of bounds!");
                        } else {
                            // no problems with the input, a task is added
                            taskList.get(listIndex - 1).markAsDone();
                        }
                    } catch (NumberFormatException e) {
                        // Second parameter is not an integer
                        System.out.println("Argument must be an Integer!");
                    }
                } else {
                    // A second parameter is not provided
                    System.out.println("Please indicate a task to mark as done");
                }

            } else if(command.contentEquals("delete")) {

                // delete command
                if (input.length > 1) {
                    try {
                        int listIndex = Integer.parseInt(input[1]);
                        if (listIndex <= 0 || listIndex > taskList.size()) {
                            // number given is out of bounds of the taskList
                            System.out.println("Invalid Argument: Index " + listIndex + " is out of bounds!");
                        } else {
                            // no problems with the input, a task is added
                            Task toDelete = taskList.get(listIndex - 1);
                            taskList.remove(listIndex - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + toDelete);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        }
                    } catch (NumberFormatException e) {
                        // Second parameter is not an integer
                        System.out.println("Argument must be an Integer!");
                    }
                } else {
                    // A second parameter is not provided
                    System.out.println("Please indicate a task to delete");
                }

            } else {

                // If the input is not recognised as any of the above commands,
                // then try to create a Task with the given input.
                // If this fails, a DukeException is thrown and the message is printed.
                try {
                    Task toAdd = Task.createTask(input);
                    taskList.add(toAdd);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + toAdd);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }

            System.out.println(divider);
            s = in.nextLine();
        }

        in.close();

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
