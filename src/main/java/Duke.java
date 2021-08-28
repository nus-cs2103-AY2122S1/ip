public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello from Duke!");
        System.out.println("");
        User user1 = new User();
        Storage storage = new Storage("./duke.txt");
        storage.loadTaskListData();
        System.out.println("");
        System.out.println("Hope you are doing well. How can I help you?");
        TaskList taskList = new TaskList();
        boolean isExit = false;
        while (!isExit) {
            String command = user1.command();
            Parser parser = new Parser(command);
            Command c = parser.parse(command);
            c.execute(taskList, storage);
            isExit = parser.isExit();
        }
    }
}