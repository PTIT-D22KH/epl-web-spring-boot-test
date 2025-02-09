package vn.duongvct.test.epl_app.service;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.repository.PlayerRepository;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

}
