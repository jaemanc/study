package com.org.study.addr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.org.study.addr.AddrDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddrResponseDto {

    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddrDto data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddrDto> datas;

    public AddrResponseDto(String code, String message, List<AddrDto> datas) {
        this.code = code;
        this.message = message;
        this.datas = datas;
    }

    public AddrResponseDto(String code, String message, AddrDto data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AddrResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
