package vn.duongvct.test.epl_app.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.duongvct.test.epl_app.domain.Club;
import vn.duongvct.test.epl_app.domain.request.club.RequestCreateClubDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseCreateClubDTO;
import vn.duongvct.test.epl_app.service.ClubService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class ClubController1 {
    private final ClubService clubService;
    public ClubController1(ClubService clubService) {
        this.clubService=clubService;
    }

    @PostMapping("/clubs")
    @ApiMessage("Create a club")
    public ResponseEntity<ResponseCreateClubDTO> createNewClub(@RequestBody RequestCreateClubDTO clubDTO) {
        Club newClub = this.clubService.handleCreateClub(this.clubService.convertRequestCreateClubToClub(clubDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clubService.convertClubToResponseCreateClubDTO(newClub));
    }
}
