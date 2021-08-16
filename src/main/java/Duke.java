public class Duke {
    final Listener listener;
    final TaskList taskList;

    private Duke() {
        taskList = new TaskList();
        listener = new Listener(taskList);
    }

    private void start(){
        Display.intro();
        listener.startListen();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
