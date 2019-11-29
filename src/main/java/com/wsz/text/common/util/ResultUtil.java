package com.wsz.text.common.util;





import com.wsz.text.common.sysconst.SystemCon;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tuser;

import java.util.List;


public class ResultUtil {
    public static ResultVo exec(boolean istrue, String msg, Object data){
        ResultVo resultVo=new ResultVo();
        if(istrue){
            resultVo.setCode(SystemCon.OK);
        }else {
            resultVo.setCode(SystemCon.ERROR);
        }
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }


    public static <T> PageVo<T> exec(Integer page,int size,long count,List<T> data){
        PageVo pageVo=new PageVo();
        pageVo.setPage(page);
        pageVo.setSize(size);
        pageVo.setCount(count);
        pageVo.setTotalpage((int)(count%size==0?count/size:count/size+1));
        pageVo.setData(data);
        return pageVo;
    }
   /* public static <T> PagePermissionVo<T> exec(int page, int size, long count,boolean edit,boolean del, List<T> data){
        PagePermissionVo<T> pageVo=new PagePermissionVo();
        pageVo.setPage(page);
        pageVo.setSize(size);
        pageVo.setCount(count);
        pageVo.setDel(del);
        pageVo.setEdit(edit);
        pageVo.setTotalpage((int)(count%size==0?count/size:count/size+1));
        pageVo.setData(data);
        return pageVo;
    }*/
}
