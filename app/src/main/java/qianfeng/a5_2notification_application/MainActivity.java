package qianfeng.a5_2notification_application;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE));

    }

    public void createCustomNotification(View view) {

        // 这个builder,只要一调用自身的build()方法，就立刻变成一个Notification对象
        Notification.Builder builder = new Notification.Builder(this);


        // 第一个参数是：应用包名 直接用getPackageName()获取即可
        // 第二个参数是：remoteViews要生成的布局的样式
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.nf_layout);
        remoteViews.setTextViewText(R.id.tv1,"正在下载:");
        remoteViews.setTextViewText(R.id.tv2,"已下载：76%");

        // 在通知上进行点击的方式可以有，PendIntent, 这个PendIntent的唯一使用场景也就是在通知这里，当满足所有的条件后，它才会被点击成功
//        Intent intent3 = new Intent();
//        PendingIntent intent2 = PendingIntent.getActivity(this,1,intent3,PendingIntent.FLAG_ONE_SHOT);//FLAG_ONE_SHOT表示此点击事件有效次数仅为1次
//        builder.setContentIntent(intent2);

        // 第1个参数：是你自定义要显示的进度条
        // 设置自定义的通知的进度条，第4个参数是false，表示不使用模糊进度条
        remoteViews.setProgressBar(R.id.progressBar,120,78,false);
        // setContent(): 这是加载自定义的通知视图的最关键的方法
        builder.setContent(remoteViews);  // 最为关键的方法，没有之一。

        builder.setAutoCancel(true); // 设置点击一次后，就自动取消在通知栏的显示

        builder.setSmallIcon(R.mipmap.ic_launcher); //这是一定要有的，否则通知就不能显示出来

        builder.setTicker("hello"); // 设置通知 在状态栏上一闪而过，显示hello这个文本(仅持续一两秒)


        manager.notify(1,builder.build());// 第一个参数是通知的唯一标志，第二个参数是 通知对象


    }

    public void cancelNotification(View view) { // 取消通知很简单，只需要在通知管理器中，调用cancel方法即可
        manager.cancel(1); // 这个1是上面那个通知(builder.builder() )的唯一标记

    }
}
