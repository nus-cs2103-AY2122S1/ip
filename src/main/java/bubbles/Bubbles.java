package bubbles;

class Bubbles {
    private String FILEPATH = "data/bubbles.txt";
    private Storage storage;
    private Ui ui;

    public Bubbles() {
        ui = new Ui();
        storage = new Storage();
        storage.loadFile(FILEPATH);
    }

    public static void main(String[] args) {
        new Bubbles().run();
    }

    private void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String introduction = "You should do what you want to do!\n"
                + "Hello I'm Bubbles from the Powerpuff Girls, what are you up to?";
        System.out.println("Hello from\n" + logo);
        formatting(introduction);

        ui.echoTask(this.storage);
    }

    private static void formatting(String str) {
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator + "\n"
                + str + "\n"
                + separator);
    }
}
