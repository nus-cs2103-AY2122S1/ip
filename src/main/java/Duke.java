import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(greet());
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(echo(input));
            input = sc.nextLine();
        }

        System.out.println(exit());
    }

    public static String greet() {
        String output = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";

        return output;
    }

    public static String echo(String input) {
        String output = "    ____________________________________________________________\n"
                + "     " + input +"\n"

                + "    ____________________________________________________________\n";

        return output;
    }

    public static String exit() {
        String output = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        return output;
    }


}
