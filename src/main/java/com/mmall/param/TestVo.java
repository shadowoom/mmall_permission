package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * permission
 * com.mmall.param
 * Created by Zhang Chen
 * 4/21/2018
 */

@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull
    @Max(value = 10, message = "id cannot be greater than 10")
    @Min(value = 0, message = "id must be equal or greater than 0")
    private Integer id;

    private List<String> strList;

}
