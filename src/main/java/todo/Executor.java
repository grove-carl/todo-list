package todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    private static final String TODO_ADD_COMMAND_KEYWORD = "todo add";
    private static final String TODO_DONE_COMMAND_KEYWORD = "todo done";
    private static final String TODO_LIST_COMMAND_KEYWORD = "todo list";
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
        } else if (isListCommand(command)) {
            List<TodoItem> todoItems = todoList.listAllUnfinished();
            return constructListResultInfo(todoItems);
        }
        return null;
    }

    private boolean isAddCommand(String command) {
        return command.startsWith(TODO_ADD_COMMAND_KEYWORD);
    }

    private boolean isDoneCommand(String command) {
        return command.startsWith(TODO_DONE_COMMAND_KEYWORD);
    }

    private boolean isListCommand(String command) {
        return command.startsWith(TODO_LIST_COMMAND_KEYWORD);
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

    private List<String> constructListResultInfo(List<TodoItem> todoItems) {
        List<String> todoItemContentLines = todoItems.stream()
                .map(TodoItem::toString)
                .collect(Collectors.toList());
        String statisticsLine = String.format("Total: %d items", todoItemContentLines.size());

        List<String> result = new ArrayList<>(todoItemContentLines);
        result.add(statisticsLine);
        return result;
    }
}
