import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean bye = false;
        String[] intro = {"What can I do for you?", "How can I help you?", "What's up?", "What's good?"};
        String[] list = new String[100];
        int listLength = 0;

        System.out.println("  ____________________________________________________________");
        System.out.printf("  Hello! I'm Duck.\n  %s\n", intro[(int)(intro.length * Math.random())]);
        System.out.println("  ____________________________________________________________\n");

        while (!bye) {
            System.out.print("> ");
            String text = input.nextLine();

            System.out.println("  ____________________________________________________________");

            if (text.equals("bye")) {
                System.out.println("  See you next time!");
                bye = true;
            } else if (text.equals("list")) {
                for (int i = 0; i < listLength; ++i) {
                    System.out.println("  " + (i + 1) + ". " + list[i]);
                }
            } else {
                list[listLength++] = text;
                System.out.println("  added: " + text);
            }

            System.out.println("  ____________________________________________________________\n");
        }
    }
}

