import java.util.ArrayList;

public class ListOfTasks {
    private static int count;
    private ArrayList<Task> xs;
    private Ui ui;

    public ListOfTasks() {
        //this.xs = new Task[100];
        ui = new Ui();
        this.xs = new ArrayList<>();
        this.count = 0;
    }

    public ArrayList<Task> getList() {
        return xs;
    }

    public void includeAdditionalTask(Task x) {
        xs.add(this.count,x);
        count++;
    }

    public void addTask(String information) {
        ui.addTaskMessage();
        information = removeVal(information, "todo");
        xs.add(count, new ToDo(information, "TODO"));
        CompilationOfFiles.updateSavedFile(this.xs.get(count), "TODO");
        ui.printCurrentTask(xs.get(count));
        count++;
        ui.printNumberOfTasks(count);
    }

    public void listOut() {
        ui.listTaskMessage();
        int a = 0;
        while (a < count) {
            ui.listEachTask(xs,a);
            a = a + 1;
        }
    }

    public void isDone(String command) {
        try {
            command = removeVal(command, "done");
            int a;
            a = Integer.parseInt(command);
            a = a - 1;
            if (a < count && a >= 0) {
                this.xs.get(a).isDone();
                CompilationOfFiles.updateFile(this.xs);
                ui.printDoneMessage();
                ui.printCurrentTask(this.xs.get(a));
            } else {
                ui.printInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.printInvalidTaskNumber();
        }
    }
    public void delete(String command) {
        try {
            command = removeVal(command, "delete");
            int a;
            a = Integer.parseInt(command);
            a = a - 1;

            if (a < count && a >= 0) {
                Task deletedVal = this.xs.remove(a);
                CompilationOfFiles.updateFile(this.xs);
                ui.printDeletedMessage();
                System.out.println("       " + deletedVal.toString());
                count--;
                ui.printNumberOfTasks(count);
            } else {
                ui.printInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.printInvalidTaskNumber();
        }
    }
    private String removeVal(String val, String command) {
        int len = command.length();
        val = val.strip();
        val = val.substring(len);
        val = val.strip();
        return val;
    }

    public void addEvent(String information) {

        if (!information.contains("/at")) {
            ui.printInvalidInput();
            return;
        }
        ui.addTaskMessage();

        information = removeVal(information, "event");
        String[] moreInformation = information.split("/at", 2);

        this.xs.add(count,new Event(moreInformation[0],moreInformation[1].strip(),"EVENT") );
        CompilationOfFiles.updateSavedFile(this.xs.get(count),"EVENT");
        ui.printCurrentTask(this.xs.get(count));
        count = count + 1;
        ui.printNumberOfTasks(count);
    }

    public void addDeadline(String information) {

        if (!information.contains("/by")) {
            ui.printInvalidInput();
            return;
        }

        information = removeVal(information, "deadline");
        String[] moreInformation2 = information.split("/by", 2);

        this.xs.add(count,new Deadline(moreInformation2[0],moreInformation2[1].strip(), "DEADLINE") );
        CompilationOfFiles.updateSavedFile(this.xs.get(count), "DEADLINE");
        System.out.println("     Got it. I've added this task:");
        ui.printCurrentTask(this.xs.get(count));
        count = count + 1;
        ui.printNumberOfTasks(count);
    }

}




