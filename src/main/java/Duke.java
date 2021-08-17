import java.util.*;

public class Duke {

    public static void main(String[] args) {

        // This class represents the tasks added by the user
        class Task {
            protected String description;
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
                return "[" + this.getStatusIcon() + "] " + this.description;
            }
        }

        String linebreak = "~~~~~~~~~~";
        String command; // this is the container for the command received from the user
        Task[] todoList = new Task[100]; // this array stores previous commands
        int pointer = 0; // this tracks the newest position to add an item in todoList

        // The chat bot name is Notaro bc it's Not-a-ro-bot :>
        // This is the introduction of the chat bot, and includes a list of the commands for the user
        System.out.println("Hi! I'm Taro, short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list!");
        System.out.println("\nWhat can I do for you today? :>");
        System.out.println(linebreak);


        // This part listens for user input and repeats
        while (true) {
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye bye!! Thanks for stopping by!");
                System.out.println(linebreak);
                break;

            } else if (command.equals("list")) {
                for (int counter = 0; counter < 100; counter++) {
                    if (todoList[counter] != null) {
                        System.out.println((counter + 1) + ". " + todoList[counter].toString());
                    } else {
                        System.out.println(linebreak);
                        break;
                    }
                }

            } else if (command.split(" ")[0].equals("done")) {
                System.out.println("Yay good job!!");
                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                todoList[ref].markAsDone();
                System.out.println(todoList[ref]);
                System.out.println(linebreak);


            } else { // an item is added
                System.out.println("added: " + command);
                System.out.println(linebreak);
                todoList[pointer] = new Task(command);
                pointer++;
            }
        }
    }
}
