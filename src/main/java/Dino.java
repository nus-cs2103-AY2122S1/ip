import java.util.Scanner;

public class Dino {
    private TaskList taskList;
    private final Storage storage;

    public Dino(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadStorage());
    }

    public void greeting() {
       System.out.println("Hello! I'm dino, your cute dinosaur bot.\n"
                + "Anything I can do for you?");
    }

    public void farewell() {
        System.out.println("Goodbye~ \n"
                + "Your cute Dino is always around you :D");
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public void readCommand(String cmd) {
        try {
            if (cmd.equals("list")) {
                if (taskList.getTaskList().size() == 0) throw new EmptyListException();
                else taskList.printTaskList();
            } else {
                String firstWord = Tool.getFirstWord(cmd);
                if (firstWord.equals("done") || firstWord.equals("delete")) {
                    int index = Tool.getIndex(cmd, firstWord);
                    if (index > 0) taskList.processTask(firstWord, index);
                } else {
                    taskList.addTask(cmd);
                }
            }
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        this.greeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                this.storage.saveToStorage(this.taskList.getTaskList());
                this.farewell();
                break;
            } else this.readCommand(input);
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Dino("data/dino.txt").run();
    }
}
