package vn.duongvct.test.epl_app.service;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.repository.ClubRepository;

@Service
public class ClubService {
    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

}
