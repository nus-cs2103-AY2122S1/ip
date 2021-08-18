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
                default:
                    storage.add(new Task(first + rest));
                    System.out.println("added: " + first + rest);

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
}
