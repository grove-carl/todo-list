package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoListTest {

    @Test
    void should_return_empty_list_when_list_all_given_no_todo_item_exist() {
        TodoList todoList = new TodoList();

        assertEquals(0, todoList.listAll().size());
    }
}