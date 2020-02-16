package com.example.github.repository;

import com.example.github.model.GithubResponse;
import com.example.github.rest.ApiClient;
import com.example.github.rest.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RetrofitCommitRepository implements CommitRepository {
    @Override
    public List<GithubResponse> getCommitList(String owner, String repo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GithubResponse>> call = apiService.getCommits(owner, repo);
        Response<List<GithubResponse>> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response == null || response
                .body() == null ? new ArrayList<GithubResponse>() : response.body();
    }
}
