import java.util.Scanner;

public class Duke {
    private static final String sep =
            "-------------------------------------------------------";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        printMsg("Hello! I'm Duke\n    I am your personal to-do list!");

        taskList inputs = new taskList(100);
        String input;

        while (true) {
            System.out.print(">>> ");
            input = s.nextLine();

            try {
                if (input.length() == 0) {
                    throw new DukeException("Input cannot be blank");
                } else if (input.equals("bye")) {
                    printMsg("Bye! Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    printMsg(inputs.getList());
                } else if (input.matches("done (\\d+)")) {
                    printMsg(inputs.markAsDone(Integer.parseInt(input.replaceAll("[^0-9]",
                            ""))));
                } else {
                    printMsg(inputs.addTask(input));
                }
            } catch (DukeException e) {
                printMsg(e.getMessage());
            }
        }
        s.close();
    }

    private static void printMsg(String... msgs) {
        System.out.println(sep);
        for (String msg:msgs) {
            System.out.println(msg);
        }
        System.out.println(sep);
    }
}
