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
			 System.out.println("�����������������ڼ���9999�˿�...");
			 while(true)
			 {
				//���ܿͻ��˵�socket����
				 Socket s = ss.accept();
				//���ܿͻ���������Ϣ
				 ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				 
				 User user = (User)ois.readObject();
				//����һ����Ϣ�����ڱ��淢�͸��ͻ��˵���Ϣ
				 CMessage msg = new CMessage();
				 ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				//�жϿͻ��˵Ĳ��������Ƿ�Ϊ����½����
				 if(user.getOperation().equals("login"))
				 {
					 String username = user.getUsername();
					 String password = user.getPassword();
					 if(username.equals("123") && password.equals("123"))
					 {
						 System.out.println("["+username+"]������!");
						 msg.setType(CMessage.SUCCESS);
						 oos.writeObject(msg);
						 oos.flush();
						 //����һ���̣߳��ø��߳���ÿͻ��˱�������  
						 //�˲������
					 }
					 else
					 {
						 msg.setType(CMessage.FAIL);
						 oos.writeObject(msg);
					 }
				 }
				 else if(user.getOperation().equals("register"))
				 {
					//ע�����
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
