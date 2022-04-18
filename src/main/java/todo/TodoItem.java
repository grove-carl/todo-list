package todo;

import lombok.Data;

@Data
public class TodoItem {

    private final int id;
    private final String content;
    private final boolean isDone;
}
