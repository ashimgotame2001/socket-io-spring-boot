package com.example.socket.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "public_chat_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublicChatHistory {

    @Id
    private UUID id;

    @OneToMany
    private List<Message> chatHistories;

    private String status;

    @OneToMany
    private List<User> members;
}
