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
package me.zhengjie.tradedeal.service.impl;

import me.zhengjie.tradedeal.domain.TfBTrade;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.tradedeal.repository.TfBTradeRepository;
import me.zhengjie.tradedeal.service.TfBTradeService;
import me.zhengjie.tradedeal.service.dto.TfBTradeDto;
import me.zhengjie.tradedeal.service.dto.TfBTradeQueryCriteria;
import me.zhengjie.tradedeal.service.mapstruct.TfBTradeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author zhaohy5
* @date 2020-08-03
**/
@Service
@RequiredArgsConstructor
public class TfBTradeServiceImpl implements TfBTradeService {

    private final TfBTradeRepository tfBTradeRepository;
    private final TfBTradeMapper tfBTradeMapper;

    @Override
    public Map<String,Object> queryAll(TfBTradeQueryCriteria criteria, Pageable pageable){
        Page<TfBTrade> page = tfBTradeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tfBTradeMapper::toDto));
    }

    @Override
    public List<TfBTradeDto> queryAll(TfBTradeQueryCriteria criteria){
        return tfBTradeMapper.toDto(tfBTradeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public TfBTradeDto findById(String id) {
        TfBTrade tfBTrade = tfBTradeRepository.findById(id).orElseGet(TfBTrade::new);
        ValidationUtil.isNull(tfBTrade.getId(),"TfBTrade","id",id);
        return tfBTradeMapper.toDto(tfBTrade);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TfBTradeDto create(TfBTrade resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return tfBTradeMapper.toDto(tfBTradeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TfBTrade resources) {
        TfBTrade tfBTrade = tfBTradeRepository.findById(resources.getId()).orElseGet(TfBTrade::new);
        ValidationUtil.isNull( tfBTrade.getId(),"TfBTrade","id",resources.getId());
        tfBTrade.copy(resources);
        tfBTradeRepository.save(tfBTrade);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            tfBTradeRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TfBTradeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TfBTradeDto tfBTrade : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("执行月", tfBTrade.getExecMonth());
            map.put("执行天", tfBTrade.getExecDay());
            map.put("执行状态", tfBTrade.getStatus());
            map.put("执行结果", tfBTrade.getExecDesc());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}