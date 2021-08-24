import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readNextLine() {
        return this.sc.nextLine();
    }

    public void greeting() {
        System.out.println("Hello! I'm dino, your cute dinosaur bot.\n"
                + "Anything I can do for you?");
    }

    public void processExit(Storage storage, TaskList taskList) {
        storage.saveToStorage(taskList.getTaskList());
        System.out.println("Goodbye~ \n"
                + "Your cute Dino is always around you :D");
        this.sc.close();
    }

}
