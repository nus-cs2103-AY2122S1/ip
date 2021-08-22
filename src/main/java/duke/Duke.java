package duke;

import command.Command;

public class Duke {

    private final TaskList taskList;
    private final Storage store;
    private final Ui ui;
    private boolean isRunning = true;

    public Duke(){
        store = new Storage("./data/duke.txt");
        taskList = new TaskList();
        this.store.retrieveTasks(taskList);
        this.ui = new Ui();
        System.out.println("Hello! I'm Duke.Duke\n" + "What can I do for you?");
    }

    private void run(){
        while(isRunning){
            String input = ui.getInput();
            try {
                Command cmd = Command.createCommand(input);
                cmd.execute(this.taskList, this.ui, this.store, this);
            }catch (DukeException e){
                System.out.println(e);
            }
        }
    }

    public void close(){
        isRunning = false;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

}
