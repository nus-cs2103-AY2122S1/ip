import java.util.Scanner;

public class Duke {

    private String[] tasks = new String[100];
    private int count = 0;

    /**
     * This is the main point of interaction of user and Duke.
     */
    public void run() {
        System.out.println("Hello I'm BugGenerator");
        System.out.println("What can I do for you?");

        Boolean end = false;
        Scanner s = new Scanner(System.in);
        while(!end) {
            String input = s.nextLine();
            switch (input) {
                case "":
                    break;
                case "list":
                    System.out.println("\t_______________________________");
                    for (int i = 0; i < count; i++) {
                        System.out.printf("\t%d. %s\n", i+1, tasks[i]);
                    }
                    System.out.println("\t_______________________________");
                    break;
                case "bye":
                    System.out.println("\t_______________________________");
                    System.out.println("\tBye. Hope to see you again soon!");
                    System.out.println("\t_______________________________");
                    end = true;
                    break;
                default:
                    tasks[count++] = input;
                    System.out.println("\t_______________________________");
                    System.out.printf("\tadded: %s\n", input);
                    System.out.println("\t_______________________________");
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
