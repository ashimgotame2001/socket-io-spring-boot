package com.example.socket.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "private_chat_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrivateChatHistory {

    @Id
    private UUID id;

    @OneToMany
    private List<Message> chatHistories;

    private String status;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;



}
