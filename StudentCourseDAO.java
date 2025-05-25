import java.io.*;
import java.util.HashMap;
import java.util.List;

public class StudentCourseDAO {
    private static final String DATA_FILE = "students.dat";

    // Save data to file
    public void save(HashMap<String, List<String>> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(data);
        }
    }

    // Load data from file
    @SuppressWarnings("unchecked")
    public HashMap<String, List<String>> load() throws IOException, ClassNotFoundException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (HashMap<String, List<String>>) ois.readObject();
        }
    }
}
