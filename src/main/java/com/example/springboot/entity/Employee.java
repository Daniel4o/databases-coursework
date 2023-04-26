package com.example.springboot.entity;

import com.example.springboot.validation.EmployeeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = "The field is required!")
	@Size(min = 3, message = "The length is minimum 3 characters!")
	@Column(name = "last_name")
	private String lastName;

	@Email(message = "Enter a valid email!")
	@NotNull(message = "The field is required!!")
	@Column(name = "email")
	private String email;

	@NotNull(message = "The field is required!!")
	@Min(value = 0, message = "Must be greater than or equal to 0!")
	@Max(value = 10, message = "Must be lower than or equal to 10!")
	@Column(name = "priority")
	private Integer priority;

	@Pattern(regexp="^[a-zA-z0-9]{10}", message="Code must be 10 characters!")
	@EmployeeCode
	private String employeeCode;

	public Employee() {
	}

	public Employee(int id, String firstName, String lastName, String email, Integer priority, String employeeCode) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.priority = priority;
		this.employeeCode = employeeCode;
	}

	public Employee(String firstName, String lastName, String email, Integer priority, String employeeCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.priority = priority;
		this.employeeCode = employeeCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", priority=" + priority + "]";
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

}
