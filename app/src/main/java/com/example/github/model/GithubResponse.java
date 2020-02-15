package com.example.github.model;

public class GithubResponse {

    String sha;
    Commit commit;


    public static class Commit{
        Author author;
        String message;

    }

    public static class Author{
        String name;
    }
}
