package com.khwinana.matsobane.assessment;

import lombok.*;

import java.util.Date;

@ToString
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    private String text;
    private Long id;
}
