package vn.duongvct.test.epl_app.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.duongvct.test.epl_app.service.PlayerService;

@RestController
public class PlayerController {
    private PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

}
