package todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TodoListTest {

    private TodoList todoList;
    private TodoListRepository todoListRepository;

    @BeforeEach
    void setUp() {
        todoListRepository = Mockito.mock(TodoListRepository.class);
        todoList = new TodoList(todoListRepository);
    }

    @Test
    void should_return_empty_list_when_list_all_given_no_todo_item_exist() {
        assertEquals(0, todoList.listAll().size());
    }

    @Test
    void should_return_all_todo_items_when_list_all_given_both_finished_and_unfinished_items_exist() {
        when(todoListRepository.findAll()).thenReturn(List.of(
                TodoItem.builder().id(1).content("swimming").isDone(false).build(),
                TodoItem.builder().id(2).content("programming").isDone(true).build(),
                TodoItem.builder().id(3).content("gaming").isDone(false).build()
        ));

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
        when(todoListRepository.findAll()).thenReturn(List.of(
                TodoItem.builder().id(1).content("swimming").isDone(false).build(),
                TodoItem.builder().id(2).content("gaming").isDone(false).build()
        ));

        List<TodoItem> todoItems = todoListRepository.findAll();
        assertEquals(2, todoItems.size());
        assertEquals("swimming", todoItems.get(0).getContent());
        assertEquals("gaming", todoItems.get(1).getContent());
    }

    @Test
    void should_generate_sequential_item_id_when_add_new_todo_item_given_item_was_added_successfully() {
        String content = "programming";
        todoList.add(content);

        TodoItem expectedSavedTodoItem = TodoItem.builder().id(1).content(content).isDone(false).build();
        verify(todoListRepository, times(1)).save(expectedSavedTodoItem);
    }

    @Test
    void should_return_inserted_item_when_add_new_todo_item_given_item_was_added_successfully() {
        String content = "gaming";
        TodoItem expectedInsertedItem = TodoItem.builder().id(1).content(content).isDone(false).build();
        when(todoListRepository.save(expectedInsertedItem)).then(returnsFirstArg());

        TodoItem insertedItem = todoList.add("gaming");

        assertEquals(1, insertedItem.getId());
        assertEquals("gaming", insertedItem.getContent());
        assertFalse(insertedItem.isDone());
    }

    @Test
    void should_return_done_item_when_done_item_given_item_was_mark_as_done_successfully() {
        String content = "gaming";
        TodoItem todoItem = TodoItem.builder().id(2).content(content).isDone(false).build();
        when(todoListRepository.findById(2)).thenReturn(Optional.of(todoItem));
        TodoItem updatedTodoItem = TodoItem.builder().id(2).content(content).isDone(true).build();
        when(todoListRepository.save(updatedTodoItem)).then(returnsFirstArg());

        TodoItem doneItem = todoList.done(2);

        assertNotNull(doneItem);
        assertEquals(2, doneItem.getId());
        assertEquals(content, doneItem.getContent());
        assertTrue(doneItem.isDone());
    }

    @Test
    void should_return_all_unfinished_items_when_list_items() {
        when(todoListRepository.findAll()).thenReturn(List.of(
                TodoItem.builder().id(1).content("swimming").isDone(false).build(),
                TodoItem.builder().id(2).content("programming").isDone(true).build(),
                TodoItem.builder().id(3).content("gaming").isDone(false).build()
        ));

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