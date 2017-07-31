package com.gitHubClient;

import com.gitHubClient.client.GitHubIssuesClient;
import com.gitHubClient.domain.SummaryBuilder;
import com.gitHubClient.domain.Summary;
import com.gitHubClient.json.SummaryWriter;
import org.eclipse.egit.github.core.Issue;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args){
       //For each owner/repo
            //Use a GitHub client to fetch issues
            //Sort them by date
            //Create a list of every issue
        // Create a histogram of the issues with date -> issue

        // Create a list of "com.gitHubClient.issue". We have to include the repoId since the "org.eclipse.egit.github.core.Issue"
        // does not keep a reference to it.
        List<com.gitHubClient.domain.Issue> issues = GitHubIssuesClient.getIssues(args);


        SummaryBuilder sb = new SummaryBuilder();
        Summary summary = sb.buildSummary(issues);
        SummaryWriter sw = new SummaryWriter(true);

        //We can stream the JSON to some output stream OR
        // It's the responsibility of the caller to close the stream when done with all piping
        sw.streamWrite(System.out, summary);
        System.out.println();
        //OR We can just print the JSON String (High memory usage for a high number of repos)
        System.out.println(sw.toJson(summary));
    }}

