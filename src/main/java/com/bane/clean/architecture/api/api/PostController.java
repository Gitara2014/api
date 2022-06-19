package com.bane.clean.architecture.api.api;

import com.bane.clean.architecture.api.dto.PostTO;
import com.bane.clean.architecture.api.services.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PostController.PATH)
@Slf4j
public class PostController {
    protected static final String PATH = "posts";

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostTO> save(@Valid @RequestBody final PostTO postTO) {
        log.debug("saving post: " + postTO);
        PostTO saved = postService.save(postTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping //TODO return updated PostTO with fresh updatedTime
    public ResponseEntity<Void> update(@Valid @RequestBody final PostTO postTO) {
        log.debug("updating post: " + postTO);
        postService.update(postTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PostTO> getById(@NotNull @RequestParam("id") final String uuid) {
        log.debug("get: " + uuid);
        PostTO postTO = postService.getById(UUID.fromString(uuid));
        return new ResponseEntity<>(postTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostTO>> getAll() {
        log.debug("getAll posts");
        List<PostTO> all = postService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/all-pageable")
    public ResponseEntity<List<PostTO>> getAllPageable(@NotNull @RequestParam("size") int size,
                                                       @NotNull @RequestParam("page") int page) {
        log.debug("all-pageable");
        log.info("all-pageable");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("author").ascending());
        return new ResponseEntity<>(postService.findAll(pageRequest), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@NotNull @RequestParam("id") final UUID uuid) {
        log.debug("delete: " + uuid);
        postService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
