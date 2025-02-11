package vn.duongvct.test.epl_app.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.request.player.RequestUpdatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseUpdatePlayerDTO;
import vn.duongvct.test.epl_app.service.PlayerService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

@RestController
@RequestMapping("/api/v1")

public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    @ApiMessage("Create a new player")
    public ResponseEntity<ResponseCreatePlayerDTO> createNewPlayer(@Valid @RequestBody RequestCreatePlayerDTO player) {
        Player newPlayer = this.playerService.handleCreatePlayer(this.playerService.convertRequestCreatePlayerDTOtoPlayer(player));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.playerService.convertPlayerToResponseCreatePlayerDTO(newPlayer));
    }

    @PutMapping("/players")
    @ApiMessage("Update a player")
    public ResponseEntity<ResponseUpdatePlayerDTO> updateAPlayer(@Valid @RequestBody RequestUpdatePlayerDTO playerDTO) throws InvalidRequestException {
        Optional<Player> player = this.playerService.getPlayerById(playerDTO.getId());
        if (!player.isPresent()) {
            throw new InvalidRequestException("Player with id = " + playerDTO.getId() + " not found.");
        }
        Player updatedPlayer = this.playerService.handleUpdatePlayer(player.get(), playerDTO);
        return ResponseEntity.ok().body(this.playerService.convertPlayerToResponseUpdatePlayerDTO(updatedPlayer));
    }

}
