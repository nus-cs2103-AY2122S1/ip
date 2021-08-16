import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected boolean isTerminated;
    protected ArrayList<Task> list;

    public Duke() {
        this.isTerminated = false;
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo =
                          "██    ██ ██  ██████ ████████  ██████  ██████  \n"
                        + "██    ██ ██ ██         ██    ██    ██ ██   ██ \n"
                        + "██    ██ ██ ██         ██    ██    ██ ██████  \n"
                        + " ██  ██  ██ ██         ██    ██    ██ ██   ██ \n"
                        + "  ████   ██  ██████    ██     ██████  ██   ██ \n";
        Duke victor = new Duke();
        victor.greet();
        Command command;
        Scanner scanner = new Scanner(System.in);

        while(!victor.isTerminated) {
            command = victor.listen(scanner);
            victor.executeCommand(command);
        }
        scanner.close();
    }

    private void executeCommand(Command command) {
        try {
            switch (command.getInstruction()) {
                case "list":
                    printList();
                    break;
                case "todo":
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    createTask(new Todo(command.getParameter_1()));
                    break;
                case "deadline":
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (command.getParameter_2() == null) {
                        throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    createTask(new Deadline(command.getParameter_1(), command.getParameter_2()));
                    break;
                case "event":
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    if (command.getParameter_2() == null) {
                        throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
                    }
                    createTask(new Event(command.getParameter_1(), command.getParameter_2()));
                    break;
                case "done":
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
                    }
                    if (Integer.parseInt(command.getParameter_1()) < 1 ||
                            Integer.parseInt(command.getParameter_1()) > list.size()) {
                        throw new DukeException("☹ OOPS!!! The task number is invalid");
                    }
                    markAsDone(Integer.parseInt(command.getParameter_1()));
                    break;
                case "delete":
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
                    }
                    if (Integer.parseInt(command.getParameter_1()) < 1 ||
                            Integer.parseInt(command.getParameter_1()) > list.size()) {
                        throw new DukeException("☹ OOPS!!! The task number is invalid");
                    }
                    deleteTask(Integer.parseInt(command.getParameter_1()));
                    break;
                case "bye":
                    bye();
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DukeException e) {
            formatPrint(e.getMessage());
        }
    }

    private void greet() {
        String greeting = "Howdy, pardner!";
        String question = "Is there anything old Vic can do you for?";
        formatPrint(greeting, question);
    }

    private Command listen(Scanner scanner) {
        String instruction = scanner.nextLine();
        return new Command(instruction);
    }

    private void markAsDone(int taskNumber) {
        Task task = this.list.get(taskNumber - 1);
        task.markAsDone();
        formatPrint("Nice! I've marked this task as done:", "  [X] " + task.getDescription());
    }

    private void printList() {
        int count = 1;
        System.out.print("\n    ____________________________________________________________" +
                "\n    Here are the tasks in your list:\n");
        for (Task task : this.list) {
            System.out.printf("     %d.%s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void createTask(Task task) {
        list.add(task);
        formatPrint("Got it. I've added this task:", "  " + task.toString(),
                "Now you have " + list.size() + " tasks in the list.");
    }

    private void deleteTask(int taskNumber) {
        Task task = this.list.get(taskNumber - 1);
        this.list.remove(task);
        formatPrint("Noted. I've removed this task:", "  " + task.toString(),
                "Now you have " + list.size() + " tasks in the list.");
    }

    private void bye() {
        String bye = "See ya round, buckaroo.";
        formatPrint(bye);
        terminate();
    }

    private void terminate() {
        this.isTerminated = true;
    }

    private void formatPrint(String... lines) {
        System.out.println("\n    ____________________________________________________________");
        for (String line : lines) {
            System.out.printf("     %s\n", line);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
