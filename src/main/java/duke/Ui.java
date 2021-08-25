package duke;

public class Ui {
    private String name;
    public Ui(String name) {
        this.name = name;
    }

    public void greeting() {
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Hello... I'm " + this.name + ":/");
        System.out.println("And how can I help you?");
        System.out.println("========== " + this.name + " ===========\n");
    }

    public void prettyPrinter(String message) {
        System.out.println("========== " + this.name + " ===========");
        System.out.println(message);
        System.out.println("========== " + this.name + " ===========\n");
    }

}
