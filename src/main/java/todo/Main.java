package todo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import todo.repository.TodoListMemoryRepository;
import todo.repository.TodoListRepository;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner scanner = new Scanner(System.in);
        TodoListRepository todoListRepository = new TodoListMemoryRepository(new LinkedHashMap<>());
        TodoList todoList = new TodoList(todoListRepository);
        Executor executor = new Executor(todoList);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            List<String> results = executor.execute(command);
            results.forEach(System.out::println);
        }
    }
}
