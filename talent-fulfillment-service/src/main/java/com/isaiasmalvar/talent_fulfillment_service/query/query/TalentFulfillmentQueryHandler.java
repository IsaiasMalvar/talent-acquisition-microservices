package com.isaiasmalvar.talent_fulfillment_service.query.query;

import com.isaiasmalvar.talent_fulfillment_service.query.dto.TalentFulfillmentQueryResponseDTO;
import com.isaiasmalvar.talent_fulfillment_service.query.repository.TalentFulfillment;
import com.isaiasmalvar.talent_fulfillment_service.query.repository.TalentFulfillmentRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TalentFulfillmentQueryHandler {

    private final TalentFulfillmentRepository talentFulfillmentRepository;

    public TalentFulfillmentQueryHandler(TalentFulfillmentRepository talentFulfillmentRepository){
        this.talentFulfillmentRepository = talentFulfillmentRepository;
    }

    @QueryHandler
    public List<TalentFulfillmentQueryResponseDTO> findAllTalentFulfillments(FindTalentFulfillmentsQuery findTalentFulfillmentsQuery){
        List<TalentFulfillmentQueryResponseDTO> talentFulfillmentQueryResponseDTOS = new ArrayList<>();
        generateTalentFulfillmentQueryResponseDTOListFromDB(talentFulfillmentQueryResponseDTOS);
        return talentFulfillmentQueryResponseDTOS;
    }

    private void generateTalentFulfillmentQueryResponseDTOListFromDB(List<TalentFulfillmentQueryResponseDTO> talentFulfillmentQueryResponseDTOS){
        List<TalentFulfillment> talentFulfillments = talentFulfillmentRepository.findAll();
        for(TalentFulfillment talentFulfillment : talentFulfillments){
            TalentFulfillmentQueryResponseDTO talentFulfillmentQueryResponseDTO = new TalentFulfillmentQueryResponseDTO();
            BeanUtils.copyProperties(talentFulfillment, talentFulfillmentQueryResponseDTO);
            talentFulfillmentQueryResponseDTOS.add(talentFulfillmentQueryResponseDTO);
        }
    }

    @QueryHandler
    public TalentFulfillmentQueryResponseDTO findTalentFulfillmentByTalentFulfillmentId(FindTalentFulfillmentByIdQuery findTalentFulfillmentByIdQuery){

        TalentFulfillmentQueryResponseDTO talentFulfillmentQueryResponseDTO = new TalentFulfillmentQueryResponseDTO();
        TalentFulfillment talentFulfillment = talentFulfillmentRepository.findById(findTalentFulfillmentByIdQuery.getTalentFulfillmentId()).get();
        BeanUtils.copyProperties(talentFulfillment, talentFulfillmentQueryResponseDTO);

        return talentFulfillmentQueryResponseDTO;
    }

}
