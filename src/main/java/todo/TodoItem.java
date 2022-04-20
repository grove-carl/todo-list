package todo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoItem {

    private final int id;
    private final String content;
    private boolean isDone;

    public void done() {
        this.isDone = true;
    }
}
