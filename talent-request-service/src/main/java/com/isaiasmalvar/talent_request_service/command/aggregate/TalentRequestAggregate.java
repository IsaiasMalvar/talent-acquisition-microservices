package com.isaiasmalvar.talent_request_service.command.aggregate;

import com.isaiasmalvar.tam_core_api.domain.CandidateSkills;
import com.isaiasmalvar.tam_core_api.domain.JobDescription;
import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

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

}
