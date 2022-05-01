package com.org.study.addr;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final Logger log_study = LogManager.getLogger("com.study");
    private static final Logger log_error = LogManager.getLogger("com.error");

    @Autowired
    AddrService addrService;

    @ApiOperation(value = "주소록 등록", notes = "")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> postAddr(
            @RequestBody @Valid AddrDto addr_dto ) {

        AddrDto addrDto = null;
        try {

            addrService.postAddr(addr_dto);

            addrDto = addrService.getAddr(addr_dto.getUser_id());

        } catch (Exception e) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pinrtStream = new PrintStream(out);
            e.printStackTrace(pinrtStream);
            log_error.error(out.toString());
            return new ResponseEntity<AddrResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("200","OK", addrDto), HttpStatus.OK);
    }


    @ApiOperation(value = "주소록 상세 조회", notes = "")
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getAddr(
            @PathVariable("user_id") @NotNull String user_id ) {

        AddrDto addr_dto = null;
        try {

            addr_dto = addrService.getAddr(user_id);

            System.out.println("value chk : " + addr_dto.toString());
            if (ObjectUtils.isEmpty(addr_dto)) {
                return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("204","NO_CONTENT"),HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pinrtStream = new PrintStream(out);
            e.printStackTrace(pinrtStream);
            log_error.error(out.toString());
            return new ResponseEntity<AddrResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("200","OK", addr_dto), HttpStatus.OK);
    }

    @ApiOperation(value = "주소록 목록 조회", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search_word", value = "search_word", required = false, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "page", value = "page", required = false, dataType = "integer", paramType = "query", defaultValue="1"),
            @ApiImplicitParam(name = "count", value = "data_count", required = false, dataType = "integer", paramType = "query", defaultValue=""),
    })
    @RequestMapping(value = "{search_word}/{page}/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AddrResponseDto> getAddrList(
            @ApiParam(value = "search_word", required = false) @PathVariable String search_word,
            @ApiParam(value = "page", required = true) @PathVariable("page") @Positive int page,
            @ApiParam(value = "count", required = true) @PathVariable("count") @Positive int count
    ) {

        List<AddrDto> addr_dto_lst = null;

        try {

            addr_dto_lst = addrService.getAddrList(search_word,page,count);

            if (addr_dto_lst.size() < 1) {
                return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("404","Not_Found"),HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pinrtStream = new PrintStream(out);
            e.printStackTrace(pinrtStream);
            log_error.error(out.toString());
            return new ResponseEntity<AddrResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("200","OK", addr_dto_lst), HttpStatus.OK);
    }

    @ApiOperation(value = "주소록 수정 ", notes = "")
    @RequestMapping(value = "{user_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> putAddr(
            @PathVariable("user_id") String user_id,
            @RequestBody AddrDto addr_dto
    ) {
        AddrDto _addr_dto = null;
        try {
             _addr_dto = addrService.getAddr(user_id);

            if (ObjectUtils.isEmpty(_addr_dto)) {
                return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("404","Not_Found"),HttpStatus.NOT_FOUND);
            }

            addr_dto.setUser_id(user_id);
            addrService.putAddr(addr_dto);

            _addr_dto = addrService.getAddr(user_id);

        } catch (Exception e) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pinrtStream = new PrintStream(out);
            e.printStackTrace(pinrtStream);
            log_error.error(out.toString());
            return new ResponseEntity<AddrResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("200","OK",_addr_dto), HttpStatus.OK);
    }

    @ApiOperation(value = "주소록 삭제", notes = "")
    @RequestMapping(value = "{user_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> deleteAddr(
            @PathVariable("user_id") String user_id
    ) {

        AddrDto addr_dto = null;
        try {

            addrService.deleteAddr(user_id);

        } catch (Exception e) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pinrtStream = new PrintStream(out);
            e.printStackTrace(pinrtStream);
            log_error.error(out.toString());
            return new ResponseEntity<AddrDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AddrResponseDto>(new AddrResponseDto("200","OK"), HttpStatus.OK);
    }




}
