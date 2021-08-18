import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> storage = new ArrayList<>();
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        String first = Sc.next();
        String rest = Sc.nextLine();

        while(true){
            if(first.equals("bye")){break;}

            switch (first){
                case "list":
                    Duke.printTasks();
                    break;

                case "done":
                    int taskID = Integer.parseInt(rest.trim());
                    if(taskID >= storage.size()){
                        System.out.println("no such task");
                    }else{
                        Task t = storage.get(taskID);
                        t.markDone();
                        Duke.printTasks();
                    }
                    break;

                case "todo":
                    ToDo newTodo = new ToDo(rest.trim());
                    addToStorage(newTodo);
                    break;

                case "deadline":
                    String[] name_by = rest.trim().split("/by");
                    Deadline newDeadLine = new Deadline(name_by[0], name_by[1]);
                    addToStorage(newDeadLine);
                    break;

                case "event":
                    String[] name_at = rest.trim().split("/at");
                    Event newEvent = new Event(name_at[0], name_at[1]);
                    addToStorage(newEvent);
                    break;

                default:
                    storage.add(new ToDo(first + rest));
            }
            first = Sc.next();
            rest = Sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        Sc.close();
    }

    private static void printTasks(){
        for (int i = 0; i < storage.size(); i++) {
            System.out.println(storage.get(i));
        }
    }

    private static void addToStorage(Task t){
        Duke.storage.add(t);
        System.out.println("You have " + storage.size() + " tasks in the list");
        System.out.println(t);
    }
}
