package com.isaiasmalvar.talent_fulfillment_service.command.aggregate;

import com.isaiasmalvar.tam_core_api.command.CreateTalentFulfillmentCommand;
import com.isaiasmalvar.tam_core_api.domain.*;
import com.isaiasmalvar.tam_core_api.event.TalentFulfillmentCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Aggregate
public class TalentFulfillmentAggregate {

    @AggregateIdentifier
    private String talentFulfillmentId;

    private String jobPostId;
    private String talentRequestId;
    private String talentRequestTitle;
    private LocalDate startDate;

    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private RequestStatus requestStatus;
    private RoleLevel roleLevel;
    private EmploymentType employmentType;

    private TalentFulfillmentAggregate(){}

    @CommandHandler
    public TalentFulfillmentAggregate(CreateTalentFulfillmentCommand createTalentFulfillmentCommand){
        TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent = new TalentFulfillmentCreatedEvent();
        BeanUtils.copyProperties(createTalentFulfillmentCommand,talentFulfillmentCreatedEvent);

        talentFulfillmentCreatedEvent.setRequestStatus(RequestStatus.ASSIGNED_TO_TA);

        AggregateLifecycle.apply(talentFulfillmentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent){
        talentFulfillmentId = talentFulfillmentCreatedEvent.getTalentFulfillmentId();
        talentRequestId = talentFulfillmentCreatedEvent.getTalentRequestId();
        talentRequestTitle = talentFulfillmentCreatedEvent.getTalentRequestTitle();
        candidateSkills = talentFulfillmentCreatedEvent.getCandidateSkills();
        jobDescription = talentFulfillmentCreatedEvent.getJobDescription();
        requestStatus = talentFulfillmentCreatedEvent.getRequestStatus();
        startDate = talentFulfillmentCreatedEvent.getStartDate();
    }
}
