package com.eoms;

import com.eoms.base.BaseControllerTest;
import com.eoms.base.CommonUtils;
import com.eoms.base.HttpUtils;
import com.eoms.base.IBaseControllerTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.eoms.base.CommonUtils.getHeadMap;

public class UserControllerTest extends BaseControllerTest implements IBaseControllerTest {

    private String url = baseUrl + "user/add";

    @Override
    @Test
    public void add() {
        Map<String,String> parameterMap = new HashMap<String, String>();
        parameterMap.put("name","asher");
        parameterMap.put("password","q24348590");
        String res = HttpUtils.doPost(url,parameterMap,HttpUtils.REQUEST_ENCODING,getHeadMap());
        CommonUtils.printJSON(url,res);
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void query() {

    }
}
