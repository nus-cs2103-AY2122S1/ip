import java.util.*;  
public class Duke {
    public static void main(String[] args) {
        boolean userEnded = false;
        System.out.print("Hello! I'm Duke What can I do for you?"); 
        while (!userEnded) {
            Scanner sc= new Scanner(System.in);
            String str= sc.nextLine();
            if (str.equals("bye")) {
                userEnded = true;
            } else {
                System.out.print(str); 
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
