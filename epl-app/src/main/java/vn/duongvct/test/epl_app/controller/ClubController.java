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
import vn.duongvct.test.epl_app.domain.Club;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.club.RequestCreateClubDTO;
import vn.duongvct.test.epl_app.domain.request.club.RequestUpdateClubDTO;
import vn.duongvct.test.epl_app.domain.request.player.RequestUpdatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseCreateClubDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseUpdateClubDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseUpdatePlayerDTO;
import vn.duongvct.test.epl_app.service.ClubService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

@RestController
@RequestMapping("/api/v1")
public class ClubController {
    private ClubService clubService;
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/clubs")
    public ResponseEntity<ResponseCreateClubDTO> createNewClub(@RequestBody RequestCreateClubDTO clubDTO) {
        Club newClub = this.clubService.handleCreateClub(this.clubService.convertRequestCreateClubToClub(clubDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clubService.convertClubToResponseCreateClubDTO(newClub));
    }
    @PutMapping("/clubs")
    @ApiMessage("Update a club")
    public ResponseEntity<ResponseUpdateClubDTO> updateAClub(@RequestBody RequestUpdateClubDTO clubDTO) throws InvalidRequestException {
        Optional<Club> club = this.clubService.getClubById(clubDTO.getId());
        if (!club.isPresent()) {
            throw new InvalidRequestException("Club with id = " + clubDTO.getId() + " not found.");
        }
        Club updatedClub = this.clubService.handleUpdatePlayer(club.get(), clubDTO);
        return ResponseEntity.ok().body(this.clubService.(updatedClub));
    }
    // @GetMapping("/players/{id}")
    // @ApiMessage("Fetch a player")
    // public ResponseEntity<Player> fetchAPlayer(@PathVariable Long id) throws InvalidRequestException {
    //     Optional<Player> player = this.playerService.getPlayerById(id);
    //     if (!player.isPresent()) {
    //         throw new InvalidRequestException("Player with id = " + id + " not found.");
    //     }
    //     return ResponseEntity.ok(player.get());
    // }

    // @GetMapping("/players")
    // @ApiMessage("Fetch all players")
    // public ResponseEntity<ResultPaginationDTO> fetchAllPlayers(
    //     @Filter Specification<Player> spec,
    //     Pageable pageable
    // ) {
    //     return ResponseEntity.ok(this.playerService.fetchAllPlayers(spec, pageable));
    // }

    // @DeleteMapping("/players/{id}")
    // @ApiMessage("Delete a player")
    // public ResponseEntity<Void> deleteAPlayer(@PathVariable Long id) {
    //     this.playerService.handleDeletePlayer(id);
    //     return ResponseEntity.ok(null);
    // }

}
