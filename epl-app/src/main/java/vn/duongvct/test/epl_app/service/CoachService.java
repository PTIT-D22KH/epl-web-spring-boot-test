package vn.duongvct.test.epl_app.service;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.repository.CoachRepository;

@Service
public class CoachService {
    private CoachRepository coachRepository;
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
}
