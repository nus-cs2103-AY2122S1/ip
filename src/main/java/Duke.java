import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<String> todo = new ArrayList<>();

        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");

        while (true) {
            Scanner input = new Scanner (System.in);
            String action = input.nextLine();
            if (!action.equals("bye") && !action.equals("list")){
                todo.add(action);
                System.out.println("---------------------------------------------\n"
                        + "     added: " + action + "\n"
                        + "---------------------------------------------");
            } else if (action.equals("list")){
                System.out.println("---------------------------------------------");
                for (int i = 0; i < todo.size(); i++) {
                    int j = i + 1;
                    System.out.println("     " + j + ". " + todo.get(i));
                }
                System.out.println("---------------------------------------------");
            } else {
                System.out.println("---------------------------------------------\n"
                        + "     Bye. Hope to see you again soon!" + "\n"
                        + "---------------------------------------------");
                break;
            }
        }
        
    }
}
