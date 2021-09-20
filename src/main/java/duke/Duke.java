package duke;

import java.util.Scanner;


public class Duke {
    // Source : https://patorjk.com/software/taag/#p=display&f=Standard&t=Dude
    private static final String LOGO = " ____            _      \n"
            + "|  _ \\ _   _  __| | ___ \n"
            + "| | | | | | |/ _` |/ _ \\\n"
            + "| |_| | |_| | (_| |  __/\n"
            + "|____/ \\__,_|\\__,_|\\___|\n";

    /**
     * Contains all the driver code currently, will be refactored in the future.
     *
     * @param args The array of command line arguments received. Currently unused.
     */
    public static void main(String[] args) {

        System.out.println("Hello from\n" + Duke.LOGO);
        System.out.println("Hello! I'm Dude");

        Scanner stdIn = new Scanner(System.in);
        boolean isRunning = true;

        DataFile dataFile = new DataFile("./duke_data.txt");
        TaskList taskList = new TaskList(dataFile);
        taskList.sort();
        System.out.println("What can I do for you?");

        System.out.println(taskList.list());
        /*
        while (isRunning) {
            String input = getPrompt(stdIn);
            isRunning = processInput(input, taskList);
        }
        */

    }

    static String getPrompt(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Contains all the input parsing code currently, will be refactored in the future.
     *
     * @param str The line of input entered by the user via stdin
     * @param taskList The instance of TaskList used to store the current tasks
     */
    static boolean processInput(String str, TaskList taskList) {

        Task newTask = Parser.parseInput(str);

        if (newTask == null) {
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                taskList.save();
                return false;
            } else if (str.equals("list")) {
                System.out.println(taskList.list());
            } else if (str.startsWith("done")) {
                String substr = str.replaceFirst("done ", "");
                int index = Integer.parseInt(substr);
                System.out.println(taskList.markDone(index));
            } else if (str.startsWith("delete")) {
                String substr = str.replaceFirst("delete ", "");
                int index = Integer.parseInt(substr);
                System.out.println(taskList.delete(index));
            } else if (str.startsWith("find")) {
                String substr = str.replaceFirst("find ", "");
                System.out.println(taskList.findTask(substr));
            } else {
                System.out.println("??? Unknown command!");
            }
        } else {
            System.out.println(taskList.add(newTask));
        }
        return true;
    }

}
