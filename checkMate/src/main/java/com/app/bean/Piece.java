package com.app.bean;

import lombok.Data;

@Data
public class Piece {
    private boolean dead;
    private Role role;
    private Color color;
}
