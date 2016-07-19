/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/7/18 18:16 创建
 *
 */
package onem.lyb.utils.schedule.config;

import onem.lyb.utils.common.enums.Messageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kshujun on 2016/7/18.Have a nice day!
 *
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 */
public enum ScheduleMethod implements Messageable {
    /** 模式：每日 */
    Daily("daily", "每日定时任务"),

    /** 模式：每日 */
    Times("times", "周期定时任务");

    /** 枚举值 */
    private final String code;

    /** 枚举描述 */
    private final String message;

    /**
     * 构造一个<code>ScheduleMethod</code>枚举对象
     *
     * @param code
     * @param message
     */
    private ScheduleMethod(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return ScheduleMethod
     */
    public static ScheduleMethod getByCode(String code) {
        for (ScheduleMethod _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ScheduleMethod>
     */
    public List<ScheduleMethod> getAllEnum() {
        List<ScheduleMethod> list = new ArrayList<ScheduleMethod>();
        for (ScheduleMethod _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (ScheduleMethod _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 判断给定的枚举，是否在列表中
     *
     * @param values 列表
     *
     * @return
     */
    public boolean isInList (ScheduleMethod... values) {
        for (ScheduleMethod e : values) {
            if (this == e) {
                return true;
            }
        }
        return false;
    }
}
