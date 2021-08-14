import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] store = new String[100];
        int index = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            switch (in) {
                case "list":
                    for (int i = 0; i < index; i++) {
                        System.out.println((i + 1) + ". " + store[i]);
                    }
                    in = sc.nextLine();
                    break;
                default:
                    System.out.println("added: " + in);
                    store[index] = in;
                    index++;
                    in = sc.nextLine();
                    break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
