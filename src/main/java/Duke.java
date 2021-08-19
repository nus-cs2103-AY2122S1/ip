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

    private final Scanner Sc;
    private final TaskList taskList;

    public Duke(){
        Sc = new Scanner(System.in);
        taskList = new TaskList();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private void run(){
        String first = this.getFirst();
        String rest = this.getRest();
        while(true){
            try {
                Commands cmd = Commands.valueOf((first));
                switch (cmd) {
                    case BYE:
                        this.close();
                        return;
                    case LIST:
                        this.taskList.printTasks();
                        break;
                    case DONE:
                        this.taskList.doneTask(Integer.parseInt(rest));
                        this.taskList.printTasks();
                        break;
                    case DELETE:
                        this.taskList.deleteTask(Integer.parseInt(rest));
                        break;
                    default:
                        Task newTask = Task.taskFactory(cmd, rest);
                        this.taskList.addToStorage(newTask);
                }
            }catch (DukeException e){
                System.out.println(e);
            }catch (IllegalArgumentException c){
                System.out.println("command not found");
            }
            first = this.getFirst();
            rest = this.getRest();
        }
    }

    private String getFirst(){
        return this.Sc.next().toUpperCase(Locale.ROOT);
    }

    private String getRest(){
        return this.Sc.nextLine().trim();
    }

    private void close(){
        System.out.println("Bye. Hope to see you again soon!");
        this.Sc.close();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

}
