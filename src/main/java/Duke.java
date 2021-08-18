import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = new String();
        ArrayList<String> store = new ArrayList<>();
        System.out.println("Hello! I'm Tom! What can I do for you?");
        command = sc.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")){
                for(int i = 0; i < store.size(); i++){
                    System.out.println((i + 1) + "." + store.get(i));
                }
            } else {
                store.add(command);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
