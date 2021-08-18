import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
      return "[" + getStatusIcon() + "] " + description;
    }
}
public class Duke {
//    static String myList = "";

    static List<Task> toDo = new ArrayList<>();

//    static void add(String newItem){
//        int count = toDo.size();
//        myList += "     " + count + ". " + newItem + "\n";
//    }

    public static void main(String[] args) {

        String item;
        String line = "-----------------------------------------";
        Scanner myObj = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! Im Duke\n" + "What can I do for you?");
        System.out.println(line);

        while(myObj.hasNext()){
            System.out.println();
            item = myObj.nextLine();

            System.out.println(item);//User input typed
            if(item.equals("bye")){
                System.out.println(line);
                System.out.println("     " + "Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if(item.equals("list")){//View list of tasks
                System.out.println(line);
                for(int i = 0; i < toDo.size();i++){
                    System.out.println("     " + String.valueOf(i+1) + ". " + toDo.get(i).toString());
                }
                System.out.println(line);
                continue;
            }
            if(item.contains("done")){
                item = item.replaceAll("\\D+","");//Extracts number from input
                int completedItem = Integer.parseInt(item);
                toDo.get(completedItem-1).markAsDone();//Set the task to done
                System.out.println(line);
                System.out.println("     " + "Nice! I've marked this task as done:");
                System.out.println("     " + toDo.get(completedItem-1).toString());
                System.out.println(line);
                continue;
            }
            toDo.add(new Task(item));//Added new task to arraylist
//            add(item);
            System.out.println(line);
            System.out.println("     added: " + item);//Added item
            System.out.println(line);
        }
//myObj.close();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        for(String s: toDo){
//
//            System.out.println(s);
//        }

    }
}
