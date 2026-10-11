import java.util.Arrays;
import java.util.List;


public record student(int id, String name, department department, int[] marks) {

    @Override
    public String toString() {
        return id + ", " + name + " ," + department + ", " + Arrays.toString(marks);
    }
}
