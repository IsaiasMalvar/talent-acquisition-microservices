package com.isaiasmalvar.talent_request_service.query.eventhandler;

import com.isaiasmalvar.talent_request_service.core.events.TalentRequestCreatedEvent;
import com.isaiasmalvar.talent_request_service.core.events.TalentRequestStatusUpdatedEvent;
import com.isaiasmalvar.talent_request_service.query.repository.TalentRequest;
import com.isaiasmalvar.talent_request_service.query.repository.TalentRequestRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TalentRequestEventHandler {

    private TalentRequestRepository talentRequestRepository;

    public TalentRequestEventHandler(TalentRequestRepository talentRequestRepository){
        this.talentRequestRepository = talentRequestRepository;
    }

    @EventHandler
    public void on(TalentRequestCreatedEvent talentRequestCreatedEvent){

        TalentRequest talentRequest = new TalentRequest();
        BeanUtils.copyProperties(talentRequestCreatedEvent, talentRequest);
        talentRequestRepository.save(talentRequest);
    }

    @EventHandler
    public void on(TalentRequestStatusUpdatedEvent talentRequestStatusUpdatedEvent){
        TalentRequest talentRequest = talentRequestRepository.findById(talentRequestStatusUpdatedEvent.getTalentRequestId()).get();
        talentRequest.setRequestStatus(talentRequestStatusUpdatedEvent.getRequestStatus());
        talentRequestRepository.save(talentRequest);
    }
}
