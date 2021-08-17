public class Task {
    Boolean done;
    String description;
    public Task(String str) {
        done = false;
        description = str;
    }

    public void markDone() {
        done = true;
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" +this.toString());
    }

    @Override
    public String toString() {
        return "["+ (done?'X':' ') + "]" + description;

    }
}
