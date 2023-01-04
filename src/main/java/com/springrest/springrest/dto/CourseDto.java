package com.springrest.springrest.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "CourseDTO model ")
@JsonFilter("courseDto")
public class CourseDto {
	
//	@NotNull
	private long id;
	
	@Size(min = 2,message = "{ string have minimum character 2 }")
	@ApiModelProperty(notes ="title atleast have 2 character" , example = "java", required = true)
	private String title;
	
	@ApiModelProperty(notes =" description" , example = "this is learnging for..", required = false)
	private String description;
	
	@Future(message = "{ date must be in future }")
	@ApiModelProperty(notes ="date should be in future",  required = true)
	@NonNull
	private Date duration;

}
