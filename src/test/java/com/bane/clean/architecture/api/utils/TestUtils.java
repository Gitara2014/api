package com.bane.clean.architecture.api.utils;

import com.bane.clean.architecture.api.dto.PostTO;
import com.bane.clean.architecture.api.entities.PostEntity;

import java.util.UUID;

public final class TestUtils {

    public static PostTO createPostTO() {
        return PostTO.builder()
                .uuid(UUID.fromString("9c7a391d-3f3f-469e-ac63-5f3797888ca2"))
                .author("Arthur C. Clarke")
                .title("2001: A Space Odyssey")
                .content("some cool sci-fi")
                .build();
    }

    public static PostEntity createPostEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(UUID.fromString("dd6efbc8-cb75-4372-8d7f-a870868237a6"));
        postEntity.setTitle("Guns, Germs, and Steel: The Fates of Human Societies");
        postEntity.setAuthor("Jared Diamond");
        postEntity.setContent("history, geography, politics");
        return postEntity;
    }
}
