import java.util.*;

public class Duke {

    // This class represents the tasks added by the user
    class Task {
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

    class Deadline extends Task {
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

    class Event extends Task {
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

    class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString() + description;
        }
    }

    public static void main(String[] args) {

        String linebreak = "~~~~~~~~~~";
        String command; // this is the container for the command received from the user
        Task[] todoList = new Task[100]; // this array stores previous commands
        int pointer = 0; // this tracks the newest position to add an item in todoList
        Duke duke = new Duke();

        // The chat bot name is Notaro bc it's Not-a-ro-bot :>
        // This is the introduction of the chat bot, and includes a list of the commands for the user
        System.out.println("Hi! I'm Taro, short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println("There are three special tasks you can add: Deadline, Event and Todo");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list!");
        System.out.println("\nWhat can I do for you today? :>");
        System.out.println(linebreak);


        // This part listens for user input and repeats
        while (true) {
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            if (command.toLowerCase().equals("bye")) {
                System.out.println("Bye bye!! It was nice meeting you!");
                System.out.println(linebreak);
                break;

            } else if (command.toLowerCase().equals("list")) {
                for (int counter = 0; counter < 100; counter++) {
                    if (todoList[counter] != null) {
                        System.out.println((counter + 1) + ". " + todoList[counter].toString());
                    } else {
                        System.out.println(linebreak);
                        break;
                    }
                }

            } else if (command.toLowerCase().split(" ")[0].equals("done")) {
                System.out.println("Yay good job!!");
                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                todoList[ref].markAsDone();
                System.out.println(todoList[ref]);
                System.out.println(linebreak);


            // Else, an item has been added to the chat bot
            } else {

                String taskType = command.toLowerCase().split(" ", 2)[0];
                System.out.println("added: " + command);
                System.out.println(linebreak);

                if (taskType.equals("todo")) {
                    String taskInfo = command.split(" ", 2)[1];
                    todoList[pointer] = duke.new Todo(taskInfo);

                } else if (taskType.equals("deadline")) {
                    String taskInfo = command.split(" ", 2)[1];
                    String description = taskInfo.split("/by",2)[0];
                    String dateBy = taskInfo.split("/by",2)[1];
                    todoList[pointer] = duke.new Deadline(description, dateBy);

                } else if (taskType.equals("event")) {
                    String taskInfo = command.split(" ", 2)[1];
                    String description = taskInfo.split("/at",2)[0];
                    String eventDetails = taskInfo.split("/at",2)[1];
                    todoList[pointer] = duke.new Event(description, eventDetails);

                } else {
                    System.out.println("Sorry! I don't understand this command :(");
                    break;
                }

                pointer++;

            }
        }
    }
}




