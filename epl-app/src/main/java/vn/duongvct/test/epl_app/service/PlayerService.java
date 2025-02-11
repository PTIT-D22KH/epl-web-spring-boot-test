package vn.duongvct.test.epl_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.request.player.RequestUpdatePlayerDTO;
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

}
