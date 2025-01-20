package org.example.springapi.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        if(jobService.createJob(job)) {
            return new ResponseEntity<>("Job created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{jobId}", method = RequestMethod.GET)
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        if(job != null) {
            return new ResponseEntity<>(job, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{jobId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {
        if(jobService.deleteJob(jobId)) {
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{jobId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        if(jobService.updateJob(jobId, job)) {
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
