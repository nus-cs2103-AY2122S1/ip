import java.util.Scanner;
public class Duke {
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
                    while (index < Task.getCounter()) {
                        System.out.println("\t" + (index + 1) + "." + Task.getTaskList()[index].getStatusIcon()
                                + " " + Task.getTaskList()[index].getDescription());
                        index++;
                    }
                    break;
                default:
                    if (instruction.startsWith("done")) {
                        int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
                        Task.getTaskList()[taskNum].markAsDone();
                        System.out.println("\tGood job! I have marked this task as done:");
                        System.out.println("\t\t" + Task.getTaskList()[taskNum].getStatusIcon()
                                + " " + Task.getTaskList()[taskNum].getDescription());
                    } else {
                        Task newTask = new Task(instruction);
                        newTask.addTask();
                        System.out.println("\tadded: " + instruction);
                    }
                    break;
            }
            System.out.println("----------------------------------\n");
        } while (!instruction.equals("bye"));
    }
}

