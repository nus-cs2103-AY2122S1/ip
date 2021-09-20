package duke;

public class GUI {

    private TaskList taskList;

    public static void main(String[] args) {
        Launcher.main(args);
    }

    public GUI() {
        DataFile dataFile = new DataFile("./duke_data.txt");
        taskList = new TaskList(dataFile);
    }

    public String getResponse(String s) {
        return processInput(s);
    }

    private String processInput(String str) {
        assert !str.equals("");
        Task newTask = Parser.parseInput(str);
        if (newTask == null) {
            if (str.equals("bye")) {
                taskList.save();
                return "Tasklist saved; Click the exit button to exit instead!";

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