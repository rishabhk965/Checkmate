package com.app.bean;

import lombok.Data;

@Data
public class MoveDto {
    private String gameId;
    private int prevX;
    private int prevY;
    private int newX;
    private int newY;
}
