import java.util.*;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
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
            System.out.println("\n_________________________\n");
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("\n_________________________\n");
                return;
            }

            else if (str.equalsIgnoreCase("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0 ; i < listIndex; i++){
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            }


            else if (str.contains("done")){
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list.get(index).toString());
            }

            else if(str.contains("delete")){
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                Task removedTask = list.get(index);
                listIndex--;
                System.out.println("Noted. I've removed this task:\n" + removedTask.toString() + "\nNow you have " + listIndex + " tasks in the list.");
                list.remove(index);
            }

            else if (str.contains("todo") || str.contains("deadline") || str.contains("event")){
                Task newTask = new Task("null");
                try {
                    if(str.contains("todo")){
                        newTask = new Todo(str.split(" ",2)[1]);
                    }
                    if(str.contains("deadline")) {
                        newTask = new Deadline(str.split("/by")[0].split(" ",2)[1],str.split("/by")[1]);
                    }
                    if (str.contains("event")) {
                        newTask = new Event(str.split("/at")[0].split(" ",2)[1],str.split("/at")[1]);
                    }
                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                    System.out.println("OOPS!!! your description is not complete or is in a wrong format");
                }
                list.add(newTask);
                listIndex++;
                System.out.println("Got it. I've added this task:\n" + newTask + "\nNow you have " + listIndex + " tasks in the list.");
            }

            else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" );
            }

            System.out.println("\n_________________________\n");
        }
    }
}
