// package vn.duongvct.test.epl_app.controller;

// import org.springframework.web.bind.annotation.RestController;

// import com.turkraft.springfilter.boot.Filter;

// import jakarta.validation.Valid;
// import vn.duongvct.test.epl_app.domain.Player;
// import vn.duongvct.test.epl_app.domain.request.player.RequestCreatePlayerDTO;
// import vn.duongvct.test.epl_app.domain.response.player.ResponseCreatePlayerDTO;
// import vn.duongvct.test.epl_app.service.PlayerService;
// import vn.duongvct.test.epl_app.util.annotation.ApiMessage;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.domain.Specification;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;

// @RestController
// @RequestMapping("/api/v1")

// public class PlayerController {
//     private final PlayerService playerService;

//     public PlayerController(PlayerService playerService) {
//         this.playerService = playerService;
//     }

//     @PostMapping("/players")
//     @ApiMessage("Create a new player")
//     public ResponseEntity<ResponseCreatePlayerDTO> createNewPlayer(@RequestBody RequestCreatePlayerDTO player) {
//         Player newPlayer = this.playerService.handleCreatePlayer(this.playerService.convertRequestCreatePlayerDTOtoPlayer(player));
//         return ResponseEntity.status(HttpStatus.CREATED).body(this.playerService.convertPlayerToResponseCreatePlayerDTO(newPlayer));
//     }

// }
