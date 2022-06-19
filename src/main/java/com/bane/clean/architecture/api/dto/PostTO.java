package com.bane.clean.architecture.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Value
@Builder
public class PostTO {

    //@com.bane.clean.architecture.api.dto.UUID  //TODO
    //@NotNull
    UUID uuid; // ? private needed? ? validator

    @Size(min = 5, max = 255)
    @NotNull String title;

    String author;

    @Size(min = 10)
    @NotNull
    String content;

    String updateTime;

}
