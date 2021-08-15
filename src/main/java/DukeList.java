public class DukeList {
    private Task[] list = new Task[100];
    private int count = 0;

    public DukeList() {}


    public void add(String text) {
        list[count] = new Task(text);
        count += 1;
        System.out.println("added: " + text);
    }


    public void list() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + list[i].toString());
        }
    }


    public void done(int item) {
        Task task = list[item - 1];
        task.done();

        String response = "Nice! I've marked this task as done:\n";
        System.out.println(response + task.toString());
    }


}
