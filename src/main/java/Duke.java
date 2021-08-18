import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String first = Sc.next();
        String rest = Sc.nextLine();

        while(true) {
            try {
                if (first.equals("bye")) {
                    break;
                }else if(first.equals("list")){
                    TaskList.printTasks();
                }else if(first.equals("done")){
                    int taskID = Integer.parseInt(rest.trim());
                    TaskList.doneTask(taskID);
                    TaskList.printTasks();
                }else{
                    Task newTask = Task.taskFactory(first, rest);
                    TaskList.addToStorage(newTask);
                }
            }catch (DukeException e){
                System.out.println(e);
            }
            first = Sc.next();
            rest = Sc.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
        Sc.close();
    }

}
