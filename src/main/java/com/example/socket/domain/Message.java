package com.example.socket.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class  Message {
    @Id
    private UUID id;
//    @OneToOne
//    private User sender;
//    @OneToOne
//    private User receiver;
//    private UUID groupCode;
//    private LocalDateTime sendAt;
    private String message;
//    private String status;
}
