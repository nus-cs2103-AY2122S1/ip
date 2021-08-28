public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello from Duke!");
        System.out.println("");
        User user1 = new User();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./duke.txt");
        storage.loadTaskListData(taskList);
        System.out.println("");
        System.out.println("Hope you are doing well. How can I help you?");
        boolean isExit = false;
        while (!isExit) {
            String command = user1.command();
            Parser parser = new Parser(command);
            if (parser.isExit()) {
                break;
            }
            Command c = parser.parse(command);
            c.execute(taskList, storage);
            isExit = parser.isExit();
        }
    }
}