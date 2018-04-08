package Console;

import java.util.Scanner;

public class ConsoleReader {

    private Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public String readFromConsole(){
        return scanner.nextLine();
    }
}
