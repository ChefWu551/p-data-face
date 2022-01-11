package com.yuefeng.handle;

import com.yuefeng.model.DataFaceConfig;
import org.junit.Test;

public class InterfaceConfigTest {

    @Test
    public void getTemplatesListTest() {
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
        DataFaceConfig dfc = InterfaceConfig.getTemplates(pathTemplate);
        System.out.println(dfc);
    }

    @Test
    public void getTemplatesXYRayTest() {
        String pathTemplate = "title: 物料采购价格对比\n" +
                "subTitle: 以去年采购均价为基础值计算比例；市场价为网络价格乘以税率损耗等费用折算的比例\n" +
                "axis:\n" +
                "  xAxis:\n" +
                "    - type: category\n" +
                "      name: 物料\n" +
                "      id: 7\n" +
                "      data:\n" +
                "        handler: link\n" +
                "        params:\n" +
                "          link: 1\n" +
                "      result:\n" +
                "        handler: select_col\n" +
                "        params:\n" +
                "          col: 0\n" +
                "  yAxis:\n" +
                "    - type: value\n" +
                "      name: 金额\n" +
                "    - type: value\n" +
                "      name: 数量\n" +
                "  series:\n" +
                "    - name: 采购均价\n" +
                "      type: bar\n" +
                "      yAxisIndex: 0\n" +
                "      id: 1\n" +
                "      data:\n" +
                "        handler: sql\n" +
                "        params:\n" +
                "          sql: select current_year.material_name as name,\n" +
                "      result:\n" +
                "        handler: map\n" +
                "        params:\n" +
                "          cols: [0, 1, 4, 5]";
        DataFaceConfig dfc = InterfaceConfig.getTemplates(pathTemplate);
        System.out.println(dfc);
    }
}
