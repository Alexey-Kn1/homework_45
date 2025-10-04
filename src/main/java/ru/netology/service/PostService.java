package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.PostData;
import ru.netology.repository.PostRepository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        var allStoredPosts = repository.all();

        var res = new ArrayList<Post>();

        allStoredPosts
                .stream()
                .filter((element) -> !element.isDeleted())
                .forEach((element) -> res.add(new Post(element)));

        return res;
    }

    public Post getById(long id) {
        var postData = repository.getById(id).orElseThrow(NotFoundException::new);

        if (postData.isDeleted()) {
            throw new NotFoundException();
        }

        return new Post(postData);
    }

    public Post save(Post post) {
        return new Post(repository.save(new PostData(post)));
    }

    public void removeById(long id) {
        var storedPostData = repository.getById(id).orElseThrow(NotFoundException::new);

        if (storedPostData.isDeleted()) {
            throw new NotFoundException();
        }

        storedPostData.setDeleted(true);
    }
}

