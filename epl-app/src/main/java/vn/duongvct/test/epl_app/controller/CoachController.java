package vn.duongvct.test.epl_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.duongvct.test.epl_app.domain.Coach;
import vn.duongvct.test.epl_app.domain.request.coach.RequestCreateCoachDTO;
import vn.duongvct.test.epl_app.domain.response.coach.ResponseCreateCoachDTO;
import vn.duongvct.test.epl_app.service.CoachService;

@RestController
@RequestMapping("/api/v1")
public class CoachController {
    private CoachService coachService;
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("/coaches")
    public ResponseEntity<ResponseCreateCoachDTO> createNewCoach(@RequestBody RequestCreateCoachDTO coachDTO) {
        Coach newCoach = this.coachService.handleCreateCoach(this.coachService.convertRequestCreateCoachDTOtoCoach(coachDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.coachService.convertCoachToResponseCreateCoachDTO(newCoach));
    }

}
