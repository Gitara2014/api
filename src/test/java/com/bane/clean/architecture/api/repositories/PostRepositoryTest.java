package com.bane.clean.architecture.api.repositories;

import com.bane.clean.architecture.api.entities.PostEntity;
import com.bane.clean.architecture.api.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * "@DataJpaTest": Using this annotation will disable full auto-configuration and instead apply only configuration relevant to JPA tests.
 * <p>
 * By default, tests annotated with @DataJpaTest are transactional and roll back at the end of each test.
 * <p>
 * They also use an embedded in-memory database (replacing any explicit or usually auto-configured DataSource).
 * The @AutoConfigureTestDatabase annotation can be used to override these settings.
 * <p>
 * SQL queries are logged by default by setting the spring.jpa.show-sql property to true. This can be disabled using the showSql attribute.
 */
@ActiveProfiles("testing")
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void emptyRepository() {
        List<PostEntity> allPosts = postRepository.findAll();
        assertThat(allPosts).isEmpty();
    }

    @Test
    void testInsertAndDelete() {
        //arrange
        PostEntity postEntity = TestUtils.createPostEntity();

        //act
        postRepository.save(postEntity);

        //assert
        List<PostEntity> savedPosts = postRepository.findAll();
        assertThat(savedPosts).isNotEmpty().hasSize(1);
        assertThat(savedPosts.get(0).getAuthor()).isEqualTo("Jared Diamond");

        postRepository.deleteAll();
        Optional<PostEntity> deletedPost = postRepository.findById(postEntity.getId());
        assertThat(deletedPost.isPresent()).isFalse();
    }
}