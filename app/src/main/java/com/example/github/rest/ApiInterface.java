package com.example.github.rest;

import com.example.github.model.GithubResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("{owner}/{repo}/commits")
    Call<List<GithubResponse>> getCommits();
}
