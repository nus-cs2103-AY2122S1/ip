import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<String> tasks = new ArrayList<>();

    private static void getLstItems() {
        int counter = 1;
        for (String task : tasks) {
            System.out.println(String.valueOf(counter) + ". " + task);
            counter += 1;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLines = "-----------------------------------------";
        System.out.println("Hello! I'm Naruto\nWhat can I do for you?\n" + horizontalLines);
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(horizontalLines);
                System.out.println("See ya! Hope to see you again!" + "\n" + horizontalLines);
                return;
            }
            if (userInput.equals("list")) {
                System.out.println(horizontalLines);
                getLstItems();
                System.out.println(horizontalLines);
                continue;
            }
            System.out.println(horizontalLines);
            tasks.add(userInput);
            System.out.println("added: " + userInput + "\n" + horizontalLines);
        }
    }
}
