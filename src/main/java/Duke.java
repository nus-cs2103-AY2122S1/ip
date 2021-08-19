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

    private Scanner Sc;
    private TaskList taskList;

    public Duke(){
        Sc = new Scanner(System.in);
        taskList = new TaskList();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        String first = bot.getCommand();
        String rest = bot.getRest();
        while(true){
            try {
                Commands cmd = Commands.valueOf((first));
                switch (cmd) {
                    case BYE:
                        bot.close();
                        return;
                    case LIST:
                        bot.taskList.printTasks();
                        break;
                    case DONE:
                        bot.taskList.doneTask(Integer.parseInt(rest));
                        bot.taskList.printTasks();
                        break;
                    case DELETE:
                        bot.taskList.deleteTask(Integer.parseInt(rest));
                        break;
                    default:
                        Task newTask = Task.taskFactory(cmd, rest);
                        bot.taskList.addToStorage(newTask);
                }
            }catch (DukeException e){
                System.out.println(e);
            }catch (IllegalArgumentException c){
                System.out.println("command not found");
            }
            first = bot.getCommand();
            rest = bot.getRest();
        }
    }

    private String getCommand(){
        return this.Sc.next().toUpperCase(Locale.ROOT);
    }

    private String getRest(){
        return this.Sc.nextLine().trim();
    }

    private void close(){
        System.out.println("Bye. Hope to see you again soon!");
        this.Sc.close();
    }

}
