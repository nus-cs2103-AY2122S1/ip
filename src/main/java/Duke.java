import java.util.*;

public class Duke {
    private static Task[] list = new Task[100];
    private static int listIndex = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("\n_________________________\n" + "Bye. Hope to see you again soon!" + "\n_________________________\n");
                return;
            } else if (str.equalsIgnoreCase("list")){
                System.out.println("\n_________________________\n Here are the tasks in your list:");
                for(int i = 0 ; i < listIndex; i++){
                    System.out.println((i + 1) + ". " + list[i].toString());
                }
                System.out.println("\n_________________________\n");
            } else if (str.contains("done")){
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list[index].toString());
            } else if (str.contains("todo") || str.contains("deadline") || str.contains("event")){
                Task newTask = new Task("null");
                if(str.contains("todo")){
                    newTask = new Todo(str.split(" ",2)[1]);
                }
                if(str.contains("deadline")) {
                    newTask = new Deadline(str.split("/by")[0].split(" ",2)[1],str.split("/by")[1]);
                }
                if (str.contains("event")) {
                    newTask = new Event(str.split("/at")[0].split(" ",2)[1],str.split("/at")[1]);
                }
                list[listIndex] = newTask;
                listIndex++;
                System.out.println("\n_________________________\n" + "Got it. I've added this task:\n" + newTask + "\nNow you have " + listIndex + " tasks in the list." + "\n_________________________\n");
            } else {
                System.out.println("\n_________________________\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n_________________________\n");
            }
        }
    }
}
