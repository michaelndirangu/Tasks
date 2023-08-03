package com.mike.Tasks.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse <T> {
    private String message;
    private HttpStatus statusCode;
    private Boolean success;
    private T entity;
}
