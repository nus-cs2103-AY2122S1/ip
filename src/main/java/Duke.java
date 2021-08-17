import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // scan the line for the user's input
        List list = new List();
        boolean shouldContinue = true;
        while (shouldContinue) {//create loop for the chat
//            if (input.equals("bye") || input.equals("Bye") || input.equals("BYE")) {//input is bye
//                System.out.println("Bye. Hope to see you again soon!");
//                break;
//            } else if(input.equals("list")) {// shows the current list
//                list.show();
//                input = sc.nextLine();
//            } else { //input is not bye so add input to list
//                System.out.println("added: " + input);
//                list.add(input);
//                input = sc.nextLine();
//            }
            switch(input) {
                case "bye":
                case "Bye":
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    shouldContinue = false;
                    break;
                case "list":
                    list.show();
                    input = sc.nextLine();
                    break;
                default:
                    System.out.println("added: " + input);
                    list.add(input);
                    input = sc.nextLine();
            }
        }
        sc.close();
    }
}
