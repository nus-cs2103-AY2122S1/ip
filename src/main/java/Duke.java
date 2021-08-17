import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Duke \n" + "    What can I do for you?\n" +
                "    ____________________________________________________________\n");
        boolean end;
        end = true;
        while (end) {
            String text = scanner.nextLine();
            String[] splitText = text.trim().split("\\s+");
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
                        System.out.println("Error: The value you inputted is not valid!");
                    }
                    break;
                default:
                    taskList.add(text);
                    break;
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
