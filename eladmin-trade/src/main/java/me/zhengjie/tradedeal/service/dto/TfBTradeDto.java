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
package me.zhengjie.tradedeal.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author zhaohy5
* @date 2020-08-03
**/
@Data
public class TfBTradeDto implements Serializable {

    /** 执行月 */
    private String execMonth;

    /** 执行天 */
    private String execDay;

    /** 执行状态 */
    private Integer status;

    /** 执行结果 */
    private String execDesc;

    private String id;
}