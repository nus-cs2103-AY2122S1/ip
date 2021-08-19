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
            System.out.println("     " + ( a + 1 ) + ". " + this.xs[a].information );
            a = a + 1;

        }
    }
}
