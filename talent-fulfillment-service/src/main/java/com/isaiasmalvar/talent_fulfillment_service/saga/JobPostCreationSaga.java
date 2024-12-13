package com.isaiasmalvar.talent_fulfillment_service.saga;

import com.isaiasmalvar.talent_fulfillment_service.core.events.TalentFulfillmentDecisionSubmittedEvent;
import com.isaiasmalvar.tam_core_api.command.CreateJobPostCommand;
import com.isaiasmalvar.tam_core_api.command.UpdateTalentRequestStatusCommand;
import com.isaiasmalvar.tam_core_api.event.JobPostCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class JobPostCreationSaga {

    public static final String TALENT_FULFILLMENT_ID = "talentFulfillmentId";

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = TALENT_FULFILLMENT_ID)
    public void handle (TalentFulfillmentDecisionSubmittedEvent talentFulfillmentDecisionSubmittedEvent){
        CreateJobPostCommand createJobPostCommand = CreateJobPostCommand.builder().
                jobPostId(talentFulfillmentDecisionSubmittedEvent.getJobPostId()).
                talentFulfillmentId(talentFulfillmentDecisionSubmittedEvent.getTalentFulfillmentId()).
                talentRequestId(talentFulfillmentDecisionSubmittedEvent.getTalentRequestId()).
                talentRequestTitle(talentFulfillmentDecisionSubmittedEvent.getTalentRequestTitle()).
                startDate(talentFulfillmentDecisionSubmittedEvent.getStartDate()).
                jobDescription(talentFulfillmentDecisionSubmittedEvent.getJobDescription()).
                candidateSkills(talentFulfillmentDecisionSubmittedEvent.getCandidateSkills()).
                requestStatus(talentFulfillmentDecisionSubmittedEvent.getRequestStatus()).
                roleLevel(talentFulfillmentDecisionSubmittedEvent.getRoleLevel()).
                employmentType(talentFulfillmentDecisionSubmittedEvent.getEmploymentType()).
                build();

        commandGateway.send(createJobPostCommand);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = TALENT_FULFILLMENT_ID)
    public void handle(JobPostCreatedEvent jobPostCreatedEvent){
        UpdateTalentRequestStatusCommand updateTalentRequestStatusCommand = UpdateTalentRequestStatusCommand.builder().talentRequestId(jobPostCreatedEvent.getTalentRequestId()).requestStatus(jobPostCreatedEvent.getRequestStatus()).build();

        commandGateway.send(updateTalentRequestStatusCommand);
    }
}
