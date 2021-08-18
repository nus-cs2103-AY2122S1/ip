import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = new String();
        ArrayList<Task> store = new ArrayList<>();

        System.out.println("Hello! I'm Tom! What can I do for you?");
        command = sc.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")){
                for(int i = 0; i < store.size(); i++){
                    System.out.println((i + 1) + "." + "[" + store.get(i).getStatusIcon() + "] " + store.get(i).getDescription());
                }
            } else if(command.substring(0,4).equals("done")){
                int textNumber = Integer.parseInt(command.substring(5));
                store.get(textNumber - 1).done();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + store.get(textNumber - 1).getStatusIcon() + "] " + store.get(textNumber - 1).getDescription());
            } else {
                store.add(new Task(command));
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
