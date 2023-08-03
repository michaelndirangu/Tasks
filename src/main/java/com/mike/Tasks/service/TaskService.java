package com.mike.Tasks.service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mike.Tasks.dtos.TaskDTO;
import com.mike.Tasks.model.Category;
import com.mike.Tasks.model.Task;
import com.mike.Tasks.repository.CategoryRepo;
import com.mike.Tasks.repository.TaskRepo;
import com.mike.Tasks.response.EntityResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;    
    private final CategoryRepo categoryRepo;


    public EntityResponse getTasks() {
        EntityResponse response = new EntityResponse();
        List<Task> tasks = taskRepo.findAll();
        try {
            if (tasks.isEmpty()) {
                response.setEntity(tasks);
                response.setStatusCode(HttpStatus.NO_CONTENT);
                response.setMessage("No tasks found.");
                response.setSuccess(false);
         } else {
            response.setEntity(tasks);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Found "+ tasks.size() +" tasks");
            response.setSuccess(true);
         }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setSuccess(false);
        }
        return response;
    }


    //get by Id
    public EntityResponse getTaskById(Long id) {
        EntityResponse response = new EntityResponse();
        Optional<Task> task = taskRepo.findById(id);
        try {
            if (task.isPresent()) {
                response.setEntity(task);
                response.setStatusCode(HttpStatus.OK);
                response.setMessage("Found task no "+ id);
                response.setSuccess(true);
         } else {
                response.setStatusCode(HttpStatus.NO_CONTENT);
                response.setMessage("No tasks found.");
                response.setSuccess(false);
         }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setSuccess(false);
        }
        return response;
    }

    //delete by Id
    public EntityResponse deleteTaskById(Long id) {
        EntityResponse response = new EntityResponse();
        try {
            taskRepo.deleteById(id);
            response.setMessage("Deleted task with id "+ id);
            response.setStatusCode(HttpStatus.OK);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setSuccess(false);
        }
        return response;
    }

    //update by Id
    public EntityResponse updateTask( Long id, Task updatedTaskDTO ) {
        EntityResponse response = new EntityResponse();

        //get existing task from db
        Optional<Task> existingTaskOptional = taskRepo.findById(id);

        try {
            if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();

            //update fields from dto data
            existingTask.setTitle(updatedTaskDTO.getTitle());
            existingTask.setDescription(updatedTaskDTO.getDescription());
            existingTask.setDate(updatedTaskDTO.getDate());
            existingTask.setStatus(updatedTaskDTO.getStatus());

            Task updatedTask = taskRepo.save(existingTask);
            response.setEntity(updatedTask);
            response.setMessage("Task updated su ccesfully");
            response.setStatusCode(HttpStatus.OK);
            response.setSuccess(true);
        } else {
            response.setMessage("Task with id " + id + " not found");
            response.setSuccess(false);;
            response.setStatusCode(HttpStatus.NOT_FOUND);
        }   
        } catch (Exception e) {
            // TODO: handle exception
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return response;
    }
 

    public EntityResponse createTask(Task task) {
        EntityResponse response = new EntityResponse();
        try {
            // Save the task to the database or storage system here
            // For example: taskRepo.save(task);
            // Category category = Category.builder().name(task.getCategory()).build();
            Task tsk = Task.builder().
            title(task.getTitle())
            .description(task.getDescription()).date(task.getDate()).status(task.getStatus())
            .build();
            var t = taskRepo.save(tsk);
            
           
            // If the task creation is successful, you can set the generated ID on the task object
            // For example: task.setId(generatedId);
    
            response.setEntity(t); // Optionally, set the created task in the response data
            response.setMessage("Task created successfully");
            response.setStatusCode(HttpStatus.CREATED);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setSuccess(false);
        }
        return response;
    }


    public EntityResponse updateTaskStatus(@PathVariable Long id, @RequestParam boolean done) {
        EntityResponse response = new EntityResponse();
        try {
            Task task = taskRepo.findById(id).orElse(null);

            if(task == null) {
                response.setMessage("Task with id "+ id + " not found");
                response.setStatusCode(HttpStatus.NOT_FOUND);
                response.setSuccess(false);
                response.setEntity(task);
            } else {
                task.setStatus(task.getStatus());
                taskRepo.save(task);
                response.setMessage("Task updated successfully");
                response.setStatusCode(HttpStatus.OK);
                response.setSuccess(true);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setSuccess(false);
        }
        return response;

    }
    


}
