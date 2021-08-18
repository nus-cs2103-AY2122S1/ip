import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<Task> list = new ArrayList<>();

    private static void addToList(Task task) {
        list.add(task);
    }

    private static void listTasks() {
        if (list.size() < 1) {
            System.out.println("You haven't added anything to the list yet! Try adding something.");
        } else {
            if (list.size() == 1) {
                System.out.println("Here is the sole task in your list:");
            }
            else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    private static void completeTask(int index) {
        Task task = list.get(index - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task);
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) { //Close Duke
                break;
            } else if (input.equals("list")) { //List current tasks and prompt for more input
                listTasks();
            } else if (input.startsWith("done")) {
                try { //Treat the substring after 'done' as an int.
                    int listIndex = Integer.parseInt(input.substring(5));
                    if (listIndex <= list.size() && listIndex >= 1) {
                        completeTask(listIndex);
                    } else {
                        System.out.println("Couldn't find that task in the list! Try again.");
                    }
                } catch (NumberFormatException e) { //If 'done' is followed by a non-integer
                    System.out.println("Please make sure only a number follows the command 'done'. Try again.");
                } catch (StringIndexOutOfBoundsException e) { //If 'done' is not followed by anything
                    System.out.println("Please add a number after the command 'done'. Try again.");
                }
            } else if (input.startsWith("deadline")) {
                if (!input.contains("/by")) {
                    System.out.println("Please state the deadline for this task with /by! Try again.");
                } else {
                    try {
                        int deadlineIndex = input.indexOf("/by") + 4; //need try catch
                        String deadline = input.substring(deadlineIndex);
                        String firstTrimmed = input.substring(9);
                        String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/by"));
                        Deadline createdDeadlineTask = new Deadline(lastTrimmed, deadline);
                        addToList(createdDeadlineTask);
                        System.out.println("Got it. I've added this task:\n" + "  " + createdDeadlineTask + "\n"
                                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                " in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please ensure that there is a task description and deadline. Try again.");
                    }
                }
            } else if (input.startsWith("todo")){
                try {
                    String trimmed = input.substring(5);
                    Todo createdTodoTask = new Todo(trimmed);
                    addToList(createdTodoTask);
                    System.out.println("Got it. I've added this task:\n" + "  " + createdTodoTask + "\n"
                            + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                            " in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please add the task information. Try again.");
                }
            } else if (input.startsWith("event")){
                if (!input.contains("/at")) {
                    System.out.println("Please state when the event will be held with /at! Try again.");
                } else {
                    try {
                        int atIndex = input.indexOf("/at") + 4;
                        String at = input.substring(atIndex);
                        String firstTrimmed = input.substring(6);
                        String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/at"));
                        Event createdEventTask = new Event(lastTrimmed, at);
                        addToList(createdEventTask);
                        System.out.println("Got it. I've added this task:\n" + "  " + createdEventTask + "\n"
                                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                " in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please ensure that there is an event time. " +
                                "Try again.");
                    }
                }
            } else {
                System.out.println("I didn't quite get what you meant. To add a task, begin with 'deadline', 'event'" +
                        " or 'todo'.");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
