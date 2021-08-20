import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws InputNotValidError, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();
    }

    public static void run() throws InputNotValidError, IOException{
        System.out.println(great());
        ArrayList<String[]> data = Data.loadData("/Users/fukushimayuuichirou/Desktop/ip/data.txt");

        askToDo(data);
    }

    public static String great(){
        String greatSent = "\n" +
        "   ____________________________________________________________\n" +
        "   Hello! I'm Duke\n" +
        "   What can I do for you? \n" +
        "   ____________________________________________________________";

        return greatSent;
    }    

    public static void askToDo(ArrayList<String[]> data) throws InputNotValidError, IOException{
        Scanner input = new Scanner(System.in);
        TaskList tasklist = new TaskList();
        tasklist.LoadTask(data);
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
                Data.saveData(tasklist.returnTaskList());
                break;
            }else{
                tasklist.actionHalder(splited, false, false);
            }
        }
        input.close();
    }    
}
