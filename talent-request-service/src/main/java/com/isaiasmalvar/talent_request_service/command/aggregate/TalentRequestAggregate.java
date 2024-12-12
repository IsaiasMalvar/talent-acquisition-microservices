package com.isaiasmalvar.talent_request_service.command.aggregate;

import com.isaiasmalvar.talent_request_service.command.command.CreateTalentRequestCommand;
import com.isaiasmalvar.talent_request_service.core.events.TalentRequestCreatedEvent;
import com.isaiasmalvar.talent_request_service.core.events.TalentRequestStatusUpdatedEvent;
import com.isaiasmalvar.tam_core_api.command.UpdateTalentRequestStatusCommand;
import com.isaiasmalvar.tam_core_api.domain.CandidateSkills;
import com.isaiasmalvar.tam_core_api.domain.JobDescription;
import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Aggregate
public class TalentRequestAggregate {

    @AggregateIdentifier
    private String talentRequestId;
    private String talentRequestTitle;
    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private RequestStatus requestStatus;
    private LocalDate startDate;

    public  TalentRequestAggregate(){}

    @CommandHandler
    public  TalentRequestAggregate(CreateTalentRequestCommand createTalentRequestCommand){

        TalentRequestCreatedEvent talentRequestCreatedEvent = new TalentRequestCreatedEvent();
        BeanUtils.copyProperties(createTalentRequestCommand, talentRequestCreatedEvent);
        AggregateLifecycle.apply(talentRequestCreatedEvent);
    }



    @EventSourcingHandler
    public void on(TalentRequestCreatedEvent talentRequestCreatedEvent){
        this.talentRequestId = talentRequestCreatedEvent.getTalentRequestId();
        this.talentRequestTitle = talentRequestCreatedEvent.getTalentRequestTitle();
        this.jobDescription = talentRequestCreatedEvent.getJobDescription();
        this.candidateSkills = talentRequestCreatedEvent.getCandidateSkills();
        this.requestStatus = talentRequestCreatedEvent.getRequestStatus();
        this.startDate = talentRequestCreatedEvent.getStartDate();
    }

    @CommandHandler
    public void handle(UpdateTalentRequestStatusCommand updateTalentRequestStatusCommand){
        TalentRequestStatusUpdatedEvent talentRequestStatusUpdateEvent = new  TalentRequestStatusUpdatedEvent();
        BeanUtils.copyProperties(updateTalentRequestStatusCommand, talentRequestStatusUpdateEvent);
        AggregateLifecycle.apply(talentRequestStatusUpdateEvent);
    }

    @EventSourcingHandler
    public void on(TalentRequestStatusUpdatedEvent talentRequestStatusUpdatedEvent){
        requestStatus = talentRequestStatusUpdatedEvent.getRequestStatus();
    }
}
