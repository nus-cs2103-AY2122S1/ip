import java.time.LocalDate;

public class Event extends Task{
    private String name;
    private boolean isDone;
    private String time;

    public Event(String name, boolean isDone, String time) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.time = time;
    }

    public String getTime() {
        String[] s = this.time.split(" ");

        LocalDate date = LocalDate.parse(s[1]);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s[0] + " ");
        stringBuilder.append(date.getMonth() + " ");
        stringBuilder.append(date.getDayOfMonth()  + " ");
        stringBuilder.append(date.getYear()  + " ");
        for (int i = 2; i < s.length; i++) {
            stringBuilder.append(s[i] + " ");
        }

        return stringBuilder.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }


    @Override
    public String toString() {
        return "E" + " " + name    +" "  +"/"+ time + " "+  isDone+ '\n';
    }
}