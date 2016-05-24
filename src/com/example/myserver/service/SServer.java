package com.example.myserver.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import entity.CMessage;
import entity.User;

public class SServer {
	public SServer()
	{
		ServerSocket ss = null;
		try
		{
			 ss = new ServerSocket(9987);
			 System.out.println("服务器已启动，正在监听9999端口...");
			 while(true)
			 {
				//接受客户端的socket连接
				 Socket s = ss.accept();
				//接受客户发来的信息
				 ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				 
				 User user = (User)ois.readObject();
				//创建一个信息，用于保存发送给客户端的信息
				 CMessage msg = new CMessage();
				 ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				//判断客户端的操作类型是否为：登陆操作
				 if(user.getOperation().equals("login"))
				 {
					 String username = user.getUsername();
					 String password = user.getPassword();
					 if(username.equals("123") && password.equals("123"))
					 {
						 System.out.println("["+username+"]上线了!");
						 msg.setType(CMessage.SUCCESS);
						 oos.writeObject(msg);
						 oos.flush();
						 //单开一个线程，让该线程与该客户端保持连接  
						 //此步骤待定
					 }
					 else
					 {
						 msg.setType(CMessage.FAIL);
						 oos.writeObject(msg);
					 }
				 }
				 else if(user.getOperation().equals("register"))
				 {
					//注册待定
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
