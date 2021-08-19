public class Deadline extends Task {
    private String by;

    public static Task create(String userParams){
        String[] userParamsSplit = userParams.split(" /", 2);
        String description = userParamsSplit[0];
        String by = "";
        if(userParamsSplit[1].startsWith("by")) {
            by = userParamsSplit[1].replaceFirst("by", "");
        }
        return new Deadline(description, by);
    }

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
