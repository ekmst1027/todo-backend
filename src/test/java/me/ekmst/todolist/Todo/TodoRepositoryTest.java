package me.ekmst.todolist.Todo;

import me.ekmst.todolist.domain.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    @DisplayName("투두 리스트 저장")
    void saveTodoList() {
        String content = "투두 리스트 내용";

        Todo todoList = new Todo();
        Todo todo = todoList.createTodo(content);

        Todo savedTodo = todoRepository.save(todo);
        Todo findTodo = todoRepository.findById(savedTodo.getId()).get();

        assertThat(findTodo.getId()).isEqualTo(todo.getId());
        assertThat(findTodo.getContent()).isEqualTo(todo.getContent());
        assertThat(findTodo.getCreatedDate()).isEqualTo(todo.getCreatedDate());
    }

}