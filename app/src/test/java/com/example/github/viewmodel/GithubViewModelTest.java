package com.example.github.viewmodel;

import com.example.github.model.GithubResponse;
import com.example.github.repository.CommitRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GithubViewModelTest {

    private CommitRepository repository;
    private GithubViewModel subject;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        repository = mock(CommitRepository.class);
        Type listType = new TypeToken<List<GithubResponse>>() {
        }.getType();
        List<GithubResponse> list = new Gson().fromJson(loadFile(), listType);
        when(repository.getCommitList(any(), any())).thenReturn(list);
        subject = new GithubViewModel(repository);
    }


    @Test
    public void loadCommitList() {
        subject.loadCommitList("owner", "repo");
        subject.getResponse().observeForever(list -> {
            assertEquals(2, list.size());
            GithubResponse data = list.get(0);
            assertEquals("884f37e0663e516937759a0a7d23af705ecfaaec", data.getSha());
            assertEquals("Jake Wharton", data.getCommit().getAuthor().getName());
            assertEquals("Merge pull request #3289 from square/jwilson.0128.bugcrowd\n" +
                    "\n" +
                    "Migrate bug bounty URL to bugcrowd", data.getCommit().getMessage());
        });

    }

    private String loadFile() {
        InputStream is;
        try {
            is = new FileInputStream("src/test/resources/commits.json");
        } catch (FileNotFoundException e) {
            return "";
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            try {
                line = reader.readLine();
            } catch (IOException e) {
                return "";
            }
        }

        return sb.toString();
    }
}