package entity;

import java.io.Serializable;

public class CMessage implements Serializable {
	public static final String SUCCESS="1";//�����Ƿ�ɹ�
	public static final String FAIL="2";//����ʧ��
	public String type;
	public String getType()//��Ϣ����
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
}
