package com.isaiasmalvar.talent_request_service.query.repository;

import com.isaiasmalvar.tam_core_api.domain.CandidateSkills;
import com.isaiasmalvar.tam_core_api.domain.JobDescription;
import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class TalentRequest {

    @Id
    private String talentRequestId;
    private String talentRequestTitle;

    @Embedded
    private JobDescription jobDescription;
    @Embedded
    private CandidateSkills candidateSkills;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private LocalDate startDate;
}
