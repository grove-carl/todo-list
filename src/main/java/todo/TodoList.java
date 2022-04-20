package todo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TodoList {

    private Integer TODO_ITEM_ID = 1;
    private static final boolean INITIAL_VALUE_FOR_IS_DONE_FIELD = false;

    private final Map<Integer, TodoItem> todoItems;

    public TodoList() {
        todoItems = new LinkedHashMap<>();
    }

    public List<TodoItem> listAll() {
        return new ArrayList<>(todoItems.values());
    }

    public List<TodoItem> listAllUnfinished() {
        return todoItems.values().stream().filter(todoItem -> !todoItem.isDone()).collect(Collectors.toList());
    }

    public TodoItem add(String content) {
        TodoItem todoItem = TodoItem.builder()
                .id(TODO_ITEM_ID)
                .content(content)
                .isDone(INITIAL_VALUE_FOR_IS_DONE_FIELD).build();
        todoItems.put(TODO_ITEM_ID, todoItem);

        TODO_ITEM_ID++;

        return todoItem;
    }

    public TodoItem done(int id) {
        TodoItem todoItem = todoItems.get(id);
        todoItem.done();
        return todoItem;
    }
}
