package com.isaiasmalvar.talent_fulfillment_service.query.query;

import lombok.Data;

@Data
public class FindTalentFulfillmentByIdQuery {

    private String talentFulfillmentId;

    public FindTalentFulfillmentByIdQuery(String talentFulfillmentId){
        this.talentFulfillmentId = talentFulfillmentId;
    }
}
