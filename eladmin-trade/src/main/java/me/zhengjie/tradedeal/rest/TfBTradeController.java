/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.tradedeal.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.tradedeal.domain.TfBTrade;
import me.zhengjie.tradedeal.service.TfBTradeService;
import me.zhengjie.tradedeal.service.dto.TfBTradeQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author zhaohy5
* @date 2020-08-03
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "Trade管理")
@RequestMapping("/api/tfBTrade")
public class TfBTradeController {

    private final TfBTradeService tfBTradeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tfBTrade:list')")
    public void download(HttpServletResponse response, TfBTradeQueryCriteria criteria) throws IOException {
        tfBTradeService.download(tfBTradeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Trade")
    @ApiOperation("查询Trade")
    @PreAuthorize("@el.check('tfBTrade:list')")
    public ResponseEntity<Object> query(TfBTradeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tfBTradeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Trade")
    @ApiOperation("新增Trade")
    @PreAuthorize("@el.check('tfBTrade:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TfBTrade resources){
        return new ResponseEntity<>(tfBTradeService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Trade")
    @ApiOperation("修改Trade")
    @PreAuthorize("@el.check('tfBTrade:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TfBTrade resources){
        tfBTradeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除Trade")
    @ApiOperation("删除Trade")
    @PreAuthorize("@el.check('tfBTrade:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        tfBTradeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}