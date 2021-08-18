import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                           "What can I do for you?");



//        while (true) {
//            String input = sc.nextLine();
//            if (!input.equals("bye")) {
//                System.out.println(input);
//            } else {
//                System.out.println("Bye. Hope to see you again soon!");
//                break;
//            }
//        }
        Recieve recieve = new Recieve();
        recieve.run();


    }
}
