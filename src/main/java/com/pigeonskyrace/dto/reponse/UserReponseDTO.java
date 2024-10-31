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

    public ObjectId getId() {

        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ObjectId> getColombiers() {
        return colombiers;
    }

    public void setColombiers(List<ObjectId> colombiers) {
        this.colombiers = colombiers;
    }

    public UserReponseDTO(ObjectId id, String name, String email, Role role, List<ObjectId> colombiers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.colombiers = colombiers;
    }
}
