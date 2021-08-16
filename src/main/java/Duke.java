import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
<<<<<<< HEAD
        GreetingBot newBot = new GreetingBot();
        newBot.startBot();
=======
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        while(true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(nextLine);
            }
        }
>>>>>>> d5cb34ce6514de3e22da251f53e58b7cbf3d90cf
    }







//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}




