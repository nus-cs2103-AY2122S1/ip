import java.util.*;
public class Duke {
    public static void main(String[] args) {
        boolean userEnded = false;
        ArrayList<Task> userInputs = new ArrayList<>();
        System.out.println("Hello! I'm Duke What can I do for you?"); 
        while (!userEnded) {
            Scanner sc = new Scanner(System.in);
            String str= sc.nextLine();
            if (str.equals("bye")) {
                userEnded = true;
            } else if (str.startsWith("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                try {
                    int taskIndex = Integer.parseInt(str.replaceAll("[^0-9]", "")); 
                    Task task = userInputs.get(taskIndex-1);
                    task.markAsDone();
                    System.out.println(" [" + task.getStatusIcon() + "] " + task); 
                } catch(Exception e) {
                    System.out.println(e); 
                }
            } else if (str.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < userInputs.size();i++) {
                    Task task =  userInputs.get(i);
                    System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task); 
                }
            } else {
                userInputs.add(new Task(str));
                System.out.println("added: " + str); 
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
