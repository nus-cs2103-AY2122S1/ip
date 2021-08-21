import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");
    }

    public String getNextLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }
}
