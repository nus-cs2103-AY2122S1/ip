package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /**
     * Displays the greeting message.
     */
    public void showGreeting() {
        String output = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";

        System.out.println(output);
    }

    /**
     * Displays all the tasks in the taskList.
     * @param taskList the TaskList to be printed
     */
    public void printList(TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        for (int i = 0; i < lst.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, lst.get(i).toString()));
        }
        s.append("    ____________________________________________________________\n");
        System.out.println(s.toString());
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        String output = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     *
     * @param taskList the taskList to manipulate
     */
    public void readInput(TaskList taskList) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            input = input.trim();
            if (input.equals("")) {
                continue;
            }
            String output = Parser.parseInput(input, taskList, this);
            if (output != null) {
                System.out.println(output);
            }
        }


    }
}
