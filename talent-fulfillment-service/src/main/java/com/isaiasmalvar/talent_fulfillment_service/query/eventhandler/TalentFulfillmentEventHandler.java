package com.isaiasmalvar.talent_fulfillment_service.query.eventhandler;

import com.isaiasmalvar.talent_fulfillment_service.query.repository.TalentFulfillment;
import com.isaiasmalvar.talent_fulfillment_service.query.repository.TalentFulfillmentRepository;
import com.isaiasmalvar.tam_core_api.event.TalentFulfillmentCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TalentFulfillmentEventHandler {

    private TalentFulfillmentRepository talentFulfillmentRepository;

    public TalentFulfillmentEventHandler(TalentFulfillmentRepository talentFulfillmentRepository){
        this.talentFulfillmentRepository = talentFulfillmentRepository;
    }

    @EventHandler
    public void on(TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent){
        TalentFulfillment talentFulfillment = new TalentFulfillment();
        BeanUtils.copyProperties(talentFulfillmentCreatedEvent, talentFulfillment);
        talentFulfillment.setTalentFulfillmentId(talentFulfillmentCreatedEvent.getTalentFulfillmentId());

        talentFulfillmentRepository.save(talentFulfillment);
    }
}