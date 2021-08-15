import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean bye = false;
        String[] intro = {"What can I do for you?", "How can I help you?", "What's up?", "What's good?"};
        String[] tone = {".", "?", "!", "~"};

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
            } else {
                System.out.println("  " + text + tone[(int)(tone.length * Math.random())]);
            }

            System.out.println("  ____________________________________________________________\n");
        }
    }
}

