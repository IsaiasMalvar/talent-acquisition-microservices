package com.isaiasmalvar.talent_request_service.query.query;

import com.isaiasmalvar.talent_request_service.query.dto.TalentRequestQueryResponseDTO;
import com.isaiasmalvar.talent_request_service.query.repository.TalentRequest;
import com.isaiasmalvar.talent_request_service.query.repository.TalentRequestRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TalentRequestQueryHandler {

    private final TalentRequestRepository talentRequestRepository;

    public TalentRequestQueryHandler(TalentRequestRepository talentRequestRepository){
        this.talentRequestRepository = talentRequestRepository;
    }

    @QueryHandler
    public List<TalentRequestQueryResponseDTO> findAllTalentRequests(FindTalentRequestsQuery findTalentRequestsQuery){
        List<TalentRequestQueryResponseDTO> talentRequestQueryResponseDTOList = new ArrayList<>();
        generateTalentRequestDTOListFromDatabase(talentRequestQueryResponseDTOList);
        return talentRequestQueryResponseDTOList;
    }

    private void generateTalentRequestDTOListFromDatabase(List<TalentRequestQueryResponseDTO> talentRequestQueryResponseDTOList){
        List<TalentRequest> talentRequestList = talentRequestRepository.findAll();
        for(TalentRequest talentRequest : talentRequestList){
            TalentRequestQueryResponseDTO talentRequestQueryResponseDTO = new TalentRequestQueryResponseDTO();
            BeanUtils.copyProperties(talentRequest, talentRequestQueryResponseDTO);
            talentRequestQueryResponseDTOList.add(talentRequestQueryResponseDTO);
        }
    }

    @QueryHandler
    public TalentRequestQueryResponseDTO findTalentRequestByTalentRequestId(FindTalentRequestByTalentRequestIdQuery findTalentRequestByTalentRequestIdQuery){

        TalentRequestQueryResponseDTO talentRequestQueryResponseDTO = new TalentRequestQueryResponseDTO();
        TalentRequest talentRequest = talentRequestRepository.findById(findTalentRequestByTalentRequestIdQuery.getTalentRequestId()).get();
        BeanUtils.copyProperties(talentRequest, talentRequestQueryResponseDTO);

        return talentRequestQueryResponseDTO;
    }
}
