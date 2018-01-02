package com.xiaozhu.library.db;

import android.content.Context;

import com.xiaozhu.library.R;
import com.xiaozhu.library.app.BaseApplication;
import com.xiaozhu.library.entity.DownloadEntity;
import com.xiaozhu.ormlite.DatabaseManagerWrapper;

import java.io.InputStream;
import java.util.List;


/**
 * @说明 数据库管理
 * @作者 LY
 * @时间 2017/10/20 11:17
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class DatabaseManager extends DatabaseManagerWrapper {
    private static volatile DatabaseManager instance;
    private Context mContext;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        DatabaseManager inst = instance;
        if (inst == null) {
            synchronized (DatabaseManager.class) {
                inst = instance;
                if (inst == null) {
                    inst = new DatabaseManager();
                    instance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * 初始化数据库
     */
    public void init(Context context) {
        this.mContext = context;
        super.init(context, context.getResources().getString(R.string.database_name), context.getResources().getInteger(R.integer.database_version));
    }

    @Override
    protected void registerTable() {
        try {
            String[] tables = mContext.getResources().getStringArray(R.array.tables);
            if (tables != null && tables.length > 0) {
                for (int i = 0; i < tables.length; i++) {
                    addTable(Class.forName(tables[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
