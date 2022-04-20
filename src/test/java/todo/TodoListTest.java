package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoListTest {

    private TodoList todoList;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
    }

    @Test
    void should_return_empty_list_when_list_all_given_no_todo_item_exist() {
        assertEquals(0, todoList.listAll().size());
    }

    @Test
    void should_return_all_todo_items_when_list_all_given_both_finished_and_unfinished_items_exist() {
        todoList.add("swimming");
        todoList.add("programming");
        todoList.add("gaming");
        todoList.done(2);

        List<TodoItem> todoItems = todoList.listAll();

        assertEquals(3, todoItems.size());
        assertEquals(1, todoItems.get(0).getId());
        assertEquals("swimming", todoItems.get(0).getContent());
        assertFalse(todoItems.get(0).isDone());
        assertEquals(2, todoItems.get(1).getId());
        assertEquals("programming", todoItems.get(1).getContent());
        assertTrue(todoItems.get(1).isDone());
        assertEquals(3, todoItems.get(2).getId());
        assertEquals("gaming", todoItems.get(2).getContent());
        assertFalse(todoItems.get(2).isDone());
    }

    @Test
    void should_return_non_empty_list_when_list_all_given_some_todo_items_exist() {
        todoList.add("swimming");
        todoList.add("gaming");

        List<TodoItem> todoItems = todoList.listAll();
        assertEquals(2, todoItems.size());
        assertEquals("swimming", todoItems.get(0).getContent());
        assertEquals("gaming", todoItems.get(1).getContent());
    }

    @Test
    void should_generate_sequential_item_id_when_add_new_todo_item_given_item_was_added_successfully() {
        todoList.add("swimming");
        todoList.add("gaming");

        List<TodoItem> todoItems = todoList.listAll();
        assertEquals(2, todoItems.size());
        assertEquals(1, todoItems.get(0).getId());
        assertEquals(2, todoItems.get(1).getId());
    }

    @Test
    void should_return_inserted_item_when_add_new_todo_item_given_item_was_added_successfully() {
        TodoItem insertedItem = todoList.add("gaming");

        assertEquals(1, insertedItem.getId());
        assertEquals("gaming", insertedItem.getContent());
        assertFalse(insertedItem.isDone());
    }

    @Test
    void should_return_done_item_when_done_item_given_item_was_mark_as_done_successfully() {
        todoList.add("swimming");
        todoList.add("gaming");

        TodoItem doneItem = todoList.done(2);

        assertNotNull(doneItem);
        assertEquals(2, doneItem.getId());
        assertEquals("gaming", doneItem.getContent());
        assertTrue(doneItem.isDone());
    }

    @Test
    void should_return_all_unfinished_items_when_list_items() {
        todoList.add("swimming");
        todoList.add("programming");
        todoList.add("gaming");
        todoList.done(2);

        List<TodoItem> todoItems = todoList.listAllUnfinished();

        assertEquals(2, todoItems.size());
        assertEquals(1, todoItems.get(0).getId());
        assertEquals("swimming", todoItems.get(0).getContent());
        assertFalse(todoItems.get(0).isDone());
        assertEquals(3, todoItems.get(1).getId());
        assertEquals("gaming", todoItems.get(1).getContent());
        assertFalse(todoItems.get(1).isDone());
    }
}