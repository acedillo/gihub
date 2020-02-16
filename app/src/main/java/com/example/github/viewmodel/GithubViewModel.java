package com.example.github.viewmodel;

import com.example.github.model.GithubResponse;
import com.example.github.repository.CommitRepository;
import com.example.github.repository.RetrofitCommitRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GithubViewModel extends ViewModel {

    private CommitRepository commitRepository;

    public GithubViewModel(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<List<GithubResponse>> response = new MutableLiveData<>();

    public static ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new GithubViewModel(new RetrofitCommitRepository());
            }
        };
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }

    public LiveData<List<GithubResponse>> getResponse() {
        return response;
    }

    public void loadCommitList(String owner, String repo) {
        new Thread(() -> {
            loading.postValue(true);
            List<GithubResponse> commitList = commitRepository.getCommitList(owner, repo);
            loading.postValue(false);
            response.postValue(commitList);
        }).start();
    }

}
