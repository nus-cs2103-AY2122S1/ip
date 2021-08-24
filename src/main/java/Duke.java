public class Duke {

    private Ui ui;
    private List list;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.list = new List();
        this.parser = new Parser(this.list);
    }

    public void run() {
        ui.welcome();
        parser.execute();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
