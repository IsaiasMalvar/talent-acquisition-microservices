package com.isaiasmalvar.career_portal_service.query.query;

import com.isaiasmalvar.career_portal_service.query.dto.JobPostQueryResponseDTO;
import com.isaiasmalvar.career_portal_service.query.repository.JobPost;
import com.isaiasmalvar.career_portal_service.query.repository.JobPostRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobPostQueryHandler {
    private JobPostRepository jobPostRepository;

    public JobPostQueryHandler(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }


    @QueryHandler
    public List<JobPostQueryResponseDTO> FindAllJobPostsQuery(FindAllJobPostsQuery findAllJobPostsQuery){

        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        generateJobPostQueryResponseDTOListFromDatabase(jobPostQueryResponseDTOList);

        return jobPostQueryResponseDTOList;
    }

    private void generateJobPostQueryResponseDTOListFromDatabase(List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList) {
        List<JobPost> jobPostList = jobPostRepository.findAll();

        for(JobPost jobPost: jobPostList){
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
    }

    @QueryHandler
    public JobPostQueryResponseDTO findJobPostByJobPostId(FindJobPostByJobPostIdQuery findJobPostByJobPostIdQuery){
        JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();

        JobPost jobPost = jobPostRepository.findById(findJobPostByJobPostIdQuery.getJobPostId()).get();

        BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);

        return jobPostQueryResponseDTO;
    }

}
