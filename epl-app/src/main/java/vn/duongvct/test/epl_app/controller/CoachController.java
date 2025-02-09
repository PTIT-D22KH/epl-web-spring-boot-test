package vn.duongvct.test.epl_app.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.duongvct.test.epl_app.service.CoachService;

@RestController
public class CoachController {
    private CoachService coachService;
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

}
