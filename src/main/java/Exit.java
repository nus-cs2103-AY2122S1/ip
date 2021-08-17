class Exit extends Command {
    public void exec() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static Exit of() {
        return new Exit();
    }
}
