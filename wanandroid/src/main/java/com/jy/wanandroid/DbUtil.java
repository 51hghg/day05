package com.jy.wanandroid;

import com.jy.wanandroid.dao.DaoMaster;
import com.jy.wanandroid.dao.DaoSession;
import com.jy.wanandroid.dao.FoodDbBeanDao;

import java.util.List;

public class DbUtil {
    private static DbUtil dbUtil;
    private final FoodDbBeanDao foodDbBeanDao;


    private DbUtil() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getApp(), "stu");
        DaoMaster master = new DaoMaster(openHelper.getReadableDb());
        DaoSession daoSession = master.newSession();
        foodDbBeanDao = daoSession.getFoodDbBeanDao();
    }

    public static DbUtil getDbUtil() {
        if (dbUtil == null) {
            synchronized (DbUtil.class) {
                if (dbUtil == null) {
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    public long insert(FoodDbBean foodDbBean){
        long insert = foodDbBeanDao.insert(foodDbBean);
        return insert;
    }

    public void delete(FoodDbBean foodDbBean){
        foodDbBeanDao.delete(foodDbBean);
    }

    public void update(FoodDbBean foodDbBean){
        foodDbBeanDao.update(foodDbBean);
    }

    public List<FoodDbBean> query(){
        List<FoodDbBean> list = foodDbBeanDao.queryBuilder().list();
        return list;
    }
}
