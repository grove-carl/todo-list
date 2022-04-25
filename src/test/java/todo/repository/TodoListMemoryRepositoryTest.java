package todo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import todo.TodoItem;

class TodoListMemoryRepositoryTest {

    @Test
    void should_save_and_return_saved_todo_item() {
        TodoItem todoItem = TodoItem.builder()
                .id(1).content("gaming").isDone(false).build();

        Map<Integer, TodoItem> todoItems = new HashMap<>();
        TodoListRepository repository = new TodoListMemoryRepository(todoItems);
        TodoItem savedTodoItem = repository.save(todoItem);

        assertEquals(todoItem, savedTodoItem);
        assertEquals(todoItem, todoItems.get(todoItem.getId()));
    }

    @Test
    void should_return_all_todo_items() {
        Map<Integer, TodoItem> todoItems = new LinkedHashMap<>();
        TodoItem todoItem1 = TodoItem.builder().id(1).content("swimming").isDone(false).build();
        todoItems.put(todoItem1.getId(), todoItem1);
        TodoItem todoItem2 = TodoItem.builder().id(2).content("programming").isDone(true).build();
        todoItems.put(todoItem2.getId(), todoItem2);
        TodoItem todoItem3 = TodoItem.builder().id(3).content("gaming").isDone(false).build();
        todoItems.put(todoItem3.getId(), todoItem3);

        TodoListRepository repository = new TodoListMemoryRepository(todoItems);
        List<TodoItem> results = repository.findAll();

        assertTrue(results.containsAll(
                List.of(todoItem1, todoItem2, todoItem3)
        ));
    }

    @Test
    void should_return_todo_item_by_given_id() {
        Map<Integer, TodoItem> todoItems = new LinkedHashMap<>();
        TodoItem todoItem1 = TodoItem.builder().id(1).content("swimming").isDone(false).build();
        todoItems.put(todoItem1.getId(), todoItem1);
        TodoItem todoItem2 = TodoItem.builder().id(2).content("programming").isDone(true).build();
        todoItems.put(todoItem2.getId(), todoItem2);
        TodoItem todoItem3 = TodoItem.builder().id(3).content("gaming").isDone(false).build();
        todoItems.put(todoItem3.getId(), todoItem3);

        TodoListRepository repository = new TodoListMemoryRepository(todoItems);
        Optional<TodoItem> result = repository.findById(2);

        assertTrue(result.isPresent());
        assertEquals(todoItem2, result.get());
    }
}