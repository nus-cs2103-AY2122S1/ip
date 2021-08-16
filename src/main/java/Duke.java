public class Duke {

    private UI ui;
    private boolean exit;

    public Duke () {
        this.ui = new UI ();
        exit = false;
    }

    public void start () {
        System.out.println(ui.greet());

        while (!exit) {
            String userCommand = ui.echoCommand();

            if (userCommand.equals("bye")) {
                System.out.println(ui.exit());
                exit = true;
            } else {
                System.out.println(userCommand);
            }
        }
    }


    public static void main(String[] args) {
        new Duke().start();
    }
}
