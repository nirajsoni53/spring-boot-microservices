package com.rest.webservices.restwebservices.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@ApiModel("This Model is for transaction about user(add, update , delete ,select)")
public class User extends RepresentationModel<User> {

    @Id()
    private int id;

    @NotEmpty @Column
    @ApiModelProperty("Name should not empty")
    private String name;

}
