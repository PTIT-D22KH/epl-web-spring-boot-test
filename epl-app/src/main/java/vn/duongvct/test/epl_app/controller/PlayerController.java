package vn.duongvct.test.epl_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.duongvct.test.epl_app.service.PlayerService;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    private PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

}
