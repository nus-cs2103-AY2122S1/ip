public class ListOfTasks {
    private static int count;
    private Task[] xs;

    public ListOfTasks() {
        this.xs = new Task[100];
        this.count = 0;
    }

    public void add(String information) {
        this.xs[count] = new Task(information);
        this.count = this.count + 1;
        System.out.println("     added: " + information);
    }

    public void listOut() {
        int a = 0;
        while (a < count) {
            System.out.println("     " + ( a + 1 ) + ". [" + this.xs[a].getStatusIcon() + "] "+ this.xs[a].information );
            a = a + 1;
        }
    }

    public void done(String command) {

        command = command.replace("done", "");
        command = command.strip();
        int a;

        a = Integer.parseInt(command);
        a = a - 1;

        if (a < count && a >= 0) {
            this.xs[a].done();

            System.out.println("     Nice! I've marked this task as done!");
            System.out.println("     " + " [" + this.xs[a].getStatusIcon() + "] " + this.xs[a].information);

        } else {
            System.out.println("     Invalid task number. Please try again.");
        }

    }

}
