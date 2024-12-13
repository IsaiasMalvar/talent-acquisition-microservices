package com.isaiasmalvar.talent_fulfillment_service.command.controller;

import com.isaiasmalvar.talent_fulfillment_service.command.command.SubmitTalentFulfillmentDecisionCommand;
import com.isaiasmalvar.talent_fulfillment_service.command.service.TalentFulfillmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talent-fulfillment")
public class TalentFulfillmentController {

    private TalentFulfillmentService talentFulfillmentService;

    public TalentFulfillmentController(TalentFulfillmentService talentFulfillmentService){
        this.talentFulfillmentService = talentFulfillmentService;
    }

    @PostMapping("/job-post")
    public ResponseEntity submitTalentFulfillmentDecision(@RequestBody SubmitTalentFulfillmentDecisionCommand submitTalentFulfillmentDecisionCommand){
        return talentFulfillmentService.submitTalentFulfillmentDecision(submitTalentFulfillmentDecisionCommand);
    }
}
