package com.isaiasmalvar.talent_request_service.command.dto;

import com.isaiasmalvar.tam_core_api.domain.CandidateSkills;
import com.isaiasmalvar.tam_core_api.domain.JobDescription;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateTalentRequestCommandDTO {
    private String talentRequestTitle;
    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private LocalDate startDate;

}
