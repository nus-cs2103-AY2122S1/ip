public class IndexException extends BernException{
    public IndexException(String text) {
        super(text + ": This command is trying to mark/delete uncreated event");
    }
}
