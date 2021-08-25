import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class Duke {
    public static void main(String[] args) {

        // Source : https://patorjk.com/software/taag/#p=display&f=Standard&t=Dude
        String logo2 = " ____            _      \n"
                + "|  _ \\ _   _  __| | ___ \n"
                + "| | | | | | |/ _` |/ _ \\\n"
                + "| |_| | |_| | (_| |  __/\n"
                + "|____/ \\__,_|\\__,_|\\___|\n";

        System.out.println("Hello from\n" + logo2);
        System.out.println("Hello! I'm Dude");


        Scanner stdIn =  new Scanner(System.in);
        boolean isRunning = true;
        ToDoList toDoList;

        File dukeData = new File("./duke_data.txt");

        System.out.println("What can I do for you?");

        try {
            dukeData.createNewFile();
            toDoList = new ToDoList(dukeData);

        } catch (IOException e) {
            System.out.println("Cannot create/access data file\n" + e.toString());
            toDoList = new ToDoList();
        }

        while (isRunning) {
            String input = getPrompt(stdIn);
            isRunning = processInput(input, toDoList);
        }
    }

    static String getPrompt(Scanner sc) {
        return sc.nextLine();
    }

    static boolean processInput(String str, ToDoList toDoList){
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            toDoList.saveToFile();
            return false;
        } else if (str.equals("list")) {
            System.out.println(toDoList.list());
        } else if (str.startsWith("done")){
            String substr = str.replaceFirst("done ", "");
            int index = Integer.parseInt(substr);
            System.out.println(toDoList.markDone(index));
        } else if (str.startsWith("delete")){
            String substr = str.replaceFirst("delete ", "");
            int index = Integer.parseInt(substr);
            System.out.println(toDoList.delete(index));
        } else if (str.startsWith("todo")) {
            String substr = str.replaceFirst("todo", "").stripLeading();
            System.out.println(toDoList.addToDo(substr));
        } else if (str.startsWith("deadline")) {
            String substr = str.replaceFirst("deadline", "").stripLeading();
            String[] substrArray = substr.split(" /by ", 2);
            System.out.println(toDoList.addDeadLine(substrArray[0], substrArray[1]));
        } else if (str.startsWith("event")) {
            String substr = str.replaceFirst("event", "").stripLeading();
            String[] substrArray = substr.split(" /at ", 2);
            System.out.println(toDoList.addEvent(substrArray[0], substrArray[1]));
        } else {
            System.out.println("??? Unknown command!");
        }
        return true;
    }

}
