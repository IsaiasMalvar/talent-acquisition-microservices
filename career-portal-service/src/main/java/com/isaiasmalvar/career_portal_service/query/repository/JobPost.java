package com.isaiasmalvar.career_portal_service.query.repository;

import com.isaiasmalvar.tam_core_api.domain.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class JobPost {

    @Id
    private String jobPostId;
    private String talentFulfillmentId;
    private String talentRequestId;
    private String talentRequestTitle;
    private LocalDate startDate;

    @Embedded
    private JobDescription jobDescription;
    @Embedded
    private CandidateSkills coreSkill;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    @Enumerated(EnumType.STRING)
    private RoleLevel roleLevel;
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
}
