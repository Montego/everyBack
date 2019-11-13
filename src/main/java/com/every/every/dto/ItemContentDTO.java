package com.every.every.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemContentDTO implements DTO{
    private Long id;

    private String content;

    private String contentName;

    private String contentType;

    private long contentSize;

    private String type;

    private String text;

    private String formatDateTime;

    public ItemContentDTO(String content, String contentName, String contentType, long contentSize, String type, String text) {
        this.content = content;
        this.contentName = contentName;
        this.contentType = contentType;
        this.contentSize = contentSize;
        this.type = type;
        this.text = text;
    }
}
