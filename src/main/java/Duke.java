import java.util.*;  
public class Duke {
    public static void main(String[] args) {
        boolean userEnded = false;
        ArrayList<String> userInputs = new ArrayList<>();
        System.out.println("Hello! I'm Duke What can I do for you?"); 
        while (!userEnded) {
            Scanner sc = new Scanner(System.in);
            String str= sc.nextLine();
            if (str.equals("bye")) {
                userEnded = true;
            } else if (str.equals("list")) {
                for (int i = 0; i < userInputs.size();i++) {
                    System.out.println((i + 1) + ". " + userInputs.get(i)); 
                }
            } else {
                userInputs.add(str);
                System.out.println("added: " + str); 
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
