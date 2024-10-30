package com.pigeonskyrace.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    private long id;
    private String name;
    private String password;
    private String email;

}
