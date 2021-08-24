package duke;

public class Ui {
    
    public static String LINES = "--------------------------------------------------------------------------------------\n";
    
    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        System.out.println("Hello from\n" + logo + Ui.LINES);
        allCommands();
    }
    
    public static void allCommands() {
        System.out.println("These are the list of available commands:\n" + 
                "todo              [description]- Make a todo task\n" + 
                "deadline          [description] /at YYYY-MM-DD xxxx  - Make a new deadline event\n" + 
                "event             [description] /at YYYY-MM-DD xxxx  - Make a event\n" + 
                "list              List out your events\n" + 
                "done {index}      Complete the task at mentioned index\n" + 
                "delete {index}    Complete the task at mentioned index\n" +
                "help              Show all available commands\n" +
                "bye               Quit Duke.\n" + 
                Ui.LINES +
                "What can I do for you?\n" + 
                "Enter a command: \n"
        );
    }
}
