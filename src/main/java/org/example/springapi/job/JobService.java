package org.example.springapi.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job getJobById(Long jobId);
    boolean createJob(Job job);
    boolean deleteJob(Long jobId);
    boolean updateJob(Long jobId, Job job);
}
