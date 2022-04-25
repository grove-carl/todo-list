package todo;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository {

    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();

    Optional<TodoItem> findById(Integer id);
}
