import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] storage = new Task[100];
        int storageCount = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("------------------");
        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 1; i <= storageCount; i++) {
                        Task task = storage[i - 1];
                        System.out.printf("%d: [%s] %s\n", i, task.getStatusIcon(), task.getDescription());
                    }
                    System.out.println("------------------");
                    break;
                default:
                    String[] splitInput = input.split(" ");
                    if ((splitInput[0]).equals("done")) {
                        int taskNumber;
                        if (splitInput.length != 2) {
                            System.out.println("Please key in done [number].");
                        } else {
                            try {
                                taskNumber = Integer.parseInt(splitInput[1]);
                                if (taskNumber < 1 || taskNumber > storageCount) {
                                    throw new NumberFormatException();
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number");
                                System.out.println("------------------");
                                continue;
                            }
                            if (storage[taskNumber - 1].markAsDone()) {
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println("    [X] " + storage[taskNumber - 1].getDescription());
                        }}
                    } else if (storageCount < 100) {
                        storage[storageCount++] = new Task(input);
                        System.out.println("added: " + input);
                    } else {
                        System.out.println("Maximum storage size reached.");
                    }
                    System.out.println("------------------");
            }
        }
    }
}
