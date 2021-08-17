import java.util.Scanner;

public class Duke {

//    private Storage storage;
//    private TaskList tasks;
//    private Ui ui;

    public Duke() {
    }

    public void run() {
        Welcome.of().exec();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("What can I do for you?");
        while (!(input = scanner.nextLine()).equals("bye")) {
            Echo.of(input).exec();
        }
        Exit.of().exec();
    }

    public static void main(String[] args) {

        new Duke().run();
    }
}
