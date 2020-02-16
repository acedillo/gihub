package com.example.github.repository;

import com.example.github.model.GithubResponse;

import java.util.List;

public interface CommitRepository {
    List<GithubResponse> getCommitList(String owner, String repo);
}
