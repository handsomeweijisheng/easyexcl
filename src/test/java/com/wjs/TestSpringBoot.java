package com.wjs;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import com.wjs.entity.B_Bid_Info;
import com.wjs.mapper.BidInfoMapper;
import com.wjs.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author wjs
 * @create 2022-06-28 16:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBoot {
    @Autowired
    private UserService userService;
    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Test
    public void getUser() {
        String userinfo = userService.getUser();
        System.out.println(userinfo);
    }

    /**
     * 讲数据库文件写出到excel
     */
    @Test
    public void getAll() {
        List<B_Bid_Info> bidInfoMapperAll = bidInfoMapper.findAll();
        //System.out.println(bidInfoMapperAll);
        //    讲数据写出到文件中
        EasyExcel.write("F:\\p1\\投资信息.xls")
                .head(B_Bid_Info.class)
                .excelType(ExcelTypeEnum.XLS)
                .sheet("投资数据")
                .doWrite(bidInfoMapperAll);
    }

    /**
     * 讲excel中的数据读取到内存中------------->这种方式比较麻烦,下面方法有种简化的方式
     */
    @Test
    public void readAll() {
        //    读取文件
        //    创建ExcelReaderBuilder实例
        ExcelReaderBuilder readerBuilder = EasyExcel.read();
        //    获取文件对象
        readerBuilder.file("F:\\p1\\投资信息.xls");
        //    指定sheet
        readerBuilder.sheet("投资数据");
        //    自动关闭输入流
        readerBuilder.autoCloseStream(true);
        //    设置Excel文件格式
        readerBuilder.excelType(ExcelTypeEnum.XLS);
        //    注册监听器进行数据的解析
        readerBuilder.registerReadListener(new AnalysisEventListener<Map<Integer, String>>() {

            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                Set<Integer> integers = data.keySet();
                for (Integer key :
                        integers) {
                    System.out.print(key + "-->" + data.get(key) + "   ");
                }
                System.out.println();
              /*  Iterator<Integer> iterator = integers.iterator();
                while (iterator.hasNext()){
                    Integer key = iterator.next();
                    System.out.print(key+":"+data.get(key)+",");
                }
                System.out.println();*/
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                //数据全部读取完毕的结果
                System.out.println("数据读取完毕");
            }
        });
        //    构建读取器
        ExcelReader build = readerBuilder.build();
        //    读取数据
        build.readAll();
        //    读取完毕
        build.finish();
    }

    @Test
    public void simplifyRead() {
        //List<Map<Integer, String>> list =  new LinkedHashMap<Map<Integer, String>, Object>();
        List<B_Bid_Info> list=new ArrayList<>();
        EasyExcel.read("F:\\p1\\投资信息.xls")
                .sheet("投资数据")
                .head(B_Bid_Info.class)//指定要传入解析的类型,要不然会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to com.wjs.entity.B_Bid_Info
                //这里对AnalysisEventListener指定泛型了，不指定的话是Object
                .registerReadListener(new AnalysisEventListener<B_Bid_Info>() {
                    /**
                     *
                     * @param data 读取excel每一行的数据
                     * @param context
                     */
                    @Override
                    public void invoke(B_Bid_Info data, AnalysisContext context) {
                        list.add(data);
                    }

                    /**
                     *
                     * @param context 下面这个方法是完全读取完毕之后的结果
                     */
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        System.out.println("读取完毕");
                    }
                }).doRead();
        //for (Map<Integer, String> data :
        //        list) {
        //    Set<Integer> keySet = data.keySet();
        //    for (Integer key :
        //            keySet) {
        //        System.out.print(key + "-->" + data.get(key) + "   ");
        //    }
        //    System.out.println();
        //}
        for (B_Bid_Info data:
             list) {
            System.out.println(data);
        }
    }
}
