package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketTestActivity extends AppCompatActivity {

    private EditText editText;
    private Button putButton;
    private Button postButton;
    private TextView content;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_test);
        initView();
        initListener();
    }

    private void initView(){
        editText = findViewById(R.id.edit_text);
        putButton = findViewById(R.id.get_button);
        postButton = findViewById(R.id.post_button);
        content = findViewById(R.id.content);
    }

    private void initListener(){
        executorService = Executors.newCachedThreadPool();
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        Socket socket;
                        try {
                            socket = new Socket("192.168.1.4",1990);
                            socket.setSoTimeout(10000);
                            final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            //InputStream inputStream = socket.getInputStream();
                            OutputStream outputStream = socket.getOutputStream();
                            String text = editText.getText().toString();
                            byte[] b = text.getBytes();
                            outputStream.write(b);
                            outputStream.flush();
                            Thread.sleep(2000);
                            final String result = br.readLine();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    content.setText(result);
                                }
                            });

                            br.close();
                            outputStream.close();
                            socket.close();
                        }catch (UnknownHostException e){
                            e.printStackTrace();
                            Log.d("dingyl","UnknownHostException");
                        }catch (IOException e){
                            e.printStackTrace();
                            Log.d("dingyl","IOException");
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
                //new MyThread().start();
            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
