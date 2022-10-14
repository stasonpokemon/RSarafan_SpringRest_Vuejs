package com.spring.rest.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    /**
     * если методы в контроллере Get, Post, Put, Delete и тд будут помеченны аннотацией  @JsonView() с определённым уровнем поля,
     * например @JsonView(Views.IdText.class) , то json будет возвращать объект, включающий только те поля,
     * которые помечены аннотацией @JsonView(Views.IdText.class) или помеченые пердками
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    @Column
    @JsonView(Views.IdText.class)
    private String text;

    // updatable = false - не обновляемое
    @Column(name = "creation_date", updatable = false)
    // меняем формат даты
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;
}
