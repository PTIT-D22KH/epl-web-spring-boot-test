package vn.duongvct.test.epl_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Coach;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.coach.RequestCreateCoachDTO;
import vn.duongvct.test.epl_app.domain.request.coach.RequestUpdateCoachDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.domain.response.coach.ResponseCreateCoachDTO;
import vn.duongvct.test.epl_app.domain.response.coach.ResponseUpdateCoachDTO;
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
    public Optional<Coach> getCoachById(Long id) {
        return this.coachRepository.findById(id);
    }
    public Coach handleUpdateCoach(Coach coach, RequestUpdateCoachDTO coachDTO) {
        if (coachDTO.getClubHistory() != null) {
            //TO-DO
        }
        coach.setName(coachDTO.getName());
        coach.setNation(coachDTO.getNation());
        coach.setAge(coachDTO.getAge());
        coach.setGender(coachDTO.getGender());
        return this.coachRepository.save(coach);

    }
    public ResponseUpdateCoachDTO convertCoachToResponseUpdateCoachDTO(Coach coach) {
        ResponseUpdateCoachDTO coachDTO = new ResponseUpdateCoachDTO();
        coachDTO.setId(coach.getId());
        coachDTO.setName(coach.getName());
        coachDTO.setNation(coach.getNation());
        coachDTO.setAge(coach.getAge());
        coachDTO.setGender(coach.getGender());
        coachDTO.setUpdatedAt(coach.getUpdatedAt());
        return coachDTO;
    }
    public ResultPaginationDTO fetchAllCoaches(Specification<Coach> spec, Pageable pageable) {
        Page<Coach> coachPage = this.coachRepository.findAll(pageable);
        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(coachPage.getTotalPages());
        meta.setTotal(coachPage.getTotalElements());
        result.setMeta(meta);
        List<Coach> list = coachPage.getContent().stream().collect(Collectors.toList());
        result.setResult(list);
        return result;
    }
    public void handleDeleteCoach(Long id) {
        Optional<Coach> coach = this.coachRepository.findById(id);
        if (coach.isPresent()) {
            Coach deletedCoach = coach.get();
            //delete all club related
            //TO-DO

        }
        

        this.coachRepository.deleteById(id);
    }
}
