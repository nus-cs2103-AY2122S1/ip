import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        String dory = "      _                  \n"
                + "     | |                 \n"
                + "   __| |   ___    _ __   _   _ \n"
                + "  /    |  /   \\  | /__| | | | |\n"
                + " |   O | |  O  | | |    | |_| |\n"
                + "  \\__,_|  \\___/  |_|     \\__, |\n"
                + "   ________________________/  |\n"
                + "  |__________________________/ \n"
                + "\n"
                + " how to use:  \n"
                + "     * type down something and i'll remember  \n"
                + "     * type 'list' to show everything  \n"
                + "     * type 'bye' to leave \n"
                + " ▹";


        String fish = "                              ....\n"
                + "                             /¸... \\ \n"
                + "   hi my name is dory    .·´ \\ \\   `.¸.´)\n"
                + "   and i can help you   ( o   | |      (\n"
                + "    remember things      ·.¸ / /¸.·´`·.¸)\n"
                + "                             `\\___\\   ";

        // introduction to chat bot

        System.out.println(fish);
        System.out.println(dory);

        // creates a new Scanner instance
        // System.in is the keyboard input
        Scanner input = new Scanner(System.in);

        // arraylist to save the user's tasks
        ArrayList<String> tasks = new ArrayList<String>();

        // returns true if the scanner has another input
        while (input.hasNextLine()) {
            // reads user input and modifies it to lower case
            String beforeEdit = input.nextLine();
            String nextInput = beforeEdit.toLowerCase();
            System.out.println("------------------------------------");

            if (nextInput.equals("bye")) {
                System.out.println(" ▹ see you! hope to see you again :-) ");
                System.out.println("────────────────────────────────────");
                input.close();
                break;
            } else if (nextInput.equals("list")) {
                System.out.println(" ▹ here you go! ");
                // loop through the arraylist to show everything
                for (int count = 0; count < tasks.size(); count++) {
                    String eachTask = tasks.get(count);
                    int countFromOne = count + 1;
                    System.out.println(countFromOne +  ". " + eachTask);
                }

                System.out.println("────────────────────────────────────");
            } else {
                System.out.println(" ▹ added: " + nextInput);
                // add the task to the arraylist
                tasks.add(nextInput);
                System.out.println("────────────────────────────────────");
            }
        }
    }
}
