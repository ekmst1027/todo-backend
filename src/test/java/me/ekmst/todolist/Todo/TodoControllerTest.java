package me.ekmst.todolist.Todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ekmst.todolist.domain.Todo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoControllerTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeAll
    void setup(WebApplicationContext ctx) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("투두리스트 생성")
    void createTodoList() throws Exception {
        String content = "투두리스트 생성";

        Todo todo = new Todo();
        Todo createdTodo = todo.createTodo(content);

        mockMvc.perform(post("/api/v1/createTodo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdTodo)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("투두리스트 조회")
    void getTodoList() throws Exception {
        for(int i = 0; i < 10; i++) {
            String content = "투두리스트 " + String.valueOf(i);
            Todo savedTodo = new Todo(content);
            todoRepository.save(savedTodo);
        }

        mockMvc.perform(get("/api/v1/getTodoList"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("투두리스트 단건조회")
    void getTodoOne() throws Exception {
        String content = "투두리스트";
        Todo savedTodo = new Todo(content);
        todoRepository.save(savedTodo);

        mockMvc.perform(get("/api/v1/getTodoList/1"))
                .andDo(print())
                .andExpect(status().isOk());


    }


}