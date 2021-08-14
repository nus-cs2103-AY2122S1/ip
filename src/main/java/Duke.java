import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws InputNotValidError {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();
    }

    public static void run() throws InputNotValidError{
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

    public static void askToDo() throws InputNotValidError{
        Scanner input = new Scanner(System.in);
        TaskList tasklist = new TaskList(100);
        while(true){
            System.out.println("");
            String action = input.nextLine();
            String[] splited = action.split(" ", 2);
            String action1 = splited[0];
            if(action1.equals("bye")){
                String byesent = "\n" +
                "   ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "   ____________________________________________________________";
                System.out.println(byesent);
                break;
            }else{
                tasklist.actionHalder(splited);
            }
        }
    }    
}
