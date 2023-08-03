package com.mike.Tasks.dtos;
import java.util.Date;

import com.mike.Tasks.model.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Date date;
    private String status;
    // private Category category;

    
    // public TaskDTO(Long id, String title, String description, Date date, String status) {
    //     this.id = id;
    //     this.title = title;
    //     this.description = description;
    //     this.date = date;
    //     this.status = status;
    // }


    
}
