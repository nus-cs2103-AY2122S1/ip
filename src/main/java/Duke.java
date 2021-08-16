public class Duke {

    private UI ui;
    private boolean exit;
    private ToDoList toDoList;

    public Duke () {
        this.ui = new UI ();
        this.toDoList = new ToDoList();
        exit = false;
    }

    public void start () {
        System.out.println(ui.greet());

        while (!exit) {
            String userCommand = ui.echoCommand();

            if (userCommand.equals("bye")) {
                System.out.println(ui.exit());
                exit = true;
            } else if (userCommand.equals("list")) {
                System.out.println(ui.retrieveList());
            } else {
                toDoList.add(userCommand);
                System.out.println(ui.addedTask(userCommand));
            }
        }
    }


    public static void main(String[] args) {
        new Duke().start();
    }
}
