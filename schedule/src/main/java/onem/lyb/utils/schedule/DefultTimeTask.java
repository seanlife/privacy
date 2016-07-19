/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/7/19 10:22 创建
 *
 */
package onem.lyb.utils.schedule;

import java.util.TimerTask;

/**
 * Created by Kshujun on 2016/7/19.Have a nice day!
 *
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 */
public class DefultTimeTask  extends TimerTask {

    @Override
    public void run() {
        System.out.println("默认定时任务 ");
    }


}
