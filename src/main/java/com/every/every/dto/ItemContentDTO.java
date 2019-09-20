package com.every.every.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemContentDTO {
    private Long id;

    private String content;

    private String contentName;

    private String contentType;

    private long contentSize;
}
