package vn.duongvct.test.epl_app.service;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.Player;
import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
import vn.duongvct.test.epl_app.domain.response.player.ResponseCreatePlayerDTO;
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

}
