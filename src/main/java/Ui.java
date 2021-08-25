public class Ui {
    public Ui () {
        
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
    
//    public void readCommand() {
//
//    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        String separator = "------------------------------------------------------------------";
        System.out.println(separator);
    }

//    public void () {
//
//    }
}
