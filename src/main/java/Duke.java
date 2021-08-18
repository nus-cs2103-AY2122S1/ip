import java.util.*;

public class Duke {

    // This class represents the tasks added by the user
    protected class Task {
        protected final String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

            public String toString() {
                return "[" + this.getStatusIcon() + "] ";
            }
    }

    protected class Deadline extends Task {
        protected final String dateBy;

        public Deadline(String description, String dateBy) {
            super(description);
            this.dateBy = dateBy;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + description + " (by: " + dateBy + ")";
        }
    }

    protected class Event extends Task {
        protected final String eventDetails;

        public Event(String description, String eventDetails) {
            super(description);
            this.eventDetails = eventDetails;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + description + " (at: " + eventDetails + ")";
        }
    }

    protected class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString() + description;
        }
    }

    protected static class DukeExceptions extends Exception {
        public DukeExceptions(String message) {
            super(message);
        }
    }

    protected static class CommandDoesNotExist extends DukeExceptions {
        public CommandDoesNotExist(String message) {
            super("Sorry! The command \"" + message + "\" doesn't exist :(\nPlease try again!");
        }
    }

    protected static class EmptyDescriptionException extends DukeExceptions {
        public EmptyDescriptionException(String message) {
            super("Sorry! There needs to be a description for a " + message + " item :(\nPlease try again!");
        }
    }

    protected static class EmptyDetailsException extends DukeExceptions {
        public EmptyDetailsException(String message) {
            super("Sorry! Please include more details for a " + message + " item :(\nPlease try again!");
        }
    }

    // TODO it shows 1 to 0 if "done" is entered before any items are appended to the list
    protected static class NotDoneRightException extends DukeExceptions {
        public NotDoneRightException(String start, String end) {
            super("Please input an integer in the range of " + start + " to " + end + "!");
        }
    }

    // TODO it shows 1 to 0 if "done" is entered before any items are appended to the list
    protected static class DeletionException extends DukeExceptions {
        public DeletionException(String start, String end) {
            super("Please input an integer in the range of " + start + " to " + end + "!");
        }
    }

    public static void main(String[] args) throws DukeExceptions {

        String linebreak = "~~~~~~~~~~";
        String command; // this is the container for the command received from the user
        ArrayList<Task> todoList = new ArrayList<Task>(); // this array stores previous commands
        int pointer = 0; // this tracks the newest position to add an item in todoList
        //enum Commands {bye, list, done, delete, deadline, event, todo}; TODO
        Duke duke = new Duke();

        // The chat bot name is Notaro bc it's Not-a-ro-bot :>
        // This is the introduction of the chat bot, and includes a list of the commands for the user
        System.out.println("Hi! I'm Taro, short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println("There are three special tasks you can add: Deadline, Event and Todo");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list :(");
        System.out.println("done [number] : Marks the item corresponding the number in the todo list as complete!");
        System.out.println("delete [number] : Deletes the item corresponding the number in the todo list");
        System.out.println("\nWhat can I do for you today? :>");
        System.out.println(linebreak);
        Scanner sc = new Scanner(System.in);


        // This part listens for user input and repeats until the command "bye" is identified
        while (true) {
            command = sc.nextLine();

            if (command.toLowerCase().equals("bye")) {
                System.out.println("Bye bye!! It was nice meeting you!");
                System.out.println(linebreak);
                sc.close();
                break;


            } else if (command.toLowerCase().equals("list")) {
                if (pointer == 0) {
                    System.out.println("Yay! Nothing on your list right now :>");
                }

                int counter = 0;
                for (Task task : todoList) {
                    System.out.println((counter + 1) + ". " + todoList.get(counter).toString());
                    counter++;
                }
                System.out.println(linebreak);


            } else if (command.toLowerCase().split(" ")[0].equals("done")) {

                if (command.toLowerCase().split(" ").length == 1 || Integer.parseInt(command.split(" ")[1]) < 1
                        || Integer.parseInt(command.split(" ")[1]) > todoList.size()) {
                    throw new NotDoneRightException("1", String.valueOf(todoList.size()));
                }

                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                todoList.get(ref).markAsDone();
                System.out.println("Yay good job!!");
                System.out.println(todoList.get(ref));
                System.out.println(linebreak);


            } else if (command.toLowerCase().split(" ")[0].equals("delete")) {

                if (command.toLowerCase().split(" ").length == 1 || Integer.parseInt(command.split(" ")[1]) < 1
                        || Integer.parseInt(command.split(" ")[1]) > todoList.size()) {
                    throw new DeletionException("1", String.valueOf(todoList.size()));
                }

                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println("Oki! I have removed this task:");
                System.out.println(todoList.remove(ref));
                System.out.println(todoList.size() + " more tasks to go!");
                System.out.println(linebreak);


            // Else, an item has been added to the chat bot
            } else {

                String taskType = command.toLowerCase().split(" ", 2)[0];

                switch (taskType) {
                    case "todo" -> {
                        String[] taskInfo = command.split(" ", 2);
                        if (taskInfo.length == 1) {
                            throw new EmptyDescriptionException("todo");
                        }

                        System.out.println("added: " + command);
                        System.out.println(linebreak);
                        todoList.add(duke.new Todo(taskInfo[1]));
                        break;

                    }
                    case "deadline" -> {
                        String[] taskInfo = command.split(" ", 2);
                        if (taskInfo.length == 1) {
                            throw new EmptyDescriptionException("todo");
                        }

                        String[] additionalTaskInfo = taskInfo[1].split("/by", 2);
                        if (additionalTaskInfo.length == 1) {
                            throw new EmptyDetailsException("deadline");
                        }
                        String description = additionalTaskInfo[0];
                        String dateBy = additionalTaskInfo[1];

                        System.out.println("added: " + command);
                        System.out.println(linebreak);
                        todoList.add(duke.new Deadline(description, dateBy));
                        break;

                    }
                    case "event" -> {
                        String[] taskInfo = command.split(" ", 2);
                        if (taskInfo.length == 1) {
                            throw new EmptyDescriptionException("todo");
                        }

                        String[] additionalTaskInfo = taskInfo[1].split("/at", 2);
                        if (additionalTaskInfo.length == 1) {
                            throw new EmptyDetailsException("event");
                        }
                        String description = additionalTaskInfo[0];
                        String eventDetails = additionalTaskInfo[1];

                        System.out.println("added: " + command);
                        System.out.println(linebreak);
                        todoList.add(duke.new Event(description, eventDetails));
                        break;

                    }
                    default -> throw new CommandDoesNotExist(command);
                }

                pointer++;

            }
        }
    }
}




