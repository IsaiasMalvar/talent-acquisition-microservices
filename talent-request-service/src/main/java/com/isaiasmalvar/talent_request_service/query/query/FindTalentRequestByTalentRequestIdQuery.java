package com.isaiasmalvar.talent_request_service.query.query;

import lombok.Data;

@Data
public class FindTalentRequestByTalentRequestIdQuery {

    private String talentRequestId;

    public FindTalentRequestByTalentRequestIdQuery(String talentRequestId){
        this.talentRequestId = talentRequestId;
    }
}
