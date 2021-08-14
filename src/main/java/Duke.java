import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        String line = "-------------------------------------------------------\n\n";
        // greeting
        System.out.print(
                line +
                "\tHello! I'm Duke\n\n" +
                "\tWhat can I do for you?\n\n" +
                line
        );

        Scanner scanner = new Scanner(System.in);
        try {
            String text;
            boolean leave = false;
            while (!leave) {
                // get text
                text = scanner.nextLine();

                if (text.equals("bye")) {
                    // exit
                    text = "\tBye. Hope to see you again soon!\n";
                    leave = true;
                } else if (text.equals("list")) {
                    // print list
                    if (list.isEmpty()) text = "\tThere are no items in the list.\n";
                    else {
                        text = "";
                        for (int i = 0; i < list.size(); i++) {
                            text += "\t" + (i + 1) + ". " + list.get(i) + "\n";
                        }
                    }
                } else {
                    // add text to list
                    list.add(text);
                    text = "\tadded: " + text + "\n";
                }
                System.out.print(
                        "\n" + line +
                        text +
                        "\n" + line
                );
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println(e);
        }
    }
}
