package com.app.api;

import com.app.bean.Game;
import com.app.bean.MoveDto;
import com.app.bean.Players;
import com.app.service.GameService;
import com.app.service.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class UserApi {
    private final GameService gameService;
    private final MoveService moveService;

    @PostMapping("/new")
    private Game startNewGame(@RequestBody Players players) {
        return gameService.startGame(players);
    }

    @GetMapping("/id")
    private Game resumeGame(@PathVariable String id) {
        return gameService.resumeGame(id);
    }

    @PostMapping("/move")
    private Game makeMove(@RequestBody MoveDto moveDto) {
        return moveService.makeMove(moveDto);
    }
}
