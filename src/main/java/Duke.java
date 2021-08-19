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
                    if (wordArray.length > 1) { // if "list" is followed by any word, show a warning
                        System.out.println("Command 'list' should not take any input!");
                    } else {    // list all the tasks
                        System.out.println("Here are the tasks in your list:");
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
                    if (wordArray.length != 2) {    // if there are not exactly 2 words in the input, show a warning
                        System.out.println("Please follow command 'done' with an integer!");
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
                        } catch (NumberFormatException nfe) {   // if 2nd word is not integer, show a warning
                            System.out.println("Please follow command 'done' with an integer!");
                        }
                    }
                    break;
                case ("todo"):
                    if (wordArray.length < 2) {
                        System.out.println("Please enter a task name!");
                    } else {
                        String newDesc = tempString.substring(5);
                        storage[index] = new Todo(newDesc);
                        Task tempTask = storage[index];
                        index++;
                        System.out.println("Got it. I've added this task:\n"
                                + "  " + tempTask.toString() + "\n"
                                + "Now you have " + getLength(storage)
                                + " task" + (getLength(storage) > 1 ? "s" : "") + " in the list.");
                    }
                    break;
                case ("deadline"):
                    if (wordArray.length < 4) {
                        System.out.println("Please follow format: 'deadline [task name] /by [time]'");
                        break;
                    }
                    String newDeadlineDesc = "";
                    String newBy = "";
                    int deadlinePointer = 1;
                    while (deadlinePointer < wordArray.length && !wordArray[deadlinePointer].equals("/by")) {
                        newDeadlineDesc += wordArray[deadlinePointer] + " ";
                        deadlinePointer++;
                    }
                    deadlinePointer++;
                    if (newDeadlineDesc.length() > 0) {
                        newDeadlineDesc = newDeadlineDesc.substring(0, newDeadlineDesc.length() - 1);
                    }
                    while (deadlinePointer < wordArray.length) {
                        newBy += wordArray[deadlinePointer] + " ";
                        deadlinePointer++;
                    }
                    if (newBy.length() > 0) newBy = newBy.substring(0, newBy.length() - 1);
                    if (newDeadlineDesc.equals("") || newBy.equals("")) {
                        System.out.println("Please follow format: 'deadline [task name] /by [time]'");
                    } else {
                        storage[index] = new Deadline(newDeadlineDesc, newBy);
                        Task tempTask = storage[index];
                        index++;
                        System.out.println("Got it. I've added this task:\n"
                                + "  " + tempTask.toString() + "\n"
                                + "Now you have " + getLength(storage)
                                + " task" + (getLength(storage) > 1 ? "s" : "") + " in the list.");
                    }
                    break;
                case ("event"):
                    if (wordArray.length < 4) {
                        System.out.println("Please follow format: 'event [task name] /at [time]'");
                        break;
                    }
                    String newEventDesc = "";
                    String newAt = "";
                    int eventPointer = 1;
                    while (eventPointer < wordArray.length && !wordArray[eventPointer].equals("/at")) {
                        newEventDesc += wordArray[eventPointer] + " ";
                        eventPointer++;
                    }
                    eventPointer++;
                    if (newEventDesc.length() > 0) {
                        newEventDesc = newEventDesc.substring(0, newEventDesc.length() - 1);
                    }
                    while (eventPointer < wordArray.length) {
                        newAt += wordArray[eventPointer] + " ";
                        eventPointer++;
                    }
                    if (newAt.length() > 0) newAt = newAt.substring(0, newAt.length() - 1);
                    if (newEventDesc.equals("") || newAt.equals("")) {
                        System.out.println("Please follow format: 'event [task name] /at [time]'");
                    } else {
                        storage[index] = new Event(newEventDesc, newAt);
                        Task tempTask = storage[index];
                        index++;
                        System.out.println("Got it. I've added this task:\n"
                                + "  " + tempTask.toString() + "\n"
                                + "Now you have " + getLength(storage)
                                + " task" + (getLength(storage) > 1 ? "s" : "") + " in the list.");
                    }
                    break;
                default:    // No command word is recognised
                    System.out.println("Command not recognised!");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static <T> int getLength(T[] arr){
        int count = 0;
        for(T element : arr) {
            if (element != null) ++count;
        }
        return count;
    }
}
