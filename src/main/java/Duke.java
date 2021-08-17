import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Good Day Sir/Mdm, I am Duke\nWhat can I do for you?\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();

            if (s.equals("bye")) {
                System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
                break;
            }

            System.out.println(s + "\n");

        }

    }

}



