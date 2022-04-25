package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExecutorTest {

    private TodoList todoList;
    private Executor executor;

    @BeforeEach
    void setUp() {
        todoList = Mockito.mock(TodoList.class);
        executor = new Executor(todoList);
    }

    @Test
    void should_return_execution_result_when_add_todo_item_given_item_was_added_successfully() {
        when(todoList.add("gaming")).thenReturn(TodoItem.builder().id(1).content("gaming").isDone(false).build());

        String command = "todo add gaming";
        List<String> result = executor.execute(command);

        assertEquals(2, result.size());
        assertEquals("1. gaming", result.get(0));
        assertEquals("Item 1 added", result.get(1));
    }

    @Test
    void should_return_execution_result_when_mark_item_as_done_given_item_was_mark_as_done_successfully() {
        when(todoList.done(1)).thenReturn(TodoItem.builder().id(1).content("swimming").isDone(false).build());

        String command = "todo done 1";
        List<String> result = executor.execute(command);

        assertEquals(1, result.size());
        assertEquals("Item 1 done", result.get(0));
    }

    @Test
    void should_list_all_unfinished_todo_item_when_list_items_given_no_options_added() {
        when(todoList.listAllUnfinished()).thenReturn(List.of(
                TodoItem.builder().id(1).content("swimming").isDone(false).build(),
                TodoItem.builder().id(3).content("gaming").isDone(false).build()
        ));

        String command = "todo list";
        List<String> result = executor.execute(command);

        assertEquals(3, result.size());
        assertEquals("1. swimming", result.get(0));
        assertEquals("3. gaming", result.get(1));
        assertEquals("Total: 2 items", result.get(2));
    }

    @Test
    void should_list_all_todo_item_when_list_items_given_all_option_is_added() {
        when(todoList.listAll()).thenReturn(List.of(
                TodoItem.builder().id(1).content("swimming").isDone(false).build(),
                TodoItem.builder().id(2).content("programming").isDone(true).build(),
                TodoItem.builder().id(3).content("gaming").isDone(false).build()
        ));

        String command = "todo list --all";
        List<String> result = executor.execute(command);

        assertEquals(4, result.size());
        assertEquals("1. swimming", result.get(0));
        assertEquals("2. [Done] programming", result.get(1));
        assertEquals("3. gaming", result.get(2));
        assertEquals("Total: 3 items, 1 items done", result.get(3));
    }
}