package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {

	ServerSocket serverSocket=null;
	Socket clientSocket=null;
	BufferedReader inBufferedReader=null;
	PrintStream outPrintStream=null;
	
	
	public Test(int port){
		System.out.println("�������������ڼ����˿�:"+port);
		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("�����˿�"+port+"ʧ��");
		}
		
		try {
			clientSocket=serverSocket.accept();
		} catch (IOException e) {
			System.out.println("����ʧ��");
		}
		
		try {
			inBufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			outPrintStream=new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public String getRequest(){
		String frmClt="1";
		try {
			frmClt=inBufferedReader.readLine();
			System.out.println("server �յ�����"+frmClt);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�޷���ȡ�˿�...");
			System.exit(0);
		}
		return frmClt;
		
	}
	
	
	public void sendResponse(String response){
		try{
			outPrintStream.println(response);
			System.out.println("server ��Ӧ����:"+response);
			outPrintStream.flush();
		}
		catch(Exception e){
			System.out.println("д�˿�ʧ�ܡ�����");
			System.exit(0);
		}
	}
	
	public void close(){
		try {
			inBufferedReader.close();
			outPrintStream.close();
		clientSocket.close();
		serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Test test=new Test(8888);
		while(true){
			test.sendResponse(test.getRequest());
		    test.close();
		}
		}
//		// TODO Auto-generated method stub
//		InetAddress iAddress=null;
//		try {
//			iAddress=InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(iAddress.getHostName());
//		System.out.println(iAddress.getHostAddress());
//		//�������ip��ַ��������
	

}
