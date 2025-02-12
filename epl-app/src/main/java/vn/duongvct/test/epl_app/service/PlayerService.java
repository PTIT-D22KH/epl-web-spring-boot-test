package vn.duongvct.test.epl_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.request.player.RequestUpdatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseUpdatePlayerDTO;
import vn.duongvct.test.epl_app.repository.PlayerRepository;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player handleCreatePlayer(Player player) {
        // TODO Auto-generated method stub
        
        return this.playerRepository.save(player);
    }

    public ResponseCreatePlayerDTO convertPlayerToResponseCreatePlayerDTO(Player player) {
        ResponseCreatePlayerDTO playerDTO = new ResponseCreatePlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setNation(player.getNation());
        playerDTO.setAge(player.getAge());
        playerDTO.setCreatedAt(player.getCreatedAt());
        playerDTO.setGender(player.getGender());
        return playerDTO;
    }
    public Player convertRequestCreatePlayerDTOtoPlayer(RequestCreatePlayerDTO playerDTO) {
        Player player = new Player();
        if (playerDTO.getClubHistory() != null) {
            //TODO
        }
        if (playerDTO.getPositions() != null) {
            //TODO
        }
        player.setAge(playerDTO.getAge());
        player.setGender(playerDTO.getGender());
        player.setName(playerDTO.getName());
        player.setNation(playerDTO.getNation());
        return player;
    }

    public Optional<Player> getPlayerById(Long id) {
        return this.playerRepository.findById(id);
    }

    public Player handleUpdatePlayer(Player player, RequestUpdatePlayerDTO playerDTO) {
        if (playerDTO.getPositions() != null) {
            //TO-DO
        }
        if (playerDTO.getClubHistory() != null) {
            //TO-DO
        }
        player.setName(playerDTO.getName());
        player.setNation(playerDTO.getNation());
        player.setAge(playerDTO.getAge());
        player.setGender(playerDTO.getGender());
        return this.playerRepository.save(player);
    }
    public ResponseUpdatePlayerDTO convertPlayerToResponseUpdatePlayerDTO(Player player) {
        ResponseUpdatePlayerDTO playerDTO = new ResponseUpdatePlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setNation(player.getNation());
        playerDTO.setAge(player.getAge());
        playerDTO.setGender(player.getGender());
        playerDTO.setUpdatedAt(player.getUpdatedAt());
        return playerDTO;

    }
    public ResultPaginationDTO fetchAllPlayers(Specification<Player> spec, Pageable pageable) {
        Page<Player> pagePlayer = this.playerRepository.findAll(pageable);
        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(pagePlayer.getTotalPages());
        meta.setTotal(pagePlayer.getTotalElements());
        result.setMeta(meta);
        List<Player> list = pagePlayer.getContent().stream().collect(Collectors.toList());
        result.setResult(list);
        return result;
    }
    public void handleDeletePlayer(Long id) {
        Optional<Player> player = this.playerRepository.findById(id);
        if (player.isPresent()) {
            Player deletedPlayer = player.get();
            //delete all club related
            //TO-DO

            //delete all player positions
            //TO-DO
        }
        

        this.playerRepository.deleteById(id);
    }

}
