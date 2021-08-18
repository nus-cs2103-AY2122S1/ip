package lifeline;

public class Main {
    public static void main(String[] args) {
        Lifeline lifeline = new Lifeline("./save/tasks.json");
        lifeline.start();
    }

}
