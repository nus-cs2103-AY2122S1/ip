import java.util.Scanner;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * This method checks if a String starting with done is indeed a done command.
     * Examples:
     * Case 1: done 3 => valid, standard form
     * Case 2: done         5 ==> valid, after trimmed
     * Case 3: done with my schoolwork ==> invalid, will generate an exception
     * **/
    private boolean isDoneCommand(String userInput) {
        String copy = userInput.replace("done", "");
        copy = copy.replaceAll("[0-9]", ""); //Learnt from https://attacomsian.com/blog/java-extract-digits-from-string
        copy = copy.trim();
        return copy.isEmpty();
    }

    private boolean isDeleteCommand(String userInput) {
        String copy = userInput.replace("delete", "");
        copy = copy.replaceAll("[0-9]", "");
        copy = copy.trim();
        return copy.isEmpty();
    }

    public void parse() {
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();
        while(!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                ui.printUserInputRecord(tasks.getStorage().getUserInputRecord());
            } else if(userInput.startsWith("done")){
                if(isDoneCommand(userInput)) {
                    tasks.markAsDone(userInput, tasks.getStorage().getUserInputRecord());
                } else {
                    ui.cannotInterpretMessage();
                }
            } else if(userInput.startsWith("delete")) {
                if (isDeleteCommand(userInput)) {
                    tasks.delete(userInput, tasks.getStorage().getUserInputRecord());
                } else {
                    ui.cannotInterpretMessage();
                }
            } else {
                tasks.add(userInput, tasks.getStorage().getUserInputRecord());
            }
            userInput = myScanner.nextLine();
        }
        myScanner.close();
    }

}
