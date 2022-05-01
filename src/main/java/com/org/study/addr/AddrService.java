package com.org.study.addr;

import com.org.study.addr.AddrDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddrService {

    public AddrDto getAddr(String user_id) throws Exception;

    public List<AddrDto> getAddrList(String search_word, int page, int count) throws Exception;

    public void postAddr(AddrDto addr_dto) throws Exception;

    public void putAddr(AddrDto addr_dto) throws Exception;

    public void deleteAddr(String user_id) throws Exception;

}
