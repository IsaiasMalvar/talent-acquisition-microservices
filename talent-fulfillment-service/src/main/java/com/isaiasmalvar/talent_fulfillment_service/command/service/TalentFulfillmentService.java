package com.isaiasmalvar.talent_fulfillment_service.command.service;

import com.isaiasmalvar.talent_fulfillment_service.command.command.SubmitTalentFulfillmentDecisionCommand;
import com.isaiasmalvar.talent_fulfillment_service.query.repository.TalentFulfillment;
import com.isaiasmalvar.talent_fulfillment_service.query.repository.TalentFulfillmentRepository;
import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TalentFulfillmentService {

    private final CommandGateway commandGateway;
    private TalentFulfillmentRepository talentFulfillmentRepository;

    public TalentFulfillmentService(CommandGateway commandGateway, TalentFulfillmentRepository talentFulfillmentRepository){
        this.commandGateway = commandGateway;
        this.talentFulfillmentRepository = talentFulfillmentRepository;
    }

    public ResponseEntity submitTalentFulfillmentDecision(SubmitTalentFulfillmentDecisionCommand submitTalentFulfillmentDecisionCommand){

        TalentFulfillment talentFulfillment = talentFulfillmentRepository.findById(submitTalentFulfillmentDecisionCommand.getTalentFulfillmentId()).get();

        if(talentFulfillment.getJobPostId() != null){
            return new ResponseEntity("Job post Id " + talentFulfillment.getJobPostId() + " is already " + talentFulfillment.getRequestStatus() + " ", HttpStatus.BAD_REQUEST);
        }

        if(submitTalentFulfillmentDecisionCommand.getRequestStatus().equals(RequestStatus.APPROVED)){
            submitTalentFulfillmentDecisionCommand.setJobPostId(UUID.randomUUID().toString());

            commandGateway.sendAndWait(submitTalentFulfillmentDecisionCommand);
            return new ResponseEntity(submitTalentFulfillmentDecisionCommand, HttpStatus.OK);
        }

        return new ResponseEntity("Not Approved", HttpStatus.BAD_REQUEST);
    }
}
