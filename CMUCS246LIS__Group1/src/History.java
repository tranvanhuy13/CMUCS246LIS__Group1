import java.util.ArrayList;

public class History {
    private ArrayList<String> records;

    public History() {
        records = new ArrayList<>();
    }

    public void add(String record) {
        records.add(record);
    }

    public String getAll() {
        if (records.isEmpty()) {
            return "No history yet.";
        }

        StringBuilder sb = new StringBuilder();
        for (String r : records) {
            sb.append(r).append("\n");
        }
        return sb.toString();
    }
}