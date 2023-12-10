package com.example.todo.ToDoApp;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ToDoRepository extends Neo4jRepository<ToDo,Long> {
}
