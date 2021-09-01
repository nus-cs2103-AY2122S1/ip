package duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class Duke {

    /**
     * Contains all the driver code currently, will be refactored in the future.
     *
     * @param args The array of command line arguments received. Currently unused.
     */
    public static void main(String[] args) {

        // Source : https://patorjk.com/software/taag/#p=display&f=Standard&t=Dude
        String logo2 = " ____            _      \n"
                + "|  _ \\ _   _  __| | ___ \n"
                + "| | | | | | |/ _` |/ _ \\\n"
                + "| |_| | |_| | (_| |  __/\n"
                + "|____/ \\__,_|\\__,_|\\___|\n";

        System.out.println("Hello from\n" + logo2);
        System.out.println("Hello! I'm Dude");


        Scanner stdIn =  new Scanner(System.in);
        boolean isRunning = true;
        TaskList taskList;

        File dukeData = new File("./duke_data.txt");

        System.out.println("What can I do for you?");

        try {
            dukeData.createNewFile();
            taskList = new TaskList(dukeData);

        } catch (IOException e) {
            System.out.println("Cannot create/access data file\n" + e.toString());
            taskList = new TaskList();
        }

        while (isRunning) {
            String input = getPrompt(stdIn);
            isRunning = processInput(input, taskList);
        }
    }

    static String getPrompt(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Contains all the input parsing code currently, will be refactored in the future.
     *
     * @param str The line of input entered by the user via stdin
     * @param taskList The instance of Tasklist used to store the current tasks
     */
    static boolean processInput(String str, TaskList taskList){
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            taskList.saveToFile();
            return false;
        } else if (str.equals("list")) {
            System.out.println(taskList.list());
        } else if (str.startsWith("done")){
            String substr = str.replaceFirst("done ", "");
            int index = Integer.parseInt(substr);
            System.out.println(taskList.markDone(index));
        } else if (str.startsWith("delete")){
            String substr = str.replaceFirst("delete ", "");
            int index = Integer.parseInt(substr);
            System.out.println(taskList.delete(index));
        } else if (str.startsWith("todo")) {
            String substr = str.replaceFirst("todo", "").stripLeading();
            System.out.println(taskList.addToDo(substr));
        } else if (str.startsWith("deadline")) {
            String substr = str.replaceFirst("deadline", "").stripLeading();
            String[] substrArray = substr.split(" /by ", 2);
            System.out.println(taskList.addDeadLine(substrArray[0], substrArray[1]));
        } else if (str.startsWith("event")) {
            String substr = str.replaceFirst("event", "").stripLeading();
            String[] substrArray = substr.split(" /at ", 2);
            System.out.println(taskList.addEvent(substrArray[0], substrArray[1]));
        } else {
            System.out.println("??? Unknown command!");
        }
        return true;
    }

}
