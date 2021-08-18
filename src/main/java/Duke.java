import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static String[] itemList = new String[100];
    private static int index = 0;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if (input.equals("list")) {
                for(int i = 0; i < index; i++){
                    System.out.println((i + 1) + ". " + itemList[i]);
                }
            }else {
                System.out.println("added: " + input);
                itemList[index++] = input;
            }
        }
    }
    private static void addList(String input) {

    }
}
