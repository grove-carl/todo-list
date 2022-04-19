package todo;

import java.util.List;

public class Executor {

    private static final String TODO_ADD_COMMAND_KEYWORD = "todo add";
    private final TodoList todoList;

    public Executor() {
        todoList = new TodoList();
    }

    public List<String> execute(String command) {
        String todoContent = extractTodoContentFromAddCommand(command);
        TodoItem insertedTodoItem = todoList.add(todoContent);
        return constructResultInfo(insertedTodoItem);
    }

    private String extractTodoContentFromAddCommand(String command) {
        return command.substring(TODO_ADD_COMMAND_KEYWORD.length()).trim();
    }

    private List<String> constructResultInfo(TodoItem todoItem) {
        String insertedContentLine = String.format("%d. %s", todoItem.getId(), todoItem.getContent());
        String insertedResultLine = String.format("Item %d added", todoItem.getId());
        return List.of(insertedContentLine, insertedResultLine);
    }
}
