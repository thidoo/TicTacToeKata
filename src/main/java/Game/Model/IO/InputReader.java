package Game.Model.IO;

import java.util.Scanner;

public class InputReader {

    private Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public String readFromConsole(){
        return scanner.nextLine();
    }
}
