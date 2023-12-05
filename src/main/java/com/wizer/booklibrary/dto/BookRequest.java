package com.wizer.booklibrary.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String author;
    private Integer rating;
    private Long categoryId;

}
