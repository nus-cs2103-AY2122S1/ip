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
    private final Storage store;

    public Duke(){
        Sc = new Scanner(System.in);
        store = new Storage("./data/duke.txt");
        taskList = new TaskList();
        this.store.retrieveTasks(taskList);
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
        this.store.saveToFile(this.taskList);
        System.out.println("Bye. Hope to see you again soon!");
        this.Sc.close();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

}
