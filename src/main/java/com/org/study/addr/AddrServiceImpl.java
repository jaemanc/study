package com.org.study.addr;

import com.org.study.addr.AddrDto;
import com.org.study.addr.AddrService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddrServiceImpl implements AddrService {

    AddrMapper addrMapper;

    public AddrServiceImpl(AddrMapper addrMapper) {
        this.addrMapper = addrMapper;
    }

    @Override
    public AddrDto getAddr(String user_id) throws Exception {

        AddrDto addr_dto = addrMapper.getAddr(user_id);

        return addr_dto;
    }

    @Override
    public List<AddrDto> getAddrList(String search_word, int page, int count) throws Exception {

        page = (page - 1) * count;

        List<AddrDto> addr_dto_lst = addrMapper.getAddrList(search_word, page, count);
        return addr_dto_lst;
    }

    @Override
    public void postAddr(AddrDto addr_dto) throws Exception {

        addrMapper.postAddr(addr_dto);
    }

    @Override
    public void putAddr(AddrDto addr_dto) throws Exception {

        addrMapper.putAddr(addr_dto);

    }

    @Override
    public void deleteAddr(String user_id) throws Exception {

        addrMapper.deleteAddr(user_id);

    }
}

