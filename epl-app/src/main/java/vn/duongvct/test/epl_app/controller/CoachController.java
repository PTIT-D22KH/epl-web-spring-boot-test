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

import vn.duongvct.test.epl_app.domain.Coach;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.coach.RequestCreateCoachDTO;
import vn.duongvct.test.epl_app.domain.request.coach.RequestUpdateCoachDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.domain.response.coach.ResponseCreateCoachDTO;
import vn.duongvct.test.epl_app.domain.response.coach.ResponseUpdateCoachDTO;
import vn.duongvct.test.epl_app.service.CoachService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

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

    @PutMapping("/coaches")
    @ApiMessage("Update a coach")
    public ResponseEntity<ResponseUpdateCoachDTO> updateACoach(@RequestBody RequestUpdateCoachDTO coachDTO) throws InvalidRequestException {
        Optional<Coach> coach = this.coachService.getCoachById(coachDTO.getId());
        if (!coach.isPresent()) {
            throw new InvalidRequestException("Coach with id = " + coachDTO.getId() + " not found");
        }
        Coach updatedCoach = this.coachService.handleUpdateCoach(coach.get(), coachDTO);
        return ResponseEntity.ok(this.coachService.convertCoachToResponseUpdateCoachDTO(updatedCoach));
    }

    @GetMapping("/coaches/{id}")
    @ApiMessage("Fetch a coach")
    public ResponseEntity<Coach> fetchACoach(@PathVariable Long id) throws InvalidRequestException {
        Optional<Coach> coach = this.coachService.getCoachById(id);
        if (!coach.isPresent()) {
            throw new InvalidRequestException("Coach with id = " + id + " not found");
        }
        return ResponseEntity.ok(coach.get());
    }
    @GetMapping("/coaches")
    @ApiMessage("Fetch all coaches")
    public ResponseEntity<ResultPaginationDTO> fetchAllCoaches(
        @Filter Specification<Coach> spec,
        Pageable pageable
    ) {
        return ResponseEntity.ok(this.coachService.fetchAllCoaches(spec, pageable));
    }
    @DeleteMapping("/coaches/{id}")
    @ApiMessage("Delete a coach")
    public ResponseEntity<Void> deleteACoach(@PathVariable Long id ) {
        this.coachService.handleDeleteCoach(id);
        return ResponseEntity.ok(null);
    }
}
