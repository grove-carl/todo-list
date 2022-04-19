package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class TodoListTest {

    @Test
    void should_return_empty_list_when_list_all_given_no_todo_item_exist() {
        TodoList todoList = new TodoList();

        assertEquals(0, todoList.listAll().size());
    }

    @Test
    void should_return_non_empty_list_when_list_all_given_some_todo_items_exist() {
        TodoList todoList = new TodoList();
        todoList.add("swimming");
        todoList.add("gaming");

        List<TodoItem> todoItems = todoList.listAll();
        assertEquals(2, todoItems.size());
        assertEquals("swimming", todoItems.get(0).getContent());
        assertEquals("gaming", todoItems.get(1).getContent());
    }
}