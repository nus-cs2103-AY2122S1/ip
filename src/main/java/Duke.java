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
            } else if (userCommand.startsWith("done")) {
                char taskIndex = userCommand.charAt(5);
                int index = Integer.parseInt(String.valueOf(taskIndex));
                Task taskAtIndex = toDoList.getTask(index);
                taskAtIndex.markAsDone();
                System.out.println(ui.doneTask(taskAtIndex));
            }
            else {
                Task task = new Task(userCommand);
                toDoList.add(task);
                System.out.println(ui.addedTask(task.description));
            }
        }
    }


    public static void main(String[] args) {
        new Duke().start();
    }
}
