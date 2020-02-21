package me.ekmst.todolist.Todo;

import lombok.RequiredArgsConstructor;
import me.ekmst.todolist.domain.Todo;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoRepository todoRepository;

    @PostMapping("/createTodo")
    public Todo createTodo(@RequestBody Todo todo) {
        System.out.println("todo = " + todo);
        return todoRepository.save(todo);
    }

}
