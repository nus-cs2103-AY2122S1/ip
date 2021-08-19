import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String startUp = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        String[] list = new String[100];
        int count = 0;

        System.out.println(line + startUp + line);

        while(true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();

            if (input.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < list.length; i++) {
                    if (list[i] == null) {
                        break;
                    }
                    System.out.println(" " + (i + 1) + ". " + list[i]);
                }
                System.out.print(line);
                continue;
            }
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!" + System.lineSeparator() + line);
                break;
            }


            System.out.println(line + " " + "added: " + input + System.lineSeparator() + line);
            list[count] = input;
            count++;

        }
    }
}
