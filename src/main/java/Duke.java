public class Duke {
    final Listener listener;

    private Duke() {
        listener = new Listener();
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
