package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(task);
        assertEquals(1L, taskDto.getId());
        assertEquals("title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(taskDtoList);
        assertEquals(1, taskDtoList.size());
    }
}