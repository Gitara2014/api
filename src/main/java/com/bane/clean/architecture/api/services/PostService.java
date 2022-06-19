package com.bane.clean.architecture.api.services;

import com.bane.clean.architecture.api.dto.PostTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostTO save(PostTO postTO);

    PostTO getById(UUID uuid);

    List<PostTO> findAll(Pageable pageable);

    List<PostTO> findAll();

    void update(PostTO postTO);

    void delete(UUID uuid);
}
