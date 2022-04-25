package todo;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class TodoList {

    private Integer TODO_ITEM_ID = 1;
    private static final boolean INITIAL_VALUE_FOR_IS_DONE_FIELD = false;

    private final TodoListRepository repository;

    public List<TodoItem> listAll() {
        return repository.findAll();
    }

    public List<TodoItem> listAllUnfinished() {
        return repository.findAll().stream().filter(todoItem -> !todoItem.isDone()).collect(Collectors.toList());
    }

    public TodoItem add(String content) {
        TodoItem todoItem = TodoItem.builder()
                .id(TODO_ITEM_ID)
                .content(content)
                .isDone(INITIAL_VALUE_FOR_IS_DONE_FIELD).build();
        TODO_ITEM_ID++;
        return repository.save(todoItem);
    }

    public TodoItem done(int id) {
        TodoItem todoItem = repository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException();
        });
        todoItem.done();

        return repository.save(todoItem);
    }
}
