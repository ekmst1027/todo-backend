package me.ekmst.todolist.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    @DisplayName("투두 리스트 만들기")
    void createTodo() {
        String content = "투두리스트 만들기";

        Todo todo = new Todo(content);

        Assertions.assertThat(todo.getContent()).isEqualTo(content);
    }

}