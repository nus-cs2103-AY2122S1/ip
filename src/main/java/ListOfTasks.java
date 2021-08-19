public class ListOfTasks {
    private static int count;
    private Task[] xs;

    public ListOfTasks() {
        this.xs = new Task[100];
        this.count = 0;
    }

    public void add(String information) {
        System.out.println("     Got it. I've added this task:");
        information = remove(information, "todo");
        xs[count] = new ToDo(information);
        System.out.println("       " + xs[count].toString());
        count++;
        System.out.println("     Now you have "+ count + " task" + ((count > 1) ? "s" : "" ) +" in the list.");
    }

    public void listOut() {
        System.out.println("     Here are the tasks in your list:");
        int a = 0;
        while (a < count) {
            System.out.println("     " + ( a + 1 ) + ". " + this.xs[a].toString() );
            a = a + 1;
        }
    }

    public void done(String command) {
        command = remove(command, "done");
        int a;
        a = Integer.parseInt(command);
        a = a - 1;

        if (a < count && a >= 0) {
            this.xs[a].done();
            System.out.println("     Nice! I've marked this task as done!");
            System.out.println("     " + this.xs[a].toString());

        } else {
            System.out.println("     Invalid task number. Please try again.");
        }
    }

    private String remove(String val, String command) {
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

        information = remove(information, "event");
        String[] moreInformation = information.split("/at", 2);

        xs[count] = new Event(moreInformation[0],moreInformation[1].strip());
        System.out.println("       " + xs[count].toString());
        count = count + 1;
        System.out.println("     Now you have "+ count + " task" + ((count > 1) ? "s" : "" ) +" in the list.");
    }

    public void addDeadline(String information) {
        if (!information.contains("/by")) {

            System.out.println("    Invalid Input. Please try again.");
            return;
        }
        System.out.println("     Got it. I've added this task:");

        information = remove(information, "deadline");
        String[] moreInformation2 = information.split("/by", 2);

        xs[count] = new Deadline(moreInformation2[0],moreInformation2[1].strip());
        System.out.println("       " + xs[count].toString());
        count = count + 1;
        System.out.println("     Now you have "+ count + " task" + ((count > 1) ? "s" : "" ) +" in the list.");
    }

}




