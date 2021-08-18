import java.util.Scanner;
public class Duke {
    private static String taskList[] = new String[100];
    private static int counter = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------------------\n");
        System.out.println("Hello! I am Jarvis :)\n");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------\n");

        String instruction;
        do {
            Scanner sc = new Scanner(System.in);
            instruction = sc.nextLine();
            switch (instruction) {
                case ("bye"):
                    System.out.println("\t" + "Bye! Hope to see you soon :)");
                    break;
                case ("list"):
                    int index = 0;
                    while (index < counter) {
                        System.out.println("\t" + (index + 1) + ". " + taskList[index] + "\n");
                        index++;
                    }
                    break;
                default:
                    addTask(instruction);
                    System.out.println("\tadded: " + instruction);
                    break;
            }
            System.out.println("----------------------------------\n");
        } while (!instruction.equals("bye"));
    }

    public static void addTask(String task) {
        taskList[counter] = task;
        counter++;
    }

}
