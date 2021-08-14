import java.util.Scanner;

public class Duke {

    /**
     * This is the main point of interaction of user and Duke.
     */
    public void run() {
        System.out.println("Hello I'm BugGenerator");
        System.out.println("What can I do for you?");

        Boolean end = false;
        Scanner s = new Scanner(System.in);
        while(!end) {
            String input = s.next(); //or nextLine?
            switch (input) {
                case "bye":
                    System.out.println("\t_______________________________");
                    System.out.println("\tBye. Hope to see you again soon!");
                    System.out.println("\t_______________________________");
                    end = true;
                    break;
                default:
                    System.out.println("\t_______________________________");
                    System.out.printf("\t%s\n", input);
                    System.out.println("\t_______________________________");
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
