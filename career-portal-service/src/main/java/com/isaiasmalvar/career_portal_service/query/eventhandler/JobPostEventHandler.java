package com.isaiasmalvar.career_portal_service.query.eventhandler;

import com.isaiasmalvar.career_portal_service.query.repository.JobPost;
import com.isaiasmalvar.career_portal_service.query.repository.JobPostRepository;
import com.isaiasmalvar.tam_core_api.event.JobPostCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class JobPostEventHandler {

    private JobPostRepository jobPostRepository;

    public JobPostEventHandler (JobPostRepository jobPostRepository){
        this.jobPostRepository = jobPostRepository;
    }

    @EventHandler
    public void on(JobPostCreatedEvent jobPostCreatedEvent){
        JobPost jobPost = new JobPost();
        jobPost.setJobPostId(jobPostCreatedEvent.getJobPostId());
        BeanUtils.copyProperties(jobPostCreatedEvent, jobPost);
        jobPostRepository.save(jobPost);
    }
}
