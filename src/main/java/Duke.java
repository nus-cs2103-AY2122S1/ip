import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();
    }

    public static void run(){
        System.out.println(great());
        askToDo();
    }

    public static String great(){
        String greatSent = "\n" +
        "   ____________________________________________________________\n" +
        "   Hello! I'm Duke\n" +
        "   What can I do for you? \n" +
        "   ____________________________________________________________";

        return greatSent;
    }    

    public static void askToDo(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> todolist = new ArrayList<>();
        while(true){
            System.out.println("");
            String action = input.nextLine();
            if (action.equals("list")){
                System.out.println("    ____________________________________________________________");
                int count = 1;
                for (String eachaction : todolist){
                    System.out.println(String.valueOf(count) + ". " + eachaction);
                    count++;
                }
                System.out.println("    ____________________________________________________________");
            }else if(action.equals("bye")){
                String byesent = "\n" +
                "   ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "   ____________________________________________________________";
                System.out.println(byesent);
                break;
            }else{
                todolist.add(action);
                String item = "\n" +
                "   ____________________________________________________________\n" +
                "   added: " + action +  "\n" +
                "   ____________________________________________________________";
                System.out.println(item);
            }
        }
    }    
}
