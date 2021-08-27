import java.util.ArrayList;

public class ListOfTasks {
    private static int count;
    private ArrayList<Task> xs;

    public ListOfTasks() {
        //this.xs = new Task[100];
        this.xs = new ArrayList<>();
        this.count = 0;
    }

    public void add(String information) {
            System.out.println("     Got it. I've added this task:");
            information = removeVal(information, "todo");
//            xs[count] = new ToDo(information);
            xs.add(count, new ToDo(information, "TODO"));
            CompilationOfFiles.updateSavedFile(this.xs.get(count), "TODO");
            System.out.println("       " + this.xs.get(count).toString());
            count++;
            System.out.println("     Now you have " + count + " task" + ((count > 1) ? "s" : "") + " in the list.");
    }

    public void listOut() {
        System.out.println("     Here are the tasks in your list:");
        int a = 0;
        while (a < count) {
            System.out.println("     " + ( a + 1 ) + ". " + this.xs.get(a).toString() );
            a = a + 1;
        }
    }

    public void isDone(String command) {
        command = removeVal(command, "done");
        int a;
        a = Integer.parseInt(command);
        a = a - 1;

        if (a < count && a >= 0) {
            this.xs.get(a).isDone();
            CompilationOfFiles.updateFile(this.xs);
            System.out.println("     Nice! I've marked this task as done!");
            System.out.println("     " + this.xs.get(a).toString());

        } else {
            System.out.println("     Invalid task number. Please try again.");
        }
    }
    public void delete(String command) {
        command = removeVal(command, "delete");
        int a;
        a = Integer.parseInt(command);
        a = a - 1;

        if (a < count && a >= 0) {
            Task deletedVal = this.xs.remove(a);
            CompilationOfFiles.updateFile(this.xs);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + deletedVal.toString());
            count--;
            System.out.println("     Now you have " + count + " task" + ((count > 1) ? "s" : "") + " in the list.");

        } else {
            System.out.println("     Invalid task number. Please try again.");
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

            System.out.println("    Invalid Input. Please try again.");
            return;
        }
        System.out.println("     Got it. I've added this task:");

        information = removeVal(information, "event");
        String[] moreInformation = information.split("/at", 2);

        this.xs.add(count,new Event(moreInformation[0],moreInformation[1].strip(),"EVENT") );
        CompilationOfFiles.updateSavedFile(this.xs.get(count),"EVENT");
        System.out.println("       " + this.xs.get(count).toString());
        count = count + 1;
        System.out.println("     Now you have "+ count + " task" + ((count > 1) ? "s" : "" ) +" in the list.");
    }

    public void addDeadline(String information) {
        if (!information.contains("/by")) {

            System.out.println("    Invalid Input. Please try again.");
            return;
        }
        System.out.println("     Got it. I've added this task:");

        information = removeVal(information, "deadline");
        String[] moreInformation2 = information.split("/by", 2);

        this.xs.add(count,new Deadline(moreInformation2[0],moreInformation2[1].strip(), "DEADLINE") );
        CompilationOfFiles.updateSavedFile(this.xs.get(count), "DEADLINE");
        System.out.println("       " + this.xs.get(count).toString());
        count = count + 1;
        System.out.println("     Now you have "+ count + " task" + ((count > 1) ? "s" : "" ) +" in the list.");
    }

}




