package com.bane.clean.architecture.api.mappers;

import com.bane.clean.architecture.api.dto.PostTO;
import com.bane.clean.architecture.api.entities.PostEntity;
import com.bane.clean.architecture.api.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testing")
@SpringBootTest
class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void testDtoToEntityMapping() {
        //arrange
        PostTO postTO = TestUtils.createPostTO();

        //act
        PostEntity postEntity = postMapper.dtoToEntity(postTO);

        //assert
        assertThat(postEntity).isNotNull();
        assertThat(postEntity.getId()).isEqualTo(UUID.fromString("9c7a391d-3f3f-469e-ac63-5f3797888ca2"));
        assertThat(postEntity.getAuthor()).isEqualTo("Arthur C. Clarke");
        assertThat(postEntity.getTitle()).isEqualTo("2001: A Space Odyssey");
        assertThat(postEntity.getContent()).isEqualTo("some cool sci-fi");
        assertThat(postEntity.getUpdateTime()).isNull();
        assertThat(postEntity.getCreationTime()).isNull();
    }

    @Test
    void testEntityToDtoMapping() {
        System.out.println("ajde:" + UUID.randomUUID());

        //arrange
        PostEntity postEntity = TestUtils.createPostEntity();

        //act
        PostTO postTO = postMapper.entityToTransferObject(postEntity);

        //assert
        assertThat(postTO).isNotNull();
        assertThat(postTO.getUuid()).isEqualTo(UUID.fromString("dd6efbc8-cb75-4372-8d7f-a870868237a6"));
        assertThat(postTO.getAuthor()).isEqualTo("Jared Diamond");
        assertThat(postTO.getTitle()).isEqualTo("Guns, Germs, and Steel: The Fates of Human Societies");
        assertThat(postTO.getContent()).isEqualTo("history, geography, politics");
        assertThat(postTO.getUpdateTime()).isNull();
    }
}