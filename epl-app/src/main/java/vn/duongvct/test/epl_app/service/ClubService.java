package vn.duongvct.test.epl_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Club;
import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.club.RequestCreateClubDTO;
import vn.duongvct.test.epl_app.domain.request.club.RequestUpdateClubDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseCreateClubDTO;
import vn.duongvct.test.epl_app.domain.response.club.ResponseUpdateClubDTO;
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
    public Club handleUpdateClub(Club club, RequestUpdateClubDTO clubDTO) {
        if (clubDTO.getCoachHistory() != null) {
            //TO-DO
        }
        if (clubDTO.getPlayerHistory() != null) {
            //TO-DO
        }
        club.setName(clubDTO.getName());
        club.setNation(clubDTO.getNation());
        return this.clubRepository.save(club);
    }
    public ResponseUpdateClubDTO convertClubToResponseUpdateClub(Club club) {
        ResponseUpdateClubDTO clubDTO = new ResponseUpdateClubDTO();
        clubDTO.setId(club.getId());
        clubDTO.setName(club.getName());
        clubDTO.setNation(club.getNation());
        clubDTO.setUpdatedAt(club.getUpdatedAt());
        return clubDTO;

    }
    public ResultPaginationDTO fetchAllClubs(Specification<Club> spec, Pageable pageable) {
        Page<Club> clubPage = this.clubRepository.findAll(pageable);
        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(clubPage.getTotalPages());
        meta.setTotal(clubPage.getTotalElements());
        result.setMeta(meta);
        List<Club> list = clubPage.getContent().stream().collect(Collectors.toList());
        result.setResult(list);
        return result;
    }
    public void handleDeleteClub(Long id) {
        Optional<Club> club = this.clubRepository.findById(id);
        if (club.isPresent()) {
            Club deletedClub = club.get();
            //delete all club related
            //TO-DO

            //delete all player positions
            //TO-DO
        }
        

        this.clubRepository.deleteById(id);
    }
}
