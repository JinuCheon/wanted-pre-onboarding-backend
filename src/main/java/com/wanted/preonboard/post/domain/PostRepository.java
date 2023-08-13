package com.wanted.preonboard.post.domain;

import java.util.HashMap;
import java.util.List;

public class PostRepository {
    HashMap<Long, Post> posts = new HashMap<>();
    Long sequence = 1L;

    public List<Post> findAll() {
        return List.copyOf(posts.values());
    }

    public void save(final Post post) {
        posts.put(sequence++, post);
    }
}
