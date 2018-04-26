package com.mmall.beans;

import lombok.*;

import java.util.Set;

/**
 * permission
 * com.mmall.beans
 * Created by Zhang Chen
 * 4/26/2018
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;

    private String message;

    private Set<String> receivers;

}
