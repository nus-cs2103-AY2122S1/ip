import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String divider = "____________________________________________________________";

    private static class Task {
        private final String description;
        private boolean isDone;

        protected Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /** Factory method to create Tasks. */
        public static Task createTask(String[] input) throws DukeException {
            if(input.length <= 0) throw new DukeException();
            
            switch (input[0]) {
                case "todo": {
                    if (input.length < 2 || input[1] == null) {
                        throw new DukeException.EmptyDescriptionException("todo");
                    } else {
                        return new ToDo(input[1]);
                    }
                }
                case "deadline": {
                    if (input.length < 2 || input[1] == null) {
                        throw new DukeException.EmptyDescriptionException("deadline");
                    }

                    String[] tmp = input[1].split(" /by ", 2);

                    if (tmp.length < 2) {
                        throw new DukeException.NoTimeException("deadline");
                    }

                    return new Deadline(tmp[0], tmp[1]);
                }
                case "event": {
                    if (input.length < 2 || input[1] == null) {
                        throw new DukeException.EmptyDescriptionException("event");
                    }

                    String[] tmp = input[1].split(" /at ", 2);

                    if (tmp.length < 2) {
                        throw new DukeException.NoTimeException("event");
                    }

                    return new Event(tmp[0], tmp[1]);
                }
                default: {
                    throw new DukeException();
                }
            }
        }

        private String getStatusIcon() { return (isDone ? "X" : " "); }

        public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }



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



    public static class Deadline extends Task {
        private static final char symbol = 'D';

        private final String time;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString()
                    + " (by: " + time + ")";
        }
    }



    public static class Event extends Task {
        private static final char symbol = 'E';

        private final String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString()
                    + " (at: " + time + ")";
        }
    }


    /**
     * Exceptions unique to Duke.
     */
    private static class DukeException extends Exception {

        @Override
        public String getMessage() {
            return "Sorry, I don't know what that means :(";
        }

        public static class EmptyDescriptionException extends DukeException {
            private final String type;

            public EmptyDescriptionException(String type) {
                this.type = type;
            }

            @Override
            public String getMessage() {
                return "The description of a " + type + " cannot be empty!";
            }
        }

        public static class NoTimeException extends DukeException {
            private final String type;

            public NoTimeException(String type) {
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

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?\n");
        System.out.println(divider);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();


        while (!s.contentEquals("bye")) {

            System.out.println(divider);

            // split the input into 2 parts, which are divided by a space
            String[] input = s.split("\\s+", 2);
            // the first word in the input
            String command = input[0];

            if (input.length == 1 && command.contentEquals("list")) {

                // list command
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }

            } else if (command.contentEquals("done")) {

                // done command
                if (input.length > 1) {
                    try {
                        int listIndex = Integer.parseInt(input[1]);
                        if (listIndex <= 0 || listIndex > taskList.size()) {
                            System.out.println("Invalid Argument: Index " + listIndex + " is out of bounds!");
                        } else {
                            taskList.get(listIndex - 1).markAsDone();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Argument must be an Integer!");
                    }
                } else {
                    System.out.println("Please indicate a task to mark as done");
                }

            } else if(command.contentEquals("delete")) {

                // delete command
                if (input.length > 1) {
                    try {
                        int listIndex = Integer.parseInt(input[1]);
                        if (listIndex <= 0 || listIndex > taskList.size()) {
                            System.out.println("Invalid Argument: Index " + listIndex + " is out of bounds!");
                        } else {
                            Task toDelete = taskList.get(listIndex - 1);
                            taskList.remove(listIndex - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + toDelete);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        }
                    } catch (NumberFormatException e) {
                            System.out.println("Argument must be an Integer!");
                    }
                } else {
                    System.out.println("Please indicate a task to delete");
                }

            } else {
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
