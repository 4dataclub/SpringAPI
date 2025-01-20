package org.example.springapi.job;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    @Override
    public boolean createJob(Job job) {
        jobRepository.save(job);
        return true;
    }

    @Override
    public boolean deleteJob(Long jobId) {
        try {
            jobRepository.deleteById(jobId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long jobId, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setSalary(updatedJob.getSalary());
            jobRepository.save(job);
            return true;
        }

        return false;
    }
}
