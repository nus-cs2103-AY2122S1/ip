import java.util.Scanner;

public class Bern {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! I'm Bern, your trustworthy chatbot. \nWhat can I do for you?");
        while (true) {
            String input = myObj.nextLine();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                System.out.println("Bye. Hope to see you soon and hope you found my service useful!");
                break;
            }
        }
    }
}
