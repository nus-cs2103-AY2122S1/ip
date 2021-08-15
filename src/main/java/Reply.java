public class Reply {
    private String content;
    private final String LINE = "\t____________________________________________________________";

    public Reply(String content) {
        this.content = content.replaceAll("(?m)^", "\t ");;
    }

    @Override
    public String toString() {
        return this.LINE + "\n" + this.content + "\n" + this.LINE;
    }
}
