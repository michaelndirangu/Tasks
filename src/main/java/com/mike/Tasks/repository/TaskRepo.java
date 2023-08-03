package com.mike.Tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mike.Tasks.model.Task;
import com.mike.Tasks.service.TaskService;

public interface TaskRepo extends JpaRepository<Task, Long>  {

}
