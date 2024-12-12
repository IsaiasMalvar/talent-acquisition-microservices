package com.isaiasmalvar.talent_request_service.core.events;

import com.isaiasmalvar.tam_core_api.domain.RequestStatus;
import lombok.Data;

@Data
public class TalentRequestStatusUpdatedEvent {

    private String talentRequestId;
    private RequestStatus requestStatus;
}
