package main.java;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 */
public class Duke {
    //Duke initialisation
    private static final String line = "\t____________________________________________________________";
    private static final String intro = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String bye = "Bye. Hope to see you again soon!";
    private static String[] list = new String[100];
    private static int listCount = 0;

    /**
     * Provides horizontal lines with indentation.
     *
     * @param str
     * @return formatted reply
     */
    private static void reply(String str) {
         System.out.println(line);
         System.out.println("\t " + str);
         System.out.println(line + "\n");
    }

    /**
     * Prints out the list.
     */
    private static void printList() {
        System.out.println(line);
        if (listCount == 0) {
            System.out.println("\t List is empty");
        } else {
            for (int i = 0; i < listCount; i++) {
                System.out.println("\t " + (i + 1) + ". " + list[i]);
            }
        }
        System.out.println(line + "\n");
    }

    public static void main(String[] args) {
        reply(intro);
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        while (on) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    on = false;
                    reply(bye);
                    sc.close();
                    break;
                case "list":
                    printList();
                    break;
                default:
                    list[listCount++] = input;
                    reply("added: " + input);
            }
        }
    }
}
