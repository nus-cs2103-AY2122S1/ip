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
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < store.size(); i++){
                    System.out.println((i + 1) + "." + store.get(i).toString());
                }
            } else if(words[0].equals("done")){
                int textNumber = Integer.parseInt(words[1]);
                store.get(textNumber - 1).done();
                System.out.println("Nice! I've marked this task as done:\n"
                        + store.get(textNumber - 1).toString());
            } else {
                Task task;
                if(words[0].equals("todo")) {
                    task = new ToDo(input.substring(5));
                } else if(words[0].equals("deadline")){
                    String content = input.substring(9,input.indexOf("/by")-1);
                    String by = input.substring(input.indexOf("/by") + 4);
                    task = new Deadline(content,by);
                } else if(words[0].equals("event")){
                    String content = input.substring(6,input.indexOf("/at")-1);
                    String at = input.substring(input.indexOf("/at") + 4);
                    task = new Event(content,at);
                } else {
                    task = new Task(input);
                }
                store.add(task);
                System.out.println("Got it. I've added this task:\n"
                                + task.toString()
                                + "\nNow you have "
                                + store.size() + " tasks in the list.");
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
