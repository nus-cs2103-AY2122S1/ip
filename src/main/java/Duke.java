import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static void removeTask(int index) {
        tasks.remove(index);
    }

    public static Message printList() {
        StringBuilder itemList = new StringBuilder("\tHere are the tasks in your list:\n");

        if (tasks.size() == 0) {
            itemList.append("\tNothing here yet...");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                itemList.append("\t\t").append(i + 1).append(". ").append(tasks.get(i));
                if (i < tasks.size() - 1) {
                    itemList.append("\n");
                }
            }
        }

        return new Message(itemList.toString());
    }

    private static void printAddedTask(Task task) {
        Message addMessage = new Message("\tGot it. I've added this task:\n\t\t"
                + task
                + "\n\tTask(s) remaining in the list: "
                + tasks.size()
        );
        addMessage.printMessage();
    }

    public static void applyCommand(String command) throws DukeException {
        if (command.contains(" ")) {
            String commandType = command.split(" ", 2)[0];
            String description = command.split(" ", 2)[1].trim();

            switch (commandType) {
                case "todo": {
                    if (description.trim().isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Task task = new ToDo(description);
                        Duke.addTask(task);
                        printAddedTask(task);
                    }
                    return;
                }
                case "deadline": {
                    if (description.contains("/by")) {
                        String[] information = description.split("/by ");
                        Task task = new Deadline(information[0], information[1]);
                        Duke.addTask(task);
                        printAddedTask(task);
                    } else {
                        throw new DukeException("☹ OOPS!!! You did not provide the time." +
                                "\n\t Please use the command /by");
                    }
                    return;
                }
                case "event": {
                    if (description.contains("/at")) {
                        String[] information = description.split("/at ");
                        Task task = new Event(information[0], information[1]);
                        Duke.addTask(task);
                        printAddedTask(task);
                    } else {
                        throw new DukeException("☹ OOPS!!! You did not provide the time." +
                                "\n\t Please use the command /at");
                    }
                    return;
                }
                case "done": {
                    if (description.matches("\\d+")) {
                        int item = Integer.parseInt(description);
                        if (item == 0) {
                            throw new DukeException("☹ OOPS!!! The item should be an positive integer.");
                        }
                        if (item > tasks.size()) {
                            throw new DukeException("☹ OOPS!!! The item number is out of bound!");
                        }
                        if (Duke.tasks.get(item - 1).isDone) {
                            throw new DukeException("☹ OOPS!!! The task is already done!");
                        }
                        Duke.tasks.get(item - 1).setDone(true);
                        Message doneMessage = new Message("\tNice! I've marked this task as done:\n\t\t"
                                + tasks.get(item - 1)
                        );
                        doneMessage.printMessage();
                    } else {
                        throw new DukeException("☹ OOPS!!! The item should be an positive integer.");
                    }
                    return;
                }
                case "delete": {
                    if (description.matches("\\d+")) {
                        int item = Integer.parseInt(description);
                        if (item == 0) {
                            throw new DukeException("☹ OOPS!!! The item should be an positive integer.");
                        }
                        if (item > tasks.size()) {
                            throw new DukeException("☹ OOPS!!! The item number is out of bound!");
                        }
                        int numOfTasks = tasks.size() - 1;
                        Message doneMessage = new Message("\tNoted. I've removed this task:\n\t\t"
                                + tasks.get(item - 1)
                                + "\n\tTask(s) remaining in the list: "
                                + numOfTasks
                        );
                        Duke.removeTask(item - 1);
                        doneMessage.printMessage();
                    } else {
                        throw new DukeException("☹ OOPS!!! The item should be an positive integer.");
                    }
                    return;
                }
                default: {
                    throw new DukeException("☹ OOPS!!! I don't understand that!");
                }
            }
        }

        switch (command) {
            case "":
                throw new DukeException("Your command cannot be empty!");
            case "list":
                Duke.printList().printMessage();
                break;
            case "bye":
                break;
            default:
                throw new DukeException("☹ OOPS!!! I don't understand that!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println(Message.greet());

        String input = scanner.next();

        while (!input.equals("bye")) {
            try {
                Duke.applyCommand(input);
            } catch (DukeException e) {
                Message errorMessage = new Message("\t" + e.getMessage());
                errorMessage.printMessage();
            } finally {
                input = scanner.next();
            }
        }

        System.out.println(Message.farewell());
    }
}
