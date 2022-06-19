package com.bane.clean.architecture.api.repositories;

import com.bane.clean.architecture.api.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {


    @Modifying
    @Query("update PostEntity p set p.title = ?1, p.author = ?2, p.content = ?3, " +
            " p.updateTime = ?4 where p.id = ?5")
    int updatePost(String title,
                   String author,
                   String content,
                   ZonedDateTime updateTime,
                   UUID uuid);

    @Modifying
    @Query("delete from PostEntity p where p.id = ?1")
    int deleteEntityById(UUID uuid);

}
