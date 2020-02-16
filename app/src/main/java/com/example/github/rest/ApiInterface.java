package com.example.github.rest;

import com.example.github.model.GithubResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{owner}/{repo}/commits")
    Call<List<GithubResponse>> getCommits(@Path("owner") String owner, @Path("repo")String repo);
}
