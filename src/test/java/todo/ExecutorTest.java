package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class ExecutorTest {

    @Test
    void should_return_execution_result_when_add_todo_item_given_item_was_added_successfully() {
        String command = "todo add gaming";

        Executor executor = new Executor();
        List<String> result = executor.execute(command);

        assertEquals(2, result.size());
        assertEquals("1. gaming", result.get(0));
        assertEquals("Item 1 added", result.get(1));
    }

}