package com.springrest.springrest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "course")
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
//@ApiModel(description = "Course model ")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	
//	@Size(min = 2,message = "{ string have minimum character 2 }")
//	@ApiModelProperty(notes ="title atleast have 2 character" , example = "java", required = true)
	@Column(name = "title")
	private String title;
	
//	@ApiModelProperty(notes =" description" , example = "this is learnging for..", required = false)
	@Column(name = "description")
	private String description;
	
//	@Future(message = "{ date must be in future }")
//	@ApiModelProperty(notes ="date should be in future",  required = true)
//	@NonNull
	@Column(name = "duration")
	private Date duration;
	
	

	

}
