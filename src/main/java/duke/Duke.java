package duke;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.storage = new Storage(filePath);
    }

    /**
     * runs the Duke chat bot
     */
    public void run() {
        String separator = "    ____________________________________________________________";
        System.out.println(Ui.WelcomeMessage());
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!Parser.parse(input).equals("bye")) {
            this.storage.save(this.ui.run(Storage.load(), input), this.filePath);
            input = in.nextLine();
        }
        System.out.println(separator);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(separator);
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\Rickie\\Documents\\University\\Year 2\\Semester 1\\CS2103T\\ip\\Data\\taskList.txt").run();
    }
}