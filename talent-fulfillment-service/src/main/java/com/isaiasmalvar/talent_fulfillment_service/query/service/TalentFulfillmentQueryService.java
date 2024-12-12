package com.isaiasmalvar.talent_fulfillment_service.query.service;

import com.isaiasmalvar.talent_fulfillment_service.query.dto.TalentFulfillmentQueryResponseDTO;
import com.isaiasmalvar.talent_fulfillment_service.query.query.FindTalentFulfillmentByIdQuery;
import com.isaiasmalvar.talent_fulfillment_service.query.query.FindTalentFulfillmentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TalentFulfillmentQueryService {

    private final QueryGateway queryGateway;

    public  TalentFulfillmentQueryService(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

    public ResponseEntity findAllTalentFulfillments () {

        FindTalentFulfillmentsQuery findTalentFulfillmentsQuery = new FindTalentFulfillmentsQuery();
        List<TalentFulfillmentQueryResponseDTO> talentFulfillmentQueryResponseDTOList = queryGateway.query(findTalentFulfillmentsQuery, ResponseTypes.multipleInstancesOf(TalentFulfillmentQueryResponseDTO.class)).join();
        return new ResponseEntity(talentFulfillmentQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity findTalentFulfillmentByTalentFulfillmentId(String talentFulfillmentId) throws ExecutionException, InterruptedException {
        FindTalentFulfillmentByIdQuery findTalentFulfillmentByIdQuery = new FindTalentFulfillmentByIdQuery(talentFulfillmentId);
        TalentFulfillmentQueryResponseDTO talentFulfillmentQueryResponseDTO = queryGateway.query(findTalentFulfillmentByIdQuery, ResponseTypes.instanceOf(TalentFulfillmentQueryResponseDTO.class)).get();
        return new ResponseEntity(talentFulfillmentQueryResponseDTO, HttpStatus.OK);
    }
}
