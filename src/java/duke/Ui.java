package duke;

public class Ui {
    
    public static String LINES = "---------------------------------------------------------------------\n";
    
    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String introText = "What can I do for you?\n";
        System.out.println("Hello from\n" + logo + Ui.LINES + introText);
        allCommands();
    }
    
    public static void allCommands() {
        System.out.println("These are the list of available commands:\n" + 
                "todo [description]\t- Make a todo task\n" + 
                "deadline [description] /at YYYY-MM-DD xxxx\t- Make a new deadline event\n" + 
                "event [description] /at YYYY-MM-DD xxxx\t- Make a event\n" + 
                Ui.LINES + 
                "list\t- List out your events\n" + 
                "done {index}\t- Complete the task at mentioned index\n" + 
                "delete {index}\t- Complete the task at mentioned index\n" +
                "help\t- Show all available commands" +
                "bye\t- Quit Duke."
                );
    }
}
