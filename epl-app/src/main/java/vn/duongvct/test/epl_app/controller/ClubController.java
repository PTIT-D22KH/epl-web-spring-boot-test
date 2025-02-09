package vn.duongvct.test.epl_app.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.duongvct.test.epl_app.service.ClubService;

@RestController
public class ClubController {
    private ClubService clubService;
    public ClubController(ClubService clubService) {
        this.clubService=clubService;
    }
}
