public class UnknownCommandException extends DukeException{
    public UnknownCommandException(){
        super();
    }

    @Override
    public String getMessage(){
        return super.formatMessage("I cannot understand what you want lah!!!");
    }
}
