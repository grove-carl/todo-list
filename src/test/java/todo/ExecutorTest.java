package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExecutorTest {

    private Executor executor;

    @BeforeEach
    void setUp() {
        executor = new Executor();
    }

    @Test
    void should_return_execution_result_when_add_todo_item_given_item_was_added_successfully() {
        String command = "todo add gaming";

        List<String> result = executor.execute(command);

        assertEquals(2, result.size());
        assertEquals("1. gaming", result.get(0));
        assertEquals("Item 1 added", result.get(1));
    }

    @Test
    void should_return_execution_result_when_mark_item_as_done_given_item_was_mark_as_done_successfully() {
        executor.execute("todo add swimming");
        executor.execute("todo add gaming");

        String command = "todo done 1";
        List<String> result = executor.execute(command);

        assertEquals(1, result.size());
        assertEquals("Item 1 done", result.get(0));
    }

}