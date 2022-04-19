package todo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            executor.execute(command);
        }
    }
}
