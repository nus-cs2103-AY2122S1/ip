import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean dukeOpen = true;
        Task[] taskList = new Task[100];
        int taskIndex = 0;

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        while (dukeOpen) {
            try {
                String userInput = sc.nextLine();
                if (userInput.equals("bye")) {
                    dukeOpen = false;
                    System.out.println("Bye. Hope to see you again soon!");

                } else if (userInput.equals("list")) {
                    // List all tasks in the task list.
                    int i = 0;
                    for (Task task : taskList) {
                        if (task != null) {
                            System.out.println(++i + "." + task.toString());
                        } else {
                            break;
                        }
                    }
                    if (i == 0) {
                        System.out.println("Your list is currently empty.");
                    }

                } else if (userInput.startsWith("done")) {
                    // Mark a certain task as done.
                    String[] splitString = userInput.split(" ");
                    if (splitString.length > 1) {

                        int taskNumber = Integer.parseInt(splitString[1]);

                        try {
                            Task doneTask = taskList[taskNumber - 1];
                            doneTask.markDone();
                            System.out.println("Nice! I've marked this task as done:\n" + "  " +
                                    doneTask.toString());

                        } catch (Exception e) {
                            throw new DukeException("☹ OOPS!!! That task does not exist.");
                        }

                    } else {
                        throw new DukeException("☹ OOPS!!! Please state which task number " +
                                "you want to mark as done.");
                    }

                } else if (userInput.startsWith(("todo")) || userInput.startsWith("deadline")
                        || userInput.startsWith("event")) {
                    // Adds a Task to the task list.
                    Task newTask;
                    String[] splitString = userInput.split(" ", 2);

                    if (splitString.length > 1 && splitString[1].trim().length() > 0) {
                        String substring = splitString[1];
                        if (userInput.startsWith("todo")) {
                            newTask = new ToDo(substring);

                        } else if (userInput.startsWith("deadline")) {
                            String[] nameAndTime = substring.split(" /by ");
                            if (nameAndTime.length > 1 && nameAndTime[1].trim().length() > 0) {
                                newTask = new Deadline(nameAndTime[0], nameAndTime[1]);
                            } else {
                                throw new DukeException("☹ OOPS!!! Please provide a date or " +
                                        "time for the deadline.");
                            }

                        } else {
                            String[] nameAndTime = substring.split(" /at ");
                            if (nameAndTime.length > 1 && nameAndTime[1].trim().length() > 0) {
                                newTask = new Event(nameAndTime[0], nameAndTime[1]);
                            } else {
                                throw new DukeException("☹ OOPS!!! Please provide a date or " +
                                        "time for the event.");
                            }
                        }

                        taskList[taskIndex] = newTask;
                        taskIndex++;
                        String taskCount = (taskIndex == 1) ? "1 task" : taskIndex + " tasks";
                        System.out.println("Got it. I've added this task:\n" + "  " + newTask.toString() +
                                "\n" + "Now you have " + taskCount + " in the list.");

                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a " + splitString[0] +
                                " cannot be empty.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
    }
}
