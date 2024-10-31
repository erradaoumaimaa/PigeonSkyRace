package com.pigeonskyrace.dto.reponse;

import com.pigeonskyrace.model.enums.Role;
import org.bson.types.ObjectId;

import java.util.List;

public class UserReponseDTO {
    private ObjectId id;
    private String name;
    private String email;
    private Role role;
    private List<ObjectId> colombiers;
}
