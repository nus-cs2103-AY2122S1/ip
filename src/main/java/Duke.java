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

        while(!victor.isTerminated) {
            command = victor.listen();
            victor.executeCommand(command);
        }
    }

    private void executeCommand(Command command) {
        switch(command.getInstruction()) {
            case "list":
                printList();
                break;
            case "todo":
                createTask(new Todo(command.getParameter_1()));
                break;
            case "deadline":
                createTask(new Deadline(command.getParameter_1(), command.getParameter_2()));
                break;
            case "event":
                createTask(new Event(command.getParameter_1(), command.getParameter_2()));
                break;
            case "done":
                markAsDone(Integer.parseInt(command.getParameter_1()));
                break;
            case "bye":
                bye();
                break;
            default :
                System.out.println("Invalid command");
        }
    }

    private void greet() {
        String greeting = "Howdy, pardner!";
        String question = "Is there anything old Vic can do you for?";
        formatPrint(greeting, question);
    }

    private Command listen() {
        Scanner scanner = new Scanner(System.in);
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
