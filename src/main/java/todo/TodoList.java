package todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private static Integer TODO_ITEM_ID = 1;
    private static final boolean INITIAL_VALUE_FOR_IS_DONE_FIELD = false;

    private final List<TodoItem> todoItems;

    public TodoList() {
        todoItems = new ArrayList<>();
    }

    public List<TodoItem> listAll() {
        return todoItems;
    }

    public TodoItem add(String content) {
        TodoItem todoItem = TodoItem.builder()
                .id(TODO_ITEM_ID)
                .content(content)
                .isDone(INITIAL_VALUE_FOR_IS_DONE_FIELD).build();
        todoItems.add(todoItem);

        TODO_ITEM_ID++;

        return todoItem;
    }
}
