public class Ui {

    private String divider = ("\n_________________________\n");
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    protected void showLine(){
        System.out.println(divider);
    }

    protected void showWelcome(){
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("OOPS!!! The file was no loaded properly");
    }

    protected void showTask(TaskList taskList) {
        taskList.showTasks();
    }
}
