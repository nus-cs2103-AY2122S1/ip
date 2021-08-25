import java.util.Scanner;

public class Ui {
    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public void showStartUpError(DukeException e) {
        System.out.println(new DukeException("Error starting up."));
        System.out.println(e);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        String separator = "------------------------------------------------------------------";
        System.out.println(separator);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
