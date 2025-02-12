package vn.duongvct.test.epl_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Club;
import vn.duongvct.test.epl_app.domain.request.club.RequestCreateClubDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseCreateClubDTO;
import vn.duongvct.test.epl_app.repository.ClubRepository;

@Service
public class ClubService {
    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Club handleCreateClub(Club club) {
        return this.clubRepository.save(club);
    }
    public Club convertRequestCreateClubToClub(RequestCreateClubDTO clubDTO) {
        Club club = new Club();
        if (clubDTO.getCoachHistory() != null) {
            //TO-DO
        }
        if (clubDTO.getPlayerHistory() != null) {
            //TO-DO
        }
        club.setName(clubDTO.getName());
        club.setNation(clubDTO.getNation());
        return club;
    }
    public ResponseCreateClubDTO convertClubToResponseCreateClubDTO(Club club) {
        ResponseCreateClubDTO clubDTO = new ResponseCreateClubDTO();
        clubDTO.setId(club.getId());
        clubDTO.setName(club.getName());
        clubDTO.setNation(club.getNation());
        clubDTO.setCreatedAt(club.getCreatedAt());
        return clubDTO;
    }
    public Optional<Club> getClubById(Long id) {
        return this.clubRepository.findById(id);
    }
}
