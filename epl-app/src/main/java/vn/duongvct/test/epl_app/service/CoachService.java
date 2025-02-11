package vn.duongvct.test.epl_app.service;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Coach;
import vn.duongvct.test.epl_app.domain.request.coach.RequestCreateCoachDTO;
import vn.duongvct.test.epl_app.domain.response.coach.ResponseCreateCoachDTO;
import vn.duongvct.test.epl_app.repository.CoachRepository;

@Service
public class CoachService {
    private CoachRepository coachRepository;
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public Coach handleCreateCoach(Coach coach) {
        return this.coachRepository.save(coach);
    }
    public Coach convertRequestCreateCoachDTOtoCoach(RequestCreateCoachDTO coachDTO) {
        Coach coach = new Coach();
        if (coachDTO.getClubHistory() != null) {
            //TO-DO
        }
        coach.setAge(coachDTO.getAge());
        coach.setName(coachDTO.getName());
        coach.setNation(coachDTO.getNation());
        coach.setGender(coachDTO.getGender());
        return coach;
    }
    public ResponseCreateCoachDTO convertCoachToResponseCreateCoachDTO(Coach coach) {
        ResponseCreateCoachDTO coachDTO = new ResponseCreateCoachDTO();
        coachDTO.setId(coach.getId());
        coachDTO.setName(coach.getName());
        coachDTO.setNation(coach.getNation());
        coachDTO.setAge(coach.getAge());
        coachDTO.setGender(coach.getGender());
        coachDTO.setCreatedAt(coach.getCreatedAt());
        return coachDTO;
    }
}
