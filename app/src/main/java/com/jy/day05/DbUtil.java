package com.jy.day05;

import com.jy.day05.dao.DaoMaster;
import com.jy.day05.dao.DaoSession;
import com.jy.day05.dao.StudentBeanDao;

import java.util.List;

public class DbUtil {
    private static DbUtil dbUtil;
    private final StudentBeanDao studentBeanDao;

    public DbUtil() {
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getApp(), "food");
        DaoMaster master = new DaoMaster(openHelper.getReadableDb());
        DaoSession daoSession = master.newSession();
        studentBeanDao = daoSession.getStudentBeanDao();
    }

    public static DbUtil getDbUtil() {
        if (dbUtil == null) {
            synchronized (DbUtil.class){
                if (dbUtil == null) {
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    public void insert(StudentBean studentBean){
        studentBeanDao.insert(studentBean);
    }

    public void delete(StudentBean studentBean){
        studentBeanDao.delete(studentBean);
    }
    public void update(StudentBean studentBean){
        studentBeanDao.update(studentBean);
    }
    public List<StudentBean> query(StudentBean studentBean){
        List<StudentBean> list = studentBeanDao.queryBuilder().list();
        return list;
    }
}
