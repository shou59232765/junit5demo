package com.my.jenkinsapi;

import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.job.Job;
import com.surenpi.jenkins.client.job.Jobs;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class JenkinsTest {



    @Test
    public  void test_conn_jenkins() throws URISyntaxException, URISyntaxException, IOException {
        URI serverURI = new URI("http://localhost:8080");
        Jenkins jenkins = new Jenkins(serverURI, "soso", "123456");

        Jobs jobMgr = jenkins.getJobs();
        List<Job> allJobs = jobMgr.getAllJobs();

        for(Job job : allJobs)
        {
            System.out.println(job.getName());
        }
    }

}
