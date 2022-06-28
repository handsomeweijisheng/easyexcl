package com.wjs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wjs
 * @create 2022-06-28 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class B_Bid_Info {
    @ExcelProperty(value = "ID")
    private Integer id;
    @ExcelProperty(value = "投资ID")
    private Integer loan_id;
    @ExcelProperty(value = "用户ID")
    private Integer uid;
    @ExcelProperty(value = "投资金额")
    private Integer bid_money;
    @ExcelProperty(value = "投资时间")
    private Date bid_time;
    @ExcelProperty(value = "投资状态")
    private Integer bid_status;
}
