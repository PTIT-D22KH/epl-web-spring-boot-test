package vn.duongvct.test.epl_app.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.request.player.RequestUpdatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
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
    public ResponseEntity<ResponseCreatePlayerDTO> createNewPlayer(@RequestBody RequestCreatePlayerDTO player) {
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
    @GetMapping("/players/{id}")
    @ApiMessage("Fetch a player")
    public ResponseEntity<Player> fetchAPlayer(@PathVariable Long id) throws InvalidRequestException {
        Optional<Player> player = this.playerService.getPlayerById(id);
        if (!player.isPresent()) {
            throw new InvalidRequestException("Player with id = " + id + " not found.");
        }
        return ResponseEntity.ok(player.get());
    }

    @GetMapping("/players")
    @ApiMessage("Fetch all players")
    public ResponseEntity<ResultPaginationDTO> fetchAllPlayers(
        @Filter Specification<Player> spec,
        Pageable pageable
    ) {
        return ResponseEntity.ok(this.playerService.fetchAllPlayers(spec, pageable));
    }

    @DeleteMapping("/players/{id}")
    @ApiMessage("Delete a player")
    public ResponseEntity<Void> deleteAPlayer(@PathVariable Long id) {
        this.playerService.handleDeletePlayer(id);
        return ResponseEntity.ok(null);
    }

}
