import java.util.Scanner;

public class Duke {
    enum ValidCommands {
        DEADLINE("deadline"), DELETE("delete"), DONE("done"), END("bye"), EVENT("event"), LIST("list"), TODO("todo");

        String input;

        ValidCommands(String input) {
            this.input = input;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Duke\n" + "    What can I do for you?\n" +
                "    ____________________________________________________________\n");


        boolean end;
        end = true;
        while (end) {
            String text = scanner.nextLine();
            String[] splitText = text.trim().split("\\s+", 2);
            System.out.println("    ____________________________________________________________\n");
            switch (splitText[0]) {
                case "list":
                    taskList.listOut();
                    break;
                case "bye":
                    end = false;
                    System.out.println("    Bye. Hope to see you again soon!");
                    break;
                case "done":
                    try {
                        int index = Integer.parseInt(splitText[1]);
                        taskList.finishTask(index);
                    } catch (Exception e) {
                        System.out.println("    Error: The value you inputted is not valid!");
                    }
                    break;
                case "todo":
                    try {
                        ToDo item = new ToDo(splitText[1]);
                        taskList.add(item);
                    }
                    catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] furtherSplitDeadline = splitText[1].trim().split("/by");
                        Deadline deadline = new Deadline(furtherSplitDeadline[0], furtherSplitDeadline[1]);
                        taskList.add(deadline);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  Use /by to add a deadline!");
                    }
                    break;
                case "event":
                    try {
                        String[] furtherSplitEvent = splitText[1].trim().split("/at");
                        Event event = new Event(furtherSplitEvent[0], furtherSplitEvent[1]);
                        taskList.add(event);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  Use /at to add a timing for the event!");
                    }
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(splitText[1]);
                        taskList.deleteTask(index);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  Did you input an invalid index?");
                    }
                    break;
                default:
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
