package com.ftp;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.ftp.FTP.DeleteFileProgressListener;
import com.ftp.FTP.DownLoadProgressListener;
import com.ftp.FTP.UploadProgressListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView();
    }

    private void initView() {
    	
    	//上传功能
    	//new FTP().uploadMultiFile为多文件上传
    	//new FTP().uploadSingleFile为单文件上传
		Button buttonUpload = (Button) findViewById(R.id.button_upload);        
    	buttonUpload.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View v) {

					new Thread(new Runnable() {			
						@Override
						public void run() {
							
		                    // 上传
		                	File file = new File("/mnt/sdcard/ftpTest.docx");
		                    try {
		                    	
		                    	//单文件上传
								new FTP().uploadSingleFile(file, "/fff",new UploadProgressListener(){

									@Override
									public void onUploadProgress(String currentStep,long uploadSize,File file) {
										// TODO Auto-generated method stub
										Log.d(TAG, currentStep);										
										if(currentStep.equals(FTP.FTP_UPLOAD_SUCCESS)){
											Log.d(TAG, "-----shanchuan--successful");
										} else if(currentStep.equals(FTP.FTP_UPLOAD_LOADING)){
											long fize = file.length();
											float num = (float)uploadSize / (float)fize;
											int result = (int)(num * 100);
											Log.d(TAG, "-----shangchuan---"+result + "%");
										}
									}							
								});
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}).start();
                     
            }
        });
    
    	//下载功能
    	Button buttonDown = (Button)findViewById(R.id.button_down);
    	buttonDown.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				new Thread(new Runnable() {			
					@Override
					public void run() {
						
	                    // 下载
	                    try {
	                    	
	                    	//单文件下载
	                    	new FTP().downloadSingleFile("/web/FTP.png","/mnt/sdcard/download/","FTP.png",new DownLoadProgressListener(){

								@Override
								public void onDownLoadProgress(String currentStep, long downProcess, File file) {
									Log.d(TAG, currentStep);										
									if(currentStep.equals(FTP.FTP_DOWN_SUCCESS)){
										Log.d(TAG, "-----xiazai--successful");
									} else if(currentStep.equals(FTP.FTP_DOWN_LOADING)){
										Log.d(TAG, "-----xiazai---"+downProcess + "%");
									}
								}
	                    		
	                    	});	                    	
                    	
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
				
			}
		});
    
    	//删除功能
    	Button buttonDelete = (Button)findViewById(R.id.button_delete);
    	buttonDelete.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				new Thread(new Runnable() {			
					@Override
					public void run() {
						
	                    // 删除
	                    try {

	                    	new FTP().deleteSingleFile("/fff/ftpTest.docx",new DeleteFileProgressListener(){

								@Override
								public void onDeleteProgress(String currentStep) {
									Log.d(TAG, currentStep);										
									if(currentStep.equals(FTP.FTP_DELETEFILE_SUCCESS)){
										Log.d(TAG, "-----shanchu--success");
									} else if(currentStep.equals(FTP.FTP_DELETEFILE_FAIL)){
										Log.d(TAG, "-----shanchu--fail");
									}
								}
	                    		
	                    	});	                    	
                    	
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
				
			}
		});
    
    }
}
