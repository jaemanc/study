package com.org.study.addr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddrDto {

    @NotNull
    private String user_id;

    @NotNull
    private String user_nm;

    private String birth;

    private String gender;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String memo;

    private String del_yn;

    private String created_dtm;

}
