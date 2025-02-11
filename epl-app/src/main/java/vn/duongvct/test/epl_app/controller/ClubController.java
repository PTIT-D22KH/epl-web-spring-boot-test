package vn.duongvct.test.epl_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import vn.duongvct.test.epl_app.domain.Club;
import vn.duongvct.test.epl_app.domain.request.club.RequestCreateClubDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseCreateClubDTO;
import vn.duongvct.test.epl_app.service.ClubService;

@RestController
@RequestMapping("/api/v1")
public class ClubController {
    private ClubService clubService;
    public ClubController(ClubService clubService) {
        this.clubService=clubService;
    }

    @PostMapping("/clubs")
    public ResponseEntity<ResponseCreateClubDTO> createNewClub(@RequestBody RequestCreateClubDTO clubDTO) {
        Club newClub = this.clubService.handleCreateClub(this.clubService.convertRequestCreateClubToClub(clubDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clubService.convertClubToResponseCreateClubDTO(newClub));
    }
}
