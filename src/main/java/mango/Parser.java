package mango;
import java.util.Scanner;

/**
 * Represents a parser that makes sense of the user's commands by looking for keywords in the input.
 */
public class Parser {

    /**
     * Parses user input through the Scanner, and manipulates the list of tasks according to the commands.
     */
    public static void parse(TaskList tasks) {
        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.nextLine()).equals("bye")) {
            try {
                if (str.equals("list")) {
                    tasks.printList();
                } else if (str.contains("done")) {
                    tasks.complete(Character.getNumericValue(str.charAt(5)));
                } else if (str.contains("delete")) {
                    tasks.delete(Character.getNumericValue(str.charAt(7)));
                } else {
                    tasks.add(str);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
