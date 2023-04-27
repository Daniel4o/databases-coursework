package com.example.springboot.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="workout-notes")
public class WorkoutNote {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

    @NotNull(message = "The field is required!!")
	@Min(value = 0, message = "Must be greater than to 0!")
	@Max(value = 10000, message = "Must be lower than or equal to 10000!")
	@Column(name = "object_id")
	private Integer objectId;

    @NotNull(message = "The field is required!")
	@Size(min = 2, message = "The length is minimum 2 characters!")
	@Column(name = "description")
	private String description;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="deactive")
    private Date deactive;

    public WorkoutNote() {}

    public WorkoutNote(int id, int objectId, String description, Date deactive) {
        this.id = id;
        this.objectId = objectId;
        this.description = description;
        this.deactive = deactive;
    }

    public WorkoutNote(int objectId, String description, Date deactive) {
        this.objectId = objectId;
        this.description = description;
        this.deactive = deactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeactive() {
        return deactive;
    }

    public void setDeactive(Date deactive) {
        this.deactive = deactive;
    } 

    @Override
    public String toString() {
        return "WorkoutNote [id=" + id + ", objectId=" + objectId + ", description=" + description + ", deactive=" + deactive
                + "]";
    }
    
}
