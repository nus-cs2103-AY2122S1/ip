public class NoToDoDescriptionException extends DukeException{
    public NoToDoDescriptionException(){
        super();
    }

    @Override
    public String getMessage(){
        return super.formatMessage("Hello??? What you want me to do???");
    }
}
