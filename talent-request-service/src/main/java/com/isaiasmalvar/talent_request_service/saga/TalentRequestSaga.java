package com.isaiasmalvar.talent_request_service.saga;

import com.isaiasmalvar.talent_request_service.core.events.TalentRequestCreatedEvent;
import com.isaiasmalvar.tam_core_api.command.CreateTalentFulfillmentCommand;
import com.isaiasmalvar.tam_core_api.command.UpdateTalentRequestStatusCommand;
import com.isaiasmalvar.tam_core_api.event.TalentFulfillmentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@Saga
public class TalentRequestSaga {

    public static final String TALENT_REQUEST_ID = "talentRequestId";

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = TALENT_REQUEST_ID)
    public void handle(TalentRequestCreatedEvent talentRequestCreatedEvent){
        CreateTalentFulfillmentCommand createTalentFulfillmentCommand = CreateTalentFulfillmentCommand.builder().
                talentFulfillmentId(UUID.randomUUID().toString()).
                talentRequestId(talentRequestCreatedEvent.getTalentRequestId()).
                jobDescription(talentRequestCreatedEvent.getJobDescription()).
                candidateSkills(talentRequestCreatedEvent.getCandidateSkills()).
                requestStatus(talentRequestCreatedEvent.getRequestStatus()).
                startDate(talentRequestCreatedEvent.getStartDate()).
                build();

        commandGateway.send(createTalentFulfillmentCommand);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = TALENT_REQUEST_ID)
    public void  handle(TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent){
        UpdateTalentRequestStatusCommand updateTalentRequestStatusCommand = UpdateTalentRequestStatusCommand.builder().talentRequestId(talentFulfillmentCreatedEvent.getTalentRequestId()).requestStatus(talentFulfillmentCreatedEvent.getRequestStatus()).build();

        commandGateway.send(updateTalentRequestStatusCommand);
    }
}
