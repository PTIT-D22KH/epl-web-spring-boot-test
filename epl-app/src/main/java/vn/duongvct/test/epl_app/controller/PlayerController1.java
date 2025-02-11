package vn.duongvct.test.epl_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseCreatePlayerDTO;
import vn.duongvct.test.epl_app.service.PlayerService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class PlayerController1 {
    private final PlayerService playerService;
    public PlayerController1(PlayerService playerService) {
        this.playerService = playerService;
    }

    // @GetMapping("/players/{id}")
    // public ResponseEntity<Void> showPlayer(@PathVariable Long id) {

    // }

    @PostMapping("/players")
    @ApiMessage("Create a new player")
    public ResponseEntity<ResponseCreatePlayerDTO> createNewPlayer(@RequestBody RequestCreatePlayerDTO player) {
        Player newPlayer = this.playerService.handleCreatePlayer(this.playerService.convertRequestCreatePlayerDTOtoPlayer(player));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.playerService.convertPlayerToResponseCreatePlayerDTO(newPlayer));
    }

}
