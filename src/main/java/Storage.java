import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeTasks(List<Task> list) throws DukeException{
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task : list) {
                bw.write(task.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't write the tasks!");
        }
    }

    public void readTasks(List<Task> list) throws DukeException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating data file: " + e.getMessage());
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/duke.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(" ");

                switch(splitLine[0].charAt(1)) {
                case 'T':
                    String todoName = splitLine[1];
                    boolean todoStatus = (splitLine[0].charAt(4) == 'X');
                    list.add(new Todo(todoName, todoStatus));
                    break;
                case 'D':
                    String deadlineName = splitLine[1] + " ";
                    boolean deadlineStatus = (splitLine[0].charAt(4) == 'X');
                    String deadlineByWithBracket = line.substring(line.lastIndexOf("(by: ") + 5);
                    String deadlineBy = deadlineByWithBracket.substring(0, deadlineByWithBracket.length() - 1);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    LocalDate correctDeadline = LocalDate.parse(deadlineBy, dtf);
                    list.add(new Deadline(deadlineName, deadlineStatus, correctDeadline));
                    break;
                case 'E':
                    String eventName = splitLine[1] + " ";
                    boolean eventStatus = (splitLine[0].charAt(4) == 'X');
                    String eventAtWithBracket = line.substring(line.lastIndexOf("(at: ") + 5);
                    String eventAt = eventAtWithBracket.substring(0, eventAtWithBracket.length() - 1);
                    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMM yyy hh:mm a");
                    LocalDateTime correctEventAt = LocalDateTime.parse(eventAt, dtf2);
                    list.add(new Event(eventName, eventStatus, correctEventAt));
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't read the tasks!");
        }
    }

}
