package com.isaiasmalvar.talent_request_service.command.dto;

import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import lombok.Data;

@Data
public class TalentRequestResponseDTO {

    private String talentRequestID;
    private String talentRequestTitle;
    private RequestStatus requestStatus;
}
