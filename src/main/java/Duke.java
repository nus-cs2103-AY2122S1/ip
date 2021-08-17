import java.util.Scanner;

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
                    + "    how can i help you today ? ";


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

        // returns true if the scanner has another input
        while (input.hasNextLine()) {
            // reads user input and modifies it to lower case
            String beforeModif = input.nextLine();
            String nextInput = beforeModif.toLowerCase();
            System.out.println("------------------------------------");

            if (nextInput.equals("bye")) {
                System.out.println(" ▹ Bye. Hope to see you again soon!");
                System.out.println("────────────────────────────────────");
                input.close();
                break;
            } else {
                System.out.println(" ▹ " + nextInput);
                System.out.println("────────────────────────────────────");
            }
        }
    }
}
