import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String [] response = new String[100];
        int ctr = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("_______________________________________________");

        while (scanner.hasNext()) {
            response[ctr] = scanner.nextLine();
            if (response[ctr].equals("list")) {
                System.out.println("_______________________________________________");
                for (int i = 0; i < ctr; i++) {
                    System.out.println( (i + 1) + ". " + response[i] );
                }
                System.out.println("_______________________________________________");
            } else if (response[ctr].equals("bye")) {
                System.out.println("_______________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("_______________________________________________");
                System.exit(0);
            } else {
                System.out.println("_______________________________________________");
                System.out.println("added: " + response[ctr]);
                System.out.println("_______________________________________________");
                ctr++;
            }
        }
    }
}
