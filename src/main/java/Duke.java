import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = new String();
        System.out.println("Hello! I'm Tom! What can I do for you?");
        command = sc.nextLine();
        while(!command.equals("bye")){
            System.out.println(command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
