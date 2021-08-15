import java.util.ArrayList;
import java.util.Random;
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
        if (command.isInstruction) {
            switch(command.getInstruction()) {
                case "list":
                    printList();
                    break;
                case "done":
                    markAsDone(Integer.parseInt(command.getParameter(1)));
                    break;
                case "bye":
                    bye();
                    break;
                default :
                    System.out.println("Invalid command");
            }
        } else {
            storeTask(new Task(command.getInstruction()));
            echo(command);
        }
    }

    private void greet() {
        String[] greetings = {"Hey there, friend.", "Howdy, pardner! Might I say, you're looking fit as a fiddle.",
                "Howdy, pardner! So, when do the rustlers show up?", "Howdy, pardner!"};
        String question = "Is there anything old Vic can do you for?";
        Random rand = new Random();
        int random_int = rand.nextInt(greetings.length);
        String greeting = greetings[random_int];
        formatPrint(greeting, question);
    }

    private Command listen() {
        Scanner scanner = new Scanner(System.in);
        String instruction = scanner.nextLine();
        return new Command(instruction);
    }

    private void echo(Command command) {
        formatPrint("added: " + command.getInstruction());
    }

    private void storeTask(Task task) {
        list.add(task);
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
            System.out.printf("     %d.[%s] %s\n", count, task.getStatusIcon(), task.getDescription());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void bye() {
        String[] goodbye = {"Be seeing you.", "'Til our trails cross again, pardner.",
                "Well, happy trails, then!", "See ya round, buckaroo."};
        Random rand = new Random();
        int random_int = rand.nextInt(goodbye.length);
        String bye = goodbye[random_int];
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
