package me.ekmst.todolist.Todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.ekmst.todolist.domain.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoRepository todoRepository;

    @PostMapping("/createTodo")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping("/getTodoList")
    public Result getTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        return new Result(todoList.size(), todoList);
    }

    @GetMapping("/getTodoList/{id}")
    public Result getTodoOne(@PathVariable("id") Long id) {
        Optional<Todo> todoOne = todoRepository.findById(id);
        return new Result(1, todoOne);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        int count;
        T data;
    }

}
