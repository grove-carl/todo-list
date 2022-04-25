package todo.repository;

import java.util.List;
import java.util.Optional;
import todo.TodoItem;

public interface TodoListRepository {

    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();

    Optional<TodoItem> findById(Integer id);
}
