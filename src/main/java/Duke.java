import java.util.*;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("-----------------------------------------");
        while(true) {
            String repeat = sc.nextLine();
            if (repeat.equals("bye")) {
                System.out.println("-----------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------");
                sc.close();
                break;
            }
            System.out.println("-----------------------------------------");
            System.out.println(repeat);
            System.out.println("-----------------------------------------");
        }
    }


}
