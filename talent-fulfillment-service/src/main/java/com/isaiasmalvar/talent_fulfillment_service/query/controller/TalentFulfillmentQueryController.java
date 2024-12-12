package com.isaiasmalvar.talent_fulfillment_service.query.controller;

import com.isaiasmalvar.talent_fulfillment_service.query.dto.TalentFulfillmentQueryResponseDTO;
import com.isaiasmalvar.talent_fulfillment_service.query.service.TalentFulfillmentQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("talent-fulfillment")
public class TalentFulfillmentQueryController {

    private final TalentFulfillmentQueryService talentFulfillmentQueryService;

    public TalentFulfillmentQueryController(TalentFulfillmentQueryService talentFulfillmentQueryService){
        this.talentFulfillmentQueryService = talentFulfillmentQueryService;
    }

    @GetMapping
    ResponseEntity findAllTalentFulfillments(){
        return talentFulfillmentQueryService.findAllTalentFulfillments();
    }

    @GetMapping("/{talentFulfillmentId}")
    ResponseEntity findTalentFulfillmentByTalentFulfillmentId(@PathVariable String talentFulfillmentId) throws ExecutionException, InterruptedException {
        return talentFulfillmentQueryService.findTalentFulfillmentByTalentFulfillmentId(talentFulfillmentId);
    }
}
