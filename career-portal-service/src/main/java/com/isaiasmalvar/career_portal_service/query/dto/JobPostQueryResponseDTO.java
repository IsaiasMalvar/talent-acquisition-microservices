package com.isaiasmalvar.career_portal_service.query.dto;

import com.isaiasmalvar.tam_core_api.domain.CandidateSkills;
import com.isaiasmalvar.tam_core_api.domain.EmploymentType;
import com.isaiasmalvar.tam_core_api.domain.JobDescription;
import com.isaiasmalvar.tam_core_api.domain.RoleLevel;
import lombok.Data;

@Data
public class JobPostQueryResponseDTO {

    private String jobPostId;
    private String talentRequestTitle;
    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private RoleLevel roleLevel;
    private EmploymentType employmentType;
}
