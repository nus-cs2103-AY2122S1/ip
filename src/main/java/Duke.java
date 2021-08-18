import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String first = Sc.next();
        String rest = Sc.nextLine();

        while(true) {
            if (first.equals("bye")) {
                break;
            }
            try {
                switch (first) {
                    case "list":
                        TaskList.printTasks();
                        break;
                    case "done":
                        TaskList.doneTask(Integer.parseInt(rest.trim()));
                        TaskList.printTasks();
                        break;
                    case "delete":
                        TaskList.deleteTask(Integer.parseInt(rest.trim()));
                        break;
                    default:
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
