package todo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import todo.TodoItem;

@RequiredArgsConstructor
public class TodoListMemoryRepository implements TodoListRepository {

    private final Map<Integer, TodoItem> todoItems;

    @Override
    public TodoItem save(final TodoItem todoItem) {
        todoItems.put(todoItem.getId(), todoItem);
        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {
        return new ArrayList<>(todoItems.values());
    }

    @Override
    public Optional<TodoItem> findById(final Integer id) {
        return Optional.ofNullable(todoItems.get(id));
    }
}
