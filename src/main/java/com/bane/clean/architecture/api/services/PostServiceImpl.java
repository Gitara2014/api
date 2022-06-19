package com.bane.clean.architecture.api.services;

import com.bane.clean.architecture.api.dto.PostTO;
import com.bane.clean.architecture.api.entities.PostEntity;
import com.bane.clean.architecture.api.mappers.PostMapper;
import com.bane.clean.architecture.api.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    @Override
    public PostTO save(PostTO postTO) {
        PostEntity postEntity = postMapper.dtoToEntity(postTO);
        log.debug(postEntity.toString());
        postEntity.setId(java.util.UUID.randomUUID());
        PostEntity savedEntity = postRepository.save(postEntity);
        return postMapper.entityToTransferObject(savedEntity);
    }

    @Override
    public PostTO getById(UUID uuid) {

        List<PostEntity> allEntity = postRepository.findAll();
        for (PostEntity postEntity : allEntity) {
            log.debug("Exists: " + postRepository.existsById(postEntity.getId()));
        }

        PostEntity postEntity = postRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid.toString()));
        return postMapper.entityToTransferObject(postEntity);
    }

    @Override
    public List<PostTO> findAll() {
        List<PostEntity> allEntity = postRepository.findAll();
        List<PostTO> listPostTO = new ArrayList<>();
        for (PostEntity postEntity : allEntity) {
            PostTO postTO = postMapper.entityToTransferObject(postEntity);
            listPostTO.add(postTO);
        }
        return listPostTO;
    }

    @Override
    public List<PostTO> findAll(Pageable pageable) {
        Page<PostEntity> allPosts = postRepository.findAll(pageable);
        log.debug("total pages: " + allPosts.getTotalPages());
        log.debug("total elements: " + allPosts.getTotalElements());
        Sort sort = allPosts.getSort();
        //Slice<PostEntity> allPostsSlice = postRepository.findAll(pageable);
        return allPosts.get()
                .map(postMapper::entityToTransferObject)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(PostTO postTO) {
        postRepository.updatePost(postTO.getTitle(),
                postTO.getAuthor(),
                postTO.getContent(),
                ZonedDateTime.now(),
                postTO.getUuid());
    }

    @Transactional
    @Override
    public void delete(UUID uuid) {
        //postRepository.deleteById(uuid);
        postRepository.deleteEntityById(uuid);
    }
}
