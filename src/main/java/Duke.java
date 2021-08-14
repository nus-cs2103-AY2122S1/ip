import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        drawLine();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String userInput = sc.nextLine();
            if (userInput.toLowerCase(Locale.ROOT).equals("bye")) {
                drawLine();
                System.out.println("    " + "Bye. Hope to see you again soon!");
                drawLine();
                break;
            }
            drawLine();
            System.out.println("    " + userInput);
            drawLine();
        }
    }

    //Draws vertical line to seperate users speech from duke
    public static void drawLine() {
        System.out.println("------------------------");
    }

}
