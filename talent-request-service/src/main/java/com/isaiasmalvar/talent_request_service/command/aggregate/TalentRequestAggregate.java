package com.isaiasmalvar.talent_request_service.command.aggregate;

import com.isaiasmalvar.talent_request_service.command.command.CreateTalentRequestCommand;
import com.isaiasmalvar.talent_request_service.core.events.TalentRequestCreatedEvent;
import com.isaiasmalvar.tam_core_api.domain.CandidateSkills;
import com.isaiasmalvar.tam_core_api.domain.JobDescription;
import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
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



    @EventHandler
    public void on(TalentRequestCreatedEvent talentRequestCreatedEvent){
        this.talentRequestId = talentRequestCreatedEvent.getTalentRequestId();
        this.talentRequestTitle = talentRequestCreatedEvent.getTalentRequestTitle();
        this.jobDescription = talentRequestCreatedEvent.getJobDescription();
        this.candidateSkills = talentRequestCreatedEvent.getCandidateSkills();
        this.requestStatus = talentRequestCreatedEvent.getRequestStatus();
        this.startDate = talentRequestCreatedEvent.getStartDate();
    }
}
