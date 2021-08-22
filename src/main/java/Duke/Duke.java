package Duke;

import java.util.Locale;

import Task.Task;


public class Duke {
    public enum Commands{
        BYE,
        DONE,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT;
    }

    private final TaskList taskList;
    private final Storage store;
    private final Ui ui;

    public Duke(){
        store = new Storage("./data/duke.txt");
        taskList = new TaskList();
        this.store.retrieveTasks(taskList);
        this.ui = new Ui();
        System.out.println("Hello! I'm Duke.Duke\n" + "What can I do for you?");
    }

    private void run(){
        String input = ui.getInput();
        String[] cmd_args = Parser.parseInput(input);
        String first = cmd_args[0].toUpperCase(Locale.ROOT);
        String rest = cmd_args[1].trim();
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
            input = ui.getInput();
            cmd_args = Parser.parseInput(input);
            first = cmd_args[0].toUpperCase(Locale.ROOT);
            rest = cmd_args[1].trim();
        }
    }


    private void close(){
        this.store.saveToFile(this.taskList);
        this.ui.close();
        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

}
