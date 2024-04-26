package com.app.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Game {
    @Id
    private String id;
    private Cell[][] board;
    private GameStatus status;
    private Players players;
}
