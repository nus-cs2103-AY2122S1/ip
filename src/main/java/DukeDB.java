import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DukeDB {
    private File file;

    public DukeDB() {
        try {
            file = new File("data/duke.txt");
            File parentFile = file.getParentFile();
            if(!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DukeDB(String filePath) {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readData() throws IOException{
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task s = parse(scanner.nextLine());
                tasks.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void writeData(Task task) throws IOException {
        try {
            FileWriter writer = new FileWriter(file, true);
            String string = task.writeToFile();
            writer.write(string);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int num) throws IOException{
        int count = 0;
        ArrayList<String> strings = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str=bufferedReader.readLine()) != null) {
                if(count == num) {
                    continue;
                }
                strings.add(str);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < strings.size(); i++) {
                writer.write(strings.get(i));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doneData(int num) {
        int count = 0;
        ArrayList<String> strings = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str=bufferedReader.readLine()) != null) {
                if(count == num) {
                    String[] target = str.split(" ");
                    target[2] = "1";
                    String result = Arrays.toString(target);
                    strings.add(result);
                    continue;
                }
                strings.add(str);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < strings.size(); i++) {
                writer.write(strings.get(i));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task parse(String string) {
        Task task;
        String[] str = string.split(" ");

        String s = str[0];
        String description = "";
        String time = "";

        Boolean done = false;
        if (str[2] == "1") {
            done = true;
        }

        switch (s) {
        case "T":
            int k = 4;
            do {
                description = description + str[k];
                if(k < str.length - 1) {
                    description += " ";
                }
                k++;
            } while (k < str.length);

            task = new Todo(description);
            if (done) {
                task.markAsDone();
            }
            break;
        case "D":
            int j = 4;
            description = str[j];
            j++;
            while (!str[j].equals("|")) {
                description = description + " " + str[j];
                j++;
            }

            j++;
            do {
                time = time + str[j];
                if (j < str.length - 1) {
                    time += " ";
                }
                j++;
            } while (j < str.length);

            task = new Deadline(description, time);
            if (done) {
                task.markAsDone();
            }
            break;
        case "E":
            int m = 4;

            description = str[m];
            m++;
            while (!str[m].equals("|")) {
                description = description + " " + str[m];
                m++;
            }

            m++;
            do {
                time = time + str[m];
                if (m < str.length - 1) {
                    time += " ";
                }
                m++;
            } while (m < str.length);

            task = new Event(description, time);
            if (done) {
                task.markAsDone();
            }
            break;
        default:
            task = null;
        }
        return task;
    }

    public static void main(String[] args) throws IOException {
        DukeDB data = new DukeDB();
        ArrayList<Task> lst = data.readData();
        for(int i = 0 ; i < lst.size(); i++) {
            System.out.println(lst);
        }
    }
}
