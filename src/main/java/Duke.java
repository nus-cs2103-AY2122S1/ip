//Solution below slightly adapted from https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java

public class Duke {

    private Messages ui;
    private boolean exit;
    private ToDoList toDoList;

    public Duke () {
        this.ui = new Messages ();
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
            } else if (userCommand.startsWith("todo")) {
                String task = userCommand.substring(5);
                Task toDoTask = new Todo(task);
                toDoList.add(toDoTask);
                System.out.println(ui.addedTask(toDoTask));
            } else if (userCommand.startsWith("deadline")) {
                int charIndex = userCommand.indexOf("/");
                int byIndex = charIndex + 4;
                String by = userCommand.substring(byIndex);
                String task = userCommand.substring(9, charIndex - 1);
                Task deadlineTask = new Deadline(task, by);
                toDoList.add(deadlineTask);
                System.out.println(ui.addedTask(deadlineTask));
            } else if (userCommand.startsWith("event")) {
                int charIndex = userCommand.indexOf("/");
                int atIndex = charIndex + 4;
                String at = userCommand.substring(atIndex);
                String task = userCommand.substring(6, charIndex - 1);
                Task eventTask = new Event(task, at);
                toDoList.add(eventTask);
                System.out.println(ui.addedTask(eventTask));
            }
            else {
                Task task = new Task(userCommand);
                toDoList.add(task);
                System.out.println(ui.addedTask(task));
            }
        }
    }


    public static void main(String[] args) {
        new Duke().start();
    }
}
