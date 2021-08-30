import java.util.Scanner;
import java.time.format.DateTimeParseException;


public class Duke {
    private Scanner in = new Scanner(System.in);
    private CompilationOfFiles storage;
    private ListOfTasks xs;
    private Ui ui;

    public Duke (String filepath) {
        ui = new Ui();
        storage = new CompilationOfFiles(filepath);
        xs = new ListOfTasks();
        storage.loadAndSaveFile(xs);
    }

    public void run() {
        ui.startMessage();
        boolean isEnd = false;
        while (!isEnd) {
            String command = in.nextLine();
            isEnd = Parser.handleCommand(command,xs);
        }
        ui.endMessage();
    }
    public static void main (String[]args) {
        new Duke("data/duke.txt").run();
    }

//        public static void main (String[]args) {
//        private Scanner in = new Scanner(System.in);
//        private ListOfTasks xs = new ListOfTasks();
//        CompilationOfFiles.loadAndSaveFile(xs);
//
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("____________________________________________________________");
//        System.out.println("Hello! I'm Duke");
//        System.out.println("What can I do for you?");
//        System.out.println("____________________________________________________________");
//        String command = in.nextLine();
//        String temp = command;
//
//        System.out.println("    ____________________________________________________________");
//        System.out.println("    Bye. Hope to see you again soon!");
//        System.out.println("    ____________________________________________________________");
//
//    }
}

