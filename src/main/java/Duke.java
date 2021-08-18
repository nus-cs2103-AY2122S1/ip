import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = new String();
        String command = new String();
        ArrayList<Task> store = new ArrayList<>();

        System.out.println("Hello! I'm Tom! What can I do for you?");
        input = sc.nextLine();
        while(!input.equals("bye")){
            String words[] = input.split(" ");
            if(input.equals("list")){
                for(int i = 0; i < store.size(); i++){
                    System.out.println((i + 1) + "." + "[" + store.get(i).getStatusIcon() + "] "
                            + store.get(i).getDescription());
                }
            } else if(words[0].equals("done")){
                int textNumber = Integer.parseInt(words[1]);
                store.get(textNumber - 1).done();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + store.get(textNumber - 1).getStatusIcon() + "] " + store.get(textNumber - 1).getDescription());
            } else {
                store.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
