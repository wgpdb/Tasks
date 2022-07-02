package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.controller.TaskTitleOrContentTooShortException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testGetAllTasks() throws TaskTitleOrContentTooShortException {
        //Given
        Task task = new Task("title", "content");

        //When
        dbService.saveTask(task);
        Long id = task.getId();

        //Then
        assertEquals(1, dbService.getAllTasks().size());

        //Cleanup
        dbService.deleteTask(id);
    }

    @Test
    void testGetTask() throws TaskNotFoundException, TaskTitleOrContentTooShortException {
        //Given
        Task task = new Task("title", "content");

        //When
        dbService.saveTask(task);
        Long id = task.getId();

        //Then
        assertTrue(taskRepository.existsById(id));
        assertEquals("title", dbService.getTask(id).getTitle());
        assertEquals("content", dbService.getTask(id).getContent());

        //Cleanup
        dbService.deleteTask(id);
    }

    @Test
    void testGetTaskShouldThrowTaskNotFoundException() throws TaskTitleOrContentTooShortException {
        //Given
        Task task = new Task("title", "content");

        //When
        dbService.saveTask(task);
        Long id = task.getId();
        Long nextId = task.getId() + 1;

        //Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(nextId));

        //Cleanup
        dbService.deleteTask(id);
    }

    @Test
    void testSaveTask() throws TaskNotFoundException, TaskTitleOrContentTooShortException {
        //Given
        Task task = new Task("title", "content");

        //When
        dbService.saveTask(task);
        Long id = task.getId();

        //Then
        assertTrue(taskRepository.existsById(id));
        assertEquals("title", dbService.getTask(id).getTitle());
        assertEquals("content", dbService.getTask(id).getContent());

        //Cleanup
        dbService.deleteTask(id);
    }

    @Test
    void testSaveTaskShouldThrowTaskTitleOrContentTooShortException() {
        //Given
        Task task = new Task("ab", "ab");

        //When & Then
        assertThrows(TaskTitleOrContentTooShortException.class, () -> dbService.saveTask(task));
    }

    @Test
    void testDeleteTask() throws TaskTitleOrContentTooShortException {
        //Given
        Task task = new Task("title", "content");
        dbService.saveTask(task);
        Long id = task.getId();

        //When
        dbService.deleteTask(id);

        //Then
        assertFalse(taskRepository.existsById(id));
    }
}