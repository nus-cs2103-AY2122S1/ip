import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private TaskList taskList; //composite data type
    private Storage dukeStore; //composite data type
    private Parser parser;
    private Ui ui;

    public static void main(String[] args) {
        String filePath = "data" + File.separatorChar + "duke-storage.txt";
        new Duke(filePath).run();
    }

    public Duke(String filePathToStorage) {
        this.dukeStore = new Storage(filePathToStorage);
        this.taskList = TaskList.of(this.dukeStore);
        this.parser = Parser.initialize(taskList);
        this.ui = new Ui();
    }

    public void run() {
        while (ui.isRunning()) {
            if (!ui.isPendingReply()) {
                continue;
            }

            String userInput = ui.readCommand();
            if (userInput.equals("bye")) {
                ui.close();
            }

            try {
                String reply = this.parser.parseCommand(userInput);
                Ui.printFormatted(reply);
            } catch (DukeException e) {
                Ui.printFormatted(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

//    public static String beginScript() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        return ("Hello from\n" + logo);
//    }
//
//    public static String endScript() {
//        String exitStatment = "Bye, hope to see you again! :)";
//        return exitStatment;
//    }
//    public static void printFormatted(String text) {
//        String indent = "    ";
//        String topBorder    = "____________________________________\n";
//        String bottomBorder = "------------------------------------\n";
//        String textWithBorders = topBorder +  text + "\n" + bottomBorder;
//        String[] lines = textWithBorders.split("\n");
//        for (String line : lines) {
//            System.out.println(indent + line);
//        }
//    }

//    public static String defaultReplyToInvalidInput() {
//        return "Invalid input o(>~<)O!\n" +
//                "Checkout the available commands by typing them in the cmdline!\n"+
//                "Available commands: [todo, deadline, event]";
//    }
}

