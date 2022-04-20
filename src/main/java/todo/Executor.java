package todo;

import java.util.List;

public class Executor {

    private static final String TODO_ADD_COMMAND_KEYWORD = "todo add";
    private static final String TODO_DONE_COMMAND_KEYWORD = "todo done";
    private final TodoList todoList;

    public Executor() {
        todoList = new TodoList();
    }

    public List<String> execute(String command) {
        if (isAddCommand(command)) {
            String todoContent = extractTodoContentFromAddCommand(command);
            TodoItem insertedTodoItem = todoList.add(todoContent);
            return constructAddResultInfo(insertedTodoItem);
        } else if (isDoneCommand(command)) {
            int doneItemId = extractDoneItemIdFromDoneCommand(command);
            TodoItem doneTodoItem = todoList.done(doneItemId);
            return constructDoneResultInfo(doneTodoItem);
        }
        return null;
    }

    private boolean isAddCommand(String command) {
        return command.startsWith(TODO_ADD_COMMAND_KEYWORD);
    }

    private boolean isDoneCommand(String command) {
        return command.startsWith(TODO_DONE_COMMAND_KEYWORD);
    }

    private String extractTodoContentFromAddCommand(String command) {
        return command.substring(TODO_ADD_COMMAND_KEYWORD.length()).trim();
    }

    private int extractDoneItemIdFromDoneCommand(String command) {
        return Integer.parseInt(command.substring(TODO_DONE_COMMAND_KEYWORD.length()).trim());
    }

    private List<String> constructAddResultInfo(TodoItem todoItem) {
        String insertedContentLine = String.format("%d. %s", todoItem.getId(), todoItem.getContent());
        String resultLine = String.format("Item %d added", todoItem.getId());
        return List.of(insertedContentLine, resultLine);
    }

    private List<String> constructDoneResultInfo(TodoItem todoItem) {
        String resultLine = String.format("Item %d done", todoItem.getId());
        return List.of(resultLine);
    }
}
