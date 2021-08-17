import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner scanner;
    private static ArrayList<Task> savedInputs;

    public static void main(String[] args) throws DukeException {
        scanner = new Scanner(System.in);
        savedInputs = new ArrayList<>(100);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        chat();
    }

    /**
     * Prints (to screen) Duke's response to the user input, entered from the Command Line.
     */
    private static void chat() throws DukeException {
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            }

            try {
                int caseId = findCase(input);

                switch (caseId) {
                    // list
                    case 1:
                        StringBuilder outputList = new StringBuilder();
                        outputList.append("Here are the tasks in your list:\n");
                        for (int i = 1; i <= savedInputs.size(); i++) {
                            outputList.append(i + "." + savedInputs.get(i - 1).toString() + "\n");
                        }
                        System.out.println(outputList);
                        break;

                    // done
                    case 2:
                        // handles any characters after 'done' that are not white space
                        if (!input.startsWith("done ")) {
                            throw new DukeException("Oops! Improper formatting for done. " +
                                    "Please use: done <task number>");
                        }
                        if (input.length() == 5) {
                            throw new DukeException("Oops! The number following 'done' cannot be empty.");
                        }
                        int donePos = Integer.valueOf(input.substring(5));

                        //checks if the task to be marked as done is within the range of the list
                        if (donePos <= 0 || donePos > savedInputs.size()) {
                            throw new DukeException("Oops! " +
                                "The task to mark as done is not within the range of the list.");
                        }
                        savedInputs.get(donePos - 1).markAsDone();
                        break;

                    // delete
                    case 3:
                        if (!input.startsWith("delete ")) {
                            throw new DukeException("Oops! Improper formatting for delete. " +
                                    "Please use: delete <task number>");
                        }
                        if (input.length() == 7) {
                            throw new DukeException("Oops! The number following 'delete' cannot be empty.");
                        }
                        int deletePos = Integer.valueOf(input.substring(7));
                        if (deletePos <= 0 || deletePos > savedInputs.size()) {
                            throw new DukeException("Oops! " +
                                    "The task to delete is not within the range of the list.");
                        }

                        System.out.println("Noted. I've removed this task:\n  " +
                            savedInputs.get(deletePos - 1).toString());
                        savedInputs.remove(deletePos - 1);
                        System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                        break;

                    // todo
                    case 4:
                        // handles any characters after 'todo' that are not white space
                        if (!input.startsWith("todo ")) {
                            throw new DukeException("Oops! Improper formatting for todo. " +
                                    "Please use: todo <description>");
                        }
                        if (input.length() == 5) {
                            throw new DukeException("Oops! The description of todo cannot be empty.");
                        }

                        Todo todo = new Todo(input.substring(5));
                        System.out.println("Got it. I've added this task:\n  " + todo);
                        savedInputs.add(todo);
                        System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                        break;

                    // deadline
                    case 5:
                        // handles any characters after 'deadline' that are not white space
                        if (!input.startsWith("deadline ")) {
                            throw new DukeException("Oops! Improper formatting for deadline. " +
                                    "Please use: deadline <description> /by <date/time>");
                        }
                        // handles case of no description and date/time
                        if (input.length() == 9) {
                            throw new DukeException("Oops! The description and date/time of deadline cannot be empty.");
                        }
                        if (!input.contains(" /by ")) {
                            throw new DukeException("Oops! Inappropriate formatting for deadlines.");
                        }
                        // handles: deadline /by <time>
                        if (input.split(" /by ")[0].equals("deadline")) {
                            throw new DukeException("Oops! Missing description for deadline.");
                        }

                        String by = input.split(" /by ")[1];
                        String deadlineDescription = input.split(" /by ")[0].substring(9);

                        Deadline deadline = new Deadline(deadlineDescription, by);
                        System.out.println("Got it. I've added this task:\n  " + deadline);
                        savedInputs.add(deadline);
                        System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                        break;

                    // event
                    case 6:
                        // handles any characters after 'event' that are not white space
                        if (!input.startsWith("event ")) {
                            throw new DukeException("Oops! Improper formatting for event. " +
                                    "Please use: event <description> /at <date/time>");
                        }
                        if (input.length() == 6) {
                            throw new DukeException("Oops! The description and date/time of event cannot be empty.");
                        }
                        if(!input.contains(" /at ")){
                            throw new DukeException("Oops! Inappropriate formatting for events.");
                        }
                        if (input.split(" /at ")[0].equals("event")) {
                            throw new DukeException("Oops! Missing description for event.");
                        }

                        String at = input.split(" /at ")[1];
                        String eventDescription = input.split(" /at ")[0].substring(6);

                        Event event = new Event(eventDescription, at);
                        System.out.println("Got it. I've added this task:\n  " + event);
                        savedInputs.add(event);
                        System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                        break;

                    default:
                        throw new DukeException("Oops! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            }
        }
    }

    /**
     * Returns the identifier of each case (for switch in chat method).
     *
     * @param input User entered into Command Line
     * @return CaseId
     */
    private static int findCase(String input) {
        if (input.equals("list")) {
            return 1;
        } else if (input.startsWith("done")) {
            return 2;
        } else if (input.startsWith("delete")) {
            return 3;
        } else if (input.startsWith("todo")) {
            return 4;
        } else if (input.startsWith("deadline")) {
            return 5;
        } else if (input.startsWith("event")) {
            return 6;
        } else {
            return 7;
        }
    }
}
