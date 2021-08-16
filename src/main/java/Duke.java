import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        boolean stop = false;
        ArrayList inputArr = new ArrayList();


        while (!stop) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < inputArr.size(); i++) {
                    System.out.println((i+1) + ". " + inputArr.get(i));
                }
            }
            else if (input.equals("bye")) {
                stop = true;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                inputArr.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
