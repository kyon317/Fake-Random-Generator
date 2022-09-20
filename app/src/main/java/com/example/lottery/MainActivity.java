package com.example.lottery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int range = 50;
    private TextView textView;
    private EditText input;
    private boolean isRandom = true;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void start(View view) {
        TextView tv=findViewById(R.id.textView);//就是通过文本框的id来找到文本框
        TextView cheat = findViewById(R.id.textView3);
        tv.setVisibility(View.VISIBLE);
        cheat.setVisibility(View.INVISIBLE);
        //将view转换为Button
        Button btn=findViewById(R.id.start_button);//多态之中必须要转到对应的类型
        //获取当前标题
        String title=btn.getText().toString();//就是得到按钮的文本得到然后转换为字符串
        //判断按钮的标题
        if(title.equals("开始")){//如果当前的字符串为“开始抽奖"就执行下面的语句
            //设置为暂停
            btn.setText("停止");
            //创建一个定时器
            timer =new Timer();//此时创建一个定时器，就是创建一个对象来继承Timer的类，来调用其中的方法
            //每隔一段时间执行一次任务
            timer.schedule(new TimerTask() {//此方法就是定时器的设置，调用了Timer类之中的方法
                @Override
                public void run() {
                    produceOnePeople();//表示所需执行的任务，即每隔20毫秒就会产生一个随机的
                    // 人名显示到文本框上
                }
            },0,20);//前面一个表示需要延迟的时间，后面一个表示每隔多少毫秒都就做一件事情
            //关闭定时器
        }else{
            //设置为暂停
            btn.setText("开始");
            timer.cancel();
            //关闭定时器，此时就会停止跳动，实现抽奖完成的环节
            if (!isRandom){
                tv=findViewById(R.id.textView);//就是通过文本框的id来找到文本框
                cheat = findViewById(R.id.textView3);
                tv.setVisibility(View.INVISIBLE);
                cheat.setVisibility(View.VISIBLE);
//                tv.setText("42");
            }

        }

    }
    //产生一个随机数的人名 显示到文本框上
    public void produceOnePeople(){
        input = findViewById(R.id.number_input);
        if (!input.getText().toString().isEmpty()){
            String input_txt = input.getText().toString();
            range = Integer.parseInt(input_txt);
        }
        //产生一个随机数
        Random random =new Random();
        int result = random.nextInt((range-1)+1)+1;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //将名字显示到文本框上
                TextView tv=findViewById(R.id.textView);
                String res = String.valueOf(result);
                tv.setText(res);
            }
        });
    }
    public void Randomswitcher(View view){
        isRandom = !isRandom;
    }
    public void reset(View view){
        TextView tv=findViewById(R.id.textView);//就是通过文本框的id来找到文本框
        TextView cheat = findViewById(R.id.textView3);
        tv.setVisibility(View.VISIBLE);
        cheat.setVisibility(View.INVISIBLE);
        tv.setText("");
        isRandom = true;
        ConstraintLayout layout = findViewById(R.id.main_layout);
        layout.setClickable(false);
    }
}