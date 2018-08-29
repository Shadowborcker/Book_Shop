import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputReader {

    // Метод запроса строки
    public String askInput(String s) {
        String string;
        while (true) {
            System.out.println(s);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                string = reader.readLine();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return string;
    }

}
