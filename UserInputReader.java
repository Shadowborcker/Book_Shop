import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputReader {
    // Метод запроса строки
    public String askString() {
        String string;
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                string = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid entry");
            }
        }
        return string;
    }
    // Метод запроса числа
    public double askNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double number;
        while (true) {
            try {
                number = Double.parseDouble(reader.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Invalid entry");
            }
        }
        return number;
    }
}
