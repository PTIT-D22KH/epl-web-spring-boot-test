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

import vn.duongvct.test.epl_app.domain.Club;
import vn.duongvct.test.epl_app.domain.request.club.RequestCreateClubDTO;
import vn.duongvct.test.epl_app.domain.request.club.RequestUpdateClubDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseCreateClubDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseUpdateClubDTO;
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
        Club updatedClub = this.clubService.handleUpdateClub(club.get(), clubDTO);
        return ResponseEntity.ok().body(this.clubService.convertClubToResponseUpdateClub(updatedClub));
    }
    @GetMapping("/clubs/{id}")
    @ApiMessage("Fetch a club")
    public ResponseEntity<Club> fetchAClub(@PathVariable Long id) throws InvalidRequestException {
        Optional<Club> club = this.clubService.getClubById(id);
        if (!club.isPresent()) {
            throw new InvalidRequestException("Club with id = " + id + " not found.");
        }
        return ResponseEntity.ok(club.get());
    }

    @GetMapping("/clubs")
    @ApiMessage("Fetch all clubs")
    public ResponseEntity<ResultPaginationDTO> fetchAllClubs(
        @Filter Specification<Club> spec,
        Pageable pageable
    ) {
        return ResponseEntity.ok(this.clubService.fetchAllClubs(spec, pageable));
    }

    @DeleteMapping("/clubs/{id}")
    @ApiMessage("Delete a player")
    public ResponseEntity<Void> deleteAPlayer(@PathVariable Long id) {
        this.clubService.handleDeleteClub(id);
        return ResponseEntity.ok(null);
    }

}
