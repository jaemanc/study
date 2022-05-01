package com.org.study.addr;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddrMapper {

    public AddrDto getAddr(String user_id) throws Exception;

    public List<AddrDto> getAddrList(String search_word, int page, int count) throws Exception;

    public void postAddr(AddrDto addr_dto) throws Exception;

    public void putAddr(AddrDto addr_dto) throws Exception;

    public void deleteAddr(String user_id) throws Exception;

}
