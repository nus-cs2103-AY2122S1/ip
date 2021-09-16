package genie.ui;

public class Ui {
    
    private static String LINES = "--------------------------------------------------------------------------------------\n";
    
    public static void intro() {
        String VICTORIA =
                "__        ___  _    _____   _______   _______   ______   _       _           #     #         \n" +        
                "\\ \\      /  / | |  |  ___| |__   __| |  ___  | |  __  | | |     /  \\      #     #     #         \n" +
                " \\ \\    /  /  | |  | |        | |    | |   | | | |__| | | |    / /_ \\    #             #      \n" + 
                "  \\ \\  /  /   | |  | |        | |    | |   | | |  ____| | |   /  __  \\     #         #        \n" + 
                "   \\ \\/  /    | |  | |___     | |    | |___| | | | \\ \\  | |  /  /  \\  \\      #     #          \n" +
                "    \\___/     |_|  |_____|    |_|    |_______| |_|  \\_\\ |_| /__/    \\_ \\        #          \n" ;
        
        
        System.out.println("Hey darling, I'm\n" + VICTORIA + Ui.LINES);
        allCommands();
    }
    

    /**
     * A method that prints out all the available commands that the bot supports
     */
    public static void allCommands() {
        System.out.println("These are the services I can provide you:\n" + 
                "todo              [description]- Make a todo task\n" + 
                "deadline          [description] /at YYYY-MM-DD xxxx  - Make a new deadline event\n" + 
                "event             [description] /at YYYY-MM-DD xxxx  - Make a event\n" + 
                "list              List out your events\n" + 
                "done {index}      Complete the task at mentioned index\n" + 
                "delete {index}    Complete the task at mentioned index\n" +
                "help              Show all available commands\n" +
                "bye               Quit Duke.\n" + 
                Ui.LINES +
                "So, how can I please you today?\n" + 
                "Tell me : \n"
        );
    }
}
