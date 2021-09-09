package duke;

public class GUI {

    private DataFile dataFile;
    private TaskList taskList;

    private final String WELCOME_MESSAGE = " ____            _      \n"
            + "|  _ \\ _   _  __| | ___ \n"
            + "| | | | | | |/ _` |/ _ \\\n"
            + "| |_| | |_| | (_| |  __/\n"
            + "|____/ \\__,_|\\__,_|\\___|\n";

    public static void main(String[] args) {
        Launcher.main(args);
    }

    public GUI() {
        dataFile = new DataFile("./duke_data.txt");
        taskList = new TaskList(dataFile);
    }

    public String getResponse(String s) {

        return processInput(s);
    }

    String processInput(String str) {

        Task newTask = Parser.parseInput(str);

        if (newTask == null) {
            if (str.equals("bye")) {
                taskList.save();
                return "Bye. Hope to see you again soon!";

            } else if (str.equals("list")) {
                return taskList.list();
            } else if (str.startsWith("done")) {
                String substr = str.replaceFirst("done ", "");
                int index = Integer.parseInt(substr);
                return taskList.markDone(index);
            } else if (str.startsWith("delete")) {
                String substr = str.replaceFirst("delete ", "");
                int index = Integer.parseInt(substr);
                return taskList.delete(index);
            } else if (str.startsWith("find")) {
                String substr = str.replaceFirst("find ", "");
                return taskList.findTask(substr);
            } else {
                return "??? Unknown command!";
            }
        } else {
            return taskList.add(newTask);
        }

    }



    
}