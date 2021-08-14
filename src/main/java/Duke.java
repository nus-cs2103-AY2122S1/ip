import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
        + "Heyllo! Jack here\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n");

        boolean bye = false;
        Scanner myObj = new Scanner(System.in);
        System.out.println();

        while (!bye) {
            String temp = myObj.nextLine();
            if (temp.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + "Bye bye. Love you\n"
                        + "____________________________________________________________\n");
                bye = true;
            } else {
                System.out.println("____________________________________________________________\n"
                        + temp
                        + "\n"
                        + "____________________________________________________________\n");
            }

        }
    }
}
