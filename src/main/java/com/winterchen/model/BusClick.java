package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description
 * @Date :9:34 2019/4/15
 * @Modefied By:
 */
@Data
public class BusClick {
    @ExcelColumn(value = "cityCode", col = 1)
    private String cityCode;

    @ExcelColumn(value = "markId", col = 2)
    private String markId;

    @ExcelColumn(value = "toaluv", col = 3)
    private String toaluv;

    @ExcelColumn(value = "date", col = 4)
    private String date;

    @ExcelColumn(value = "clientVer", col = 5)
    private String clientVer;
}
