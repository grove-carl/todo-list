package todo;

import java.util.List;

public class Executor {

    private final TodoList todoList;

    public Executor() {
        todoList = new TodoList();
    }

    public List<String> execute(String command) {
        String todoContent = extractTodoContentFromCommand(command);

        TodoItem insertedTodoItem = todoList.add(todoContent);

        return constructResultInfo(insertedTodoItem);
    }

    private String extractTodoContentFromCommand(String command) {
        return command.substring("todo add".length()).trim();
    }

    private List<String> constructResultInfo(TodoItem todoItem) {
        String line1 = todoItem.getId() + ". " + todoItem.getContent();
        String line2 = "Item " + todoItem.getId() + " added";
        return List.of(line1, line2);
    }
}
