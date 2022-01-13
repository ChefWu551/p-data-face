package com.yuefeng.handle;

import com.yuefeng.service.impl.DataFaceServiceImpl;
import org.junit.Test;

public class HandlerFactoryTest {

    @Test
    public void getFaceConfigTest() {
        String pathTemplate = "title: 零售销售基本情况\n" +
        "list:\n" +
        "  id: 1\n" +
        "  data:\n" +
        "    handler: sql\n" +
        "    params:\n" +
        "        sql: select concat(substr(now(), 1,4),'年销售设备数量(台)') as name, sum(iquantity) as value\n" +
        "          from metrics_finance_so_count_ext\n" +
        "          where cinvcode like '01%' and cdepname like '%办事处' and cstname = '整机销售'\n" +
        "              and ddate like concat(substr(now(), 1,4), '%')\n" +
        "  result:\n" +
        "    handler: map";

        DataFaceServiceImpl hf = new DataFaceServiceImpl();
        hf.getFaceConfig(pathTemplate);
    }
}
