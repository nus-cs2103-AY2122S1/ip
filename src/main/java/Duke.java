import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static Task[] storage = new Task[100];
    private static int storageCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("------------------");
        String input;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 1; i <= storageCount; i++) {
                        Task task = storage[i - 1];
                        String leadingSpace = " ".repeat((int) Math.log10(storageCount) - (int) Math.log10(i));
                        // For better formatting if numbers exceed 9
                        System.out.printf("%s%d: %s\n", leadingSpace, i, task);
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
                            System.out.println("    " + storage[taskNumber - 1]);
                        }}
                    } else if (storageCount < 100) {
                        try {
                            addTask(splitInput);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Maximum storage size reached.");
                    }
                    System.out.println("------------------");
            }
        }

        sc.close();
    }

    private static void addTask(String[] splitInput) {
        String action;
        StringBuilder descriptionBuilder = new StringBuilder();
        String preposition = null; // eg. at, by, etc
        StringBuilder dateBuilder = new StringBuilder();
        if (splitInput == null || splitInput.length < 2) {
            throw new DukeException("Invalid input. Please enter the action, followed by \"/at\" or \"/by\".\n" +
                    "For example: todo Buy a gift for mum\n" +
                    "For example: deadline CS2103T individual project /by 19 August\n" +
                    "For example: event CS2103T lecture /at 19 August");
        }
        action = splitInput[0];
        for (int i = 1; i < splitInput.length; i++) {
            if (preposition == null) {
                if (splitInput[i].contains("/")) {
                    preposition = splitInput[i].substring(1);
                } else {
                    descriptionBuilder.append(splitInput[i]).append(" ");
                }
            } else {
                dateBuilder.append(splitInput[i]).append(" ");
            }
        }
        if (descriptionBuilder.length() == 0) {
            throw new DukeException("Missing task description.");
        }
        Task newTask;
        switch (action) {
            case "todo":
                newTask = new Todo(descriptionBuilder.toString().trim());
                break;
            case "event":
                if (preposition == null || !preposition.equals("at")) {
                    throw new DukeException("Use the preposition \"at\".");
                } else if (dateBuilder.length() == 0) {
                    throw new DukeException("Enter the date of the event.");
                }
                newTask = new Event(descriptionBuilder.toString().trim(), dateBuilder.toString().trim());
                break;
            case "deadline":
                if (preposition == null || !preposition.equals("by")) {
                    throw new DukeException("Use the preposition \"by\".");
                } else if (dateBuilder.length() == 0) {
                    throw new DukeException("Enter the deadline.");
                }
                newTask = new Deadline(descriptionBuilder.toString().trim(), dateBuilder.toString().trim());
                break;
            default:
                throw new DukeException("Only todo, event or deadline allowed.");
        }
        storage[storageCount++] = newTask;
        System.out.println("Got it. I have added this task:");
        System.out.println("    " + newTask);
        System.out.println("Now you have " + storageCount + " task"
                + (storageCount == 1 ? " in the list" : "s in the list"));
    }
}
