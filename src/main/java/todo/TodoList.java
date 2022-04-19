package todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private final List<TodoItem> todoItems;

    public TodoList() {
        todoItems = new ArrayList<>();
    }

    public List<TodoItem> listAll() {
        return todoItems;
    }

    public void add(String content) {
        TodoItem todoItem = TodoItem.builder().content(content).build();
        todoItems.add(todoItem);
    }
}
