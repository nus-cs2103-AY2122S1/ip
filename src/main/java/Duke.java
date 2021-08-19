import java.util.Locale;
import java.util.Scanner;


public class Duke {
    enum Commands{
        BYE,
        DONE,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT;
    }
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String first = Sc.next().toUpperCase(Locale.ROOT);
        String rest = Sc.nextLine().trim();

        while(true) {
            if (first.equals("BYE")) {
                break;
            }
            try {
                Commands cmd = Commands.valueOf((first));
                switch (cmd) {
                    case LIST:
                        TaskList.printTasks();
                        break;
                    case DONE:
                        TaskList.doneTask(Integer.parseInt(rest));
                        TaskList.printTasks();
                        break;
                    case DELETE:
                        TaskList.deleteTask(Integer.parseInt(rest));
                        break;
                    default:
                        Task newTask = Task.taskFactory(cmd, rest);
                        TaskList.addToStorage(newTask);
                }
            }catch (DukeException e){
                System.out.println(e);
            }catch (IllegalArgumentException c){
                System.out.println("command not found");
            }
            first = Sc.next().toUpperCase(Locale.ROOT);
            rest = Sc.nextLine().trim();

        }
        System.out.println("Bye. Hope to see you again soon!");
        Sc.close();
    }
}
