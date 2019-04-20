package com.winterchen.controller;

import com.winterchen.model.UserDomain;
import com.winterchen.service.UserService;
import com.winterchen.util.ExportExcelSeedBack;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :10:03 2019/4/15
 * @Modefied By:
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private UserService userService;

    //创建表头
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("显示名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);
    }

    //生成user表excel
    @GetMapping(value = "/getUser")
    public String getUser(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook,sheet);
        List<UserDomain> rows = userService.findAllUser();

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(UserDomain user:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getUserId());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getPassword());
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(user.getUserEducation());
            cell.setCellStyle(style);
            rowNum++;
        }

        String fileName = "导出excel例子.xls";

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        return path;
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse res) throws Exception{
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
//        OutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        res.reset();
        res.setContentType("application/vnd.ms-excel;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename="
                + new String((filename + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = res.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    @GetMapping("/orderrdExportExcel")
    public void grouppExportExcel( @Param("grade") String grade
                                   ,@Param("major") String major
                                   ,@Param("gender") String gender
                                   ,HttpServletRequest request
                                   ,HttpServletResponse response) throws Exception{
    List<UserDomain> groupList = userService.findUserExcel(grade,major,gender);
    //导出文件的标题
        String title = "校友信息.xls";
        //设置表格标题行
        String[] headers = new String[] {"序号","编号","姓名","年级","班级","性别","籍贯","学历","联系方式","工作城市","工作公司","工作职位"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < groupList.size(); i++) {
            objs = new Object[headers.length];
            objs[0] = 0;//设置序号,在工具类中会出现
            objs[1] = groupList.get(i).getUserBkey();
            objs[2] = groupList.get(i).getUserName();
            objs[3] = groupList.get(i).getUserGrade();
            objs[4] = groupList.get(i).getUserMajor();
            objs[5] = groupList.get(i).getUserGender();
            objs[6] = groupList.get(i).getUserBirthPlace();
            objs[7] = groupList.get(i).getUserEducation();
            objs[8] = groupList.get(i).getPhone();
            objs[9] = groupList.get(i).getUserAddress();
            objs[10] = groupList.get(i).getUserCompany();
            objs[11] = groupList.get(i).getUserPosition();
            dataList.add(objs);//数据添加到excel表格
        }
        //使用流将数据导出
        OutputStream out = null;
        try {
            //防止中文乱码
            String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);//没有标题
            ex.export(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
