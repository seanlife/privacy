/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/7/18 17:28 创建
 *
 */
package onem.lyb.utils.schedule;

import com.sun.javafx.binding.StringFormatter;
import onem.lyb.utils.schedule.config.ScheduleMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kshujun on 2016/7/18.Have a nice day!
 *
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 */
public class DefultSchedule extends Thread  {

    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    /**执行频率 单位毫秒 */
    protected long period=1000*60;

    protected Calendar calendar=Calendar.getInstance();

    protected DefultTimeTask defultTimeTask;

    protected ScheduleMethod scheduleMethod=ScheduleMethod.Daily;

    public DefultSchedule() {
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 34);
        calendar.set(Calendar.SECOND, 00);
    }

    @Override
    public void run() {
        System.out.println(MessageFormat.format("配置每日定时任务 任务:{0},模式:{1},间隔时间:{2},执行时间:{3}",
                defultTimeTask, scheduleMethod.message(), PERIOD_DAY, calendar.getTime()));
        try {
            Date date = calendar.getTime(); //第一次执行定时任务的时间
//            if (date.before(new Date())) {
//                date = this.addDay(date, 1);
//            }
            Timer timer = new Timer();
            if (scheduleMethod.equals(ScheduleMethod.Daily))
                timer.schedule(defultTimeTask, date, PERIOD_DAY);
            else
                timer.schedule(defultTimeTask, date, period);
        }catch (Exception ex){
            System.out.println("定时任务出错 请检查");
            ex.printStackTrace();
        }
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public TimerTask getTimerTask() {
        return defultTimeTask;
    }

    public void setTimerTask(DefultTimeTask defultTimeTask) {
        this.defultTimeTask = defultTimeTask;
    }

    public ScheduleMethod getScheduleMethod() {
        return scheduleMethod;
    }

    public void setScheduleMethod(ScheduleMethod scheduleMethod) {
        this.scheduleMethod = scheduleMethod;
    }

    public void setTime(int hour,int min,int sec){
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
    }

    /**
     * 将时间date 添加或者减少num天
     * @param date 时间
     * @param num -1代表前一天，1代表第二天
     * @return
     */
    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
