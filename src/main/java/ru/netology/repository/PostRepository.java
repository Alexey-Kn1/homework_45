package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private Map<Long, Post> posts;
    private long freeId;

    public PostRepository() {
        posts = new ConcurrentHashMap<>();
        freeId = 1;
    }

    public List<Post> all() {
        var res = new ArrayList<Post>(posts.size());

        posts.forEach(
                (k, v) -> {
                    res.add(v);
                }
        );

        return res;
    }

    public Optional<Post> getById(long id) {
        var res = posts.getOrDefault(id, null);

        return res == null ? Optional.empty() : Optional.of(res);
    }

    public Post save(Post post) {
        long id;

        synchronized (this) {
            id = freeId;
            freeId++;
        }

        post.setId(id);

        posts.put(id, post);

        return post;
    }

    public void removeById(long id) throws NotFoundException {
        if (posts.remove(id) == null) {
            throw new NotFoundException("ID " + id + " not found");
        }
    }
}
