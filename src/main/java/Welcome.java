class Welcome extends Command {
    private String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    public static Welcome of() {
        return new Welcome();
    }

    public void exec() {
        System.out.println("Hello from\n" + LOGO);
    }

}
