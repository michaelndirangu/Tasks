package com.mike.Tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mike.Tasks.dtos.TaskDTO;
import com.mike.Tasks.model.Task;
import com.mike.Tasks.response.EntityResponse;
import com.mike.Tasks.service.TaskService;

@CrossOrigin
@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("get")
    public ResponseEntity<EntityResponse> getTasks(){
        EntityResponse response = taskService.getTasks();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<EntityResponse> getTaskById(@PathVariable Long id) {
        EntityResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<EntityResponse> deleteTaskById(@PathVariable Long id) {
        EntityResponse response = taskService.deleteTaskById(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<EntityResponse> updateTask(@PathVariable Long id, @RequestBody Task task) {
        EntityResponse response = taskService.updateTask(id, task);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("save")
    public ResponseEntity<EntityResponse> saveTask(@RequestBody Task task) {
        EntityResponse response = taskService.createTask(task);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("update/status/{id}")
    public ResponseEntity<EntityResponse> updateTaskStatus(@PathVariable Long id, @RequestParam boolean done) {
        EntityResponse response = taskService.updateTaskStatus(id, done);
        return ResponseEntity.ok().body(response);
    }

}
