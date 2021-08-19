import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<Task> storage = new ArrayList<>();
        String tempString;
        Scanner input = new Scanner(System.in);
        // Keep asking for input as long as "bye" is not typed
        while (!(tempString = input.nextLine()).equals("bye")) {
            // Split the input into an array of words separated by spaces
            String[] wordArray = tempString.split(" ");
            try {
                // Check the first word in the input for specific commands
                switch (wordArray[0]) {
                    case (""):  // Nothing is typed
                        throw new DukeException("Please enter a command!");
                    case ("list"):  // "list" is typed as the first word
                        // list all the tasks only if "list" is the only word in the input
                        if (wordArray.length > 1) { // if "list" is followed by any word, show a warning
                            throw new DukeException("Command 'list' should not take any input!");
                        } else {    // list all the tasks
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < storage.size(); i++) {
                                Task nextTask = storage.get(i);
                                System.out.println((i + 1)
                                        + "."
                                        + nextTask.toString());
                            }
                        }
                        break;
                    case ("done"):  // "done" is the first word in the input
                        if (wordArray.length != 2) {    // if there are not exactly 2 words in the input, show a warning
                            throw new DukeException("Please follow command 'done' with an integer!");
                        } else {    // if there are exactly 2 words in the input...
                            try {   // if the second word is an integer, mark the corresponding task as done
                                int tempIndex = Integer.parseInt(wordArray[1]) - 1;
                                Task tempTask = storage.get(tempIndex);
                                tempTask.markAsDone();
                                System.out.println("Nice! I've marked this task as done:\n"
                                        + "  "
                                        + tempTask.toString());
                            } catch (NumberFormatException nfe) {   // if 2nd word is not integer, show a warning
                                throw new DukeException("Please follow command 'done' with an integer!");
                            } catch (IndexOutOfBoundsException oob) {   // If the task number is out of bound, warn
                                throw new DukeException("Invalid task number!");
                            }
                        }
                        break;
                    case ("todo"):  // "todo" is typed as the first word
                        if (wordArray.length < 2) { // if nothing is typed after the command, warn
                            throw new DukeException("Please enter a task name!");
                        } else {    // else, add the new task with everything behind the command as description
                            String newDesc = tempString.substring(5);
                            Task newTodo = new Todo(newDesc);
                            storage.add(newTodo);
                            System.out.println("Got it. I've added this task:\n"
                                    + "  " + newTodo.toString() + "\n"
                                    + "Now you have " + storage.size()
                                    + " task" + (storage.size() > 1 ? "s" : "") + " in the list.");
                        }
                        break;
                    case ("deadline"):  // "deadline" is typed as the first word
                        if (wordArray.length < 4) { // if less than 4 words are typed, warn
                            throw new DukeException("Please follow format: 'deadline [task name] /by [time]'");
                        }
                        // Scan through the input to extract the description before "/by"
                        // and the date after "/by"
                        String newDeadlineDesc = "";
                        String newBy = "";
                        int deadlinePointer = 1;
                        while (deadlinePointer < wordArray.length && !wordArray[deadlinePointer].equals("/by")) {
                            newDeadlineDesc += wordArray[deadlinePointer] + " ";
                            deadlinePointer++;
                        }
                        deadlinePointer++;
                        if (newDeadlineDesc.length() > 0) { // delete the last space
                            newDeadlineDesc = newDeadlineDesc.substring(0, newDeadlineDesc.length() - 1);
                        }
                        while (deadlinePointer < wordArray.length) {
                            newBy += wordArray[deadlinePointer] + " ";
                            deadlinePointer++;
                        }
                        // delete the last space
                        if (newBy.length() > 0) newBy = newBy.substring(0, newBy.length() - 1);
                        if (newDeadlineDesc.equals("") || newBy.equals("")) {   // if either the description or date is empty
                            throw new DukeException("Please follow format: 'deadline [task name] /by [time]'");
                        } else {    // else add a new Deadline
                            Task newDeadline = new Deadline(newDeadlineDesc, newBy);
                            storage.add(newDeadline);
                            System.out.println("Got it. I've added this task:\n"
                                    + "  " + newDeadline.toString() + "\n"
                                    + "Now you have " + storage.size()
                                    + " task" + (storage.size() > 1 ? "s" : "") + " in the list.");
                        }
                        break;
                    case ("event"): // "event" is typed as the first word
                        if (wordArray.length < 4) { // if less than 4 words are typed, warn
                            throw new DukeException("Please follow format: 'event [task name] /at [time]'");
                        }
                        // Scan through the input to extract the description before "/at"
                        // and the date after "/at"
                        String newEventDesc = "";
                        String newAt = "";
                        int eventPointer = 1;
                        while (eventPointer < wordArray.length && !wordArray[eventPointer].equals("/at")) {
                            newEventDesc += wordArray[eventPointer] + " ";
                            eventPointer++;
                        }
                        eventPointer++;
                        if (newEventDesc.length() > 0) {    // delete the last space
                            newEventDesc = newEventDesc.substring(0, newEventDesc.length() - 1);
                        }
                        while (eventPointer < wordArray.length) {
                            newAt += wordArray[eventPointer] + " ";
                            eventPointer++;
                        }
                        // delete the last space
                        if (newAt.length() > 0) newAt = newAt.substring(0, newAt.length() - 1);
                        if (newEventDesc.equals("") || newAt.equals("")) {  // if either the description or date is empty
                            throw new DukeException("Please follow format: 'event [task name] /at [time]'");
                        } else {    // else add a new Event
                            Task newEvent = new Event(newEventDesc, newAt);
                            storage.add(newEvent);
                            System.out.println("Got it. I've added this task:\n"
                                    + "  " + newEvent.toString() + "\n"
                                    + "Now you have " + storage.size()
                                    + " task" + (storage.size() > 1 ? "s" : "") + " in the list.");
                        }
                        break;
                    case ("delete"):    // "delete" is typed as the first word
                        if (wordArray.length != 2) {    // if there are not exactly 2 words in the input, show a warning
                            throw new DukeException("Please follow command 'delete' with an integer!");
                        } else {    // if there are exactly 2 words in the input...
                            try {   // if the second word is an integer, delete the corresponding task
                                int tempIndex = Integer.parseInt(wordArray[1]) - 1;
                                String taskString = storage.get(tempIndex).toString();
                                storage.remove(tempIndex);
                                System.out.println("Noted. I've removed this task:\n"
                                        + "  "
                                        + taskString + "\n"
                                        + "Now you have " + storage.size()
                                        + " task" + (storage.size() > 1 ? "s" : "") + " in the list.");
                            } catch (NumberFormatException nfe) {   // if 2nd word is not integer, show a warning
                                throw new DukeException("Please follow command 'delete' with an integer!");
                            } catch (IndexOutOfBoundsException oob) {   // If the task number is out of bound, warn
                                throw new DukeException("Invalid task number!");
                            }
                        }
                        break;
                    default:    // No command word is recognised
                        throw new DukeException("Command not recognised!");
                }
            } catch (DukeException dE) {
                System.out.println(dE.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
