package com.bane.clean.architecture.api.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class PostTOTest {

    private static Validator validator;

    @BeforeAll
    public static void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @DisplayName("Create new POST happy path with all required params")
    @Test
    void validateHappyPathWithAllRequiredParams() {

        PostTO postTO = PostTO.builder()
                .uuid(UUID.randomUUID())
                .author("Arthur C. Clarke")
                .title("2001: A Space Odyssey")
                .content("some cool sci-fi")
                .build();

        Set<ConstraintViolation<PostTO>> violations = validator.validate(postTO);
        assertThat(violations).isNotNull();
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void validationFailsForTitleAndContent() {
        PostTO postTO = PostTO.builder()
                .uuid(UUID.randomUUID())
                .author("Arthur C. Clarke")
                .title("too")
                .content("short")
                .build();

        Set<ConstraintViolation<PostTO>> violations = validator.validate(postTO);
        assertThat(violations).isNotNull();
        assertThat(violations.size()).isEqualTo(2);

    }
}