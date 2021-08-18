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
                System.out.println("\n_________________________\n + Here are the tasks in your list:");

                for(int i = 0 ; i < listIndex; i++){
                    System.out.println((i + 1) + ". " + list[i].toString());
                }
                System.out.println("\n_________________________\n");
            } else if (str.contains("done")){
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list[index].toString());
            } else {
                //split description into task type and description
                Task newTask = new Task(str.split(" ",2)[0], str.split(" ",2)[1] );
                list[listIndex] = newTask;
                listIndex++;
                System.out.println("\n_________________________\n" + "Got it. I've added this task:\n" + newTask + "Now you have " + listIndex + " tasks in the list." + "\n_________________________\n");
            }
        }
    }
}
