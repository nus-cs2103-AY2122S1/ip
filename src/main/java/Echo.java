class Echo extends Command {
    private String word;

    private Echo(String word) {
        this.word = word;
    }

    public static Echo of(String word) {
        return new Echo(word);
    }

    public void exec() {
        System.out.println(this.word);
    }
}
