import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Nukem\n"
                + "What can I do for you?");
        Task[] storage = new Task[100];
        int index = 0;
        String tempString;
        Scanner input = new Scanner(System.in);
        // Keep asking for input as long as "bye" is not typed
        while (!(tempString = input.nextLine()).equals("bye")) {
            // Split the input into an array of words separated by spaces
            String[] wordArray = tempString.split(" ");
            // Check the first word in the input for specific commands
            switch (wordArray[0]) {
                case (""):  // Nothing is typed
                    System.out.println("Please enter a command or a task name!");
                    break;
                case ("list"):  // "list" is typed as the first word
                    // list all the tasks only if "list" is the only word in the input
                    if (wordArray.length > 1) { // add a task as usual
                        storage[index] = new Task(tempString);
                        index++;
                        System.out.println("added: " + tempString);
                    } else {    // list all the tasks
                        for (int i = 0; i < storage.length; i++) {
                            if (storage[i] == null) break;
                            else {
                                Task nextTask = storage[i];
                                System.out.println((i + 1)
                                        + "."
                                        + nextTask.toString());
                            }
                        }
                    }
                    break;
                case ("done"):  // "done" is the first word in the input
                    if (wordArray.length != 2) {    // if there are not exactly 2 words in the input, add new task
                        storage[index] = new Task(tempString);
                        index++;
                        System.out.println("added: " + tempString);
                    } else {    // if there are exactly 2 words in the input...
                        try {   // if the second word is an integer, mark the corresponding task as done
                            int tempIndex = Integer.parseInt(wordArray[1]) - 1;
                            if (tempIndex < 0 || tempIndex > 99 || storage[tempIndex] == null) {
                                System.out.println("Invalid task number");
                            } else {
                                Task tempTask = storage[tempIndex];
                                tempTask.markAsDone();
                                System.out.println("Nice! I've marked this task as done:\n"
                                        + "  "
                                        + tempTask.toString());
                            }
                        } catch (NumberFormatException nfe) {   // if 2nd word is not integer, add new task
                            storage[index] = new Task(tempString);
                            index++;
                            System.out.println("added: " + tempString);
                        }
                    }
                    break;
                default:    // add a new task if no command word is detected
                    storage[index] = new Task(tempString);
                    index++;
                    System.out.println("added: " + tempString);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
