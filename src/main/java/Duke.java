import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage();
        Tasks tasks = storage.getTasks();

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("clear tasks")) {
                storage.resetTasks();
            } else if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("\tYou haven't added any tasks yet\n");
                } else {
                    System.out.println(tasks);
                }
            } else {
                try {
                    handleCompoundCommand(tasks, input);
                    storage.updateStorage();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            input = scanner.nextLine();
        }
        scanner.close();

    }

    private static void handleCompoundCommand(Tasks tasks, String input) {
        String[] commandAndDesc = input.split(" ", 2);
        String command = commandAndDesc[0];
        String description = commandAndDesc.length == 2 ? commandAndDesc[1] : "";

        switch (command) {
            case "deadline":
                tasks.addTask(new Deadline(description));
                break;
            case "event":
                tasks.addTask(new Event(description));
                break;
            case "todo":
                tasks.addTask(new ToDo(description));
                break;
            case "done":
            case "delete":
                try {
                    int taskNum = Integer.parseInt(description);
                    Task inFocus = tasks.getTask(taskNum);
                    if (inFocus == null) {
                        System.out.println("\tSorry, I can't seem to find that task\n");
                    } else {
                        if (command.equals("done")) {
                            inFocus.markAsDone();
                        } else {
                            tasks.deleteTask(inFocus);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\tI'm Sorry, WHAT?!?!\n");
                }
                break;
            default:
                if (command.equals("")) {
                    System.out.println("\tTake your time :)\n");
                } else {
                    System.out.println("\tI don't understand that command (yet...)\n");
                }
                break;
        }
    }

}
