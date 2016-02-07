package ord.B_boy.NewSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
	private static Integer index = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("��ӭ����ѧ������ϵͳ!");
		Scanner scanner = new Scanner(System.in);
		ArrayList<Student> list = new ArrayList<>();
		list = outputList(list);  //���ļ��ж�ȡ��Ϣ��List��
		mainMenu(list,scanner);
		
	}

	public static void mainMenu(ArrayList<Student> list,Scanner scanner) throws IOException {
		System.out.println("����������Ҫ��������ţ�1.��\t2.ɾ\t3.��\t4.��\t5.��ʾ\t6.����");
		int digi = scanner.nextInt();  
		switch(digi){
		case 1:
			Add(list,scanner);
			break;
		case 2:
			Delete(list,scanner);
			break;
		case 3:
			modifyMenu(list,scanner);
			break;
		case 4:
			if(!list.isEmpty()){
				Seed(list,scanner);
			}else{
				System.out.println("�б�Ϊ�գ��������");
				Add(list, scanner);
			}
			
			break;
		case 5:
			Showin(list,scanner);
			break;
		case 6:
			inputfile(list);
			System.exit(0);
		default:
			System.out.println("��������ȷ�����!");
			mainMenu(list,scanner);
		}
	}
	
	public static void Add(ArrayList<Student> list,Scanner scanner) throws IOException {
		Student std = new Student();
		System.out.println("����������");
		String name = scanner.next();
		name = isRegulerInputString(name, scanner);  //���ڼ�������Ƿ�淶
		std.setName(name);
		
		System.out.println("�������Ա�");
		String sex = scanner.next();
		sex = isRegulerInputString(sex, scanner);	//���ڼ�������Ƿ�淶
		std.setSex(sex);
		
		System.out.println("����������");
		Integer age = scanner.nextInt();
		age = isRegulerInputNumber(age, scanner);	//���ڼ�������Ƿ�淶
		std.setAge(age);
		
		System.out.println("������ѧ��");
		Integer num = scanner.nextInt();
		num = isRegulerInputNumber(num, scanner);	//���ڼ�������Ƿ�淶
		
		if(!list.isEmpty()){						//������ڼ��ѧ���Ƿ��ظ�����Ϊ���������Ϊ����ֱ�����
			for(int i=0;i<list.size();i++){
			while(list.get(i).getNum() == num){
				System.out.println("���ѧ���Դ���");
				num = hasExistNum(scanner);
				if(num.equals(000)){
					mainMenu(list, scanner);
				}
			}
		  }
			std.setNum(num);
		}else if(list.isEmpty()){
			std.setNum(num);
			System.out.println("pppp");
		}
		
		if(!isExist(list, std)){				//������ѧ����Ϣ�Ƿ��Ѿ�����
			list.add(std);
		}else{
			System.out.println("���ѧ����Ϣ�Ѿ����ڣ����������롣");
			Add(list, scanner);
		}
		Continues("Add",list,scanner);			//�ж��Ƿ���������ķ���
	}
	
	//ɾ������
	public static void Delete(ArrayList<Student> list,Scanner scanner) throws IOException {
		if(!list.isEmpty()){
			index = Seed(list, scanner);		//����Ѱ���������Ϣ��������list�ж�Ӧ��index
			list.remove(list.get(index));						//��Ūһ������ʾ��Ϣ��ȷ��ɾ��
			if(list.remove(list.get(index))){
				System.out.println("ɾ������Ա��ϸ��Ϣ"+list.get(index));
			}
			Continues("Delete", list, scanner);
		
		}else{
			System.out.println("�б�Ϊ�������");
			mainMenu(list, scanner);
		}
	}
	
	//�޸ķ���
	public static void modifyMenu(ArrayList<Student> list,Scanner scanner) throws IOException {
		if(!list.isEmpty()){
			index = Seed(list, scanner);
			System.out.println("��������Ҫ�޸ĵ���Ϣ��1.����\t2.�Ա�\t3.����\t4.ѧ��");
			int dig = scanner.nextInt();
			switch(dig){
			case 1:
				System.out.println("������������");
				String name = scanner.next();
				name = isRegulerInputString(name, scanner);
				list.get(index).setName(name);
				break;
			case 2:
				System.out.println("�������Ա�");
				String sex = scanner.next();
				sex = isRegulerInputString(sex, scanner);
				list.get(index).setName(sex);
				break;
			case 3:
				System.out.println("���������䣺");
				Integer age = scanner.nextInt();
				age = isRegulerInputNumber(age, scanner);
				list.get(index).setAge(age);
				break;
			case 4:
				System.out.println("������ѧ��");
				Integer number = scanner.nextInt();
				number = isRegulerInputNumber(number, scanner);
				System.out.println(list.size());
				for(int i=0;i<list.size();i++){
					while(number.equals(list.get(i).getNum())){
						System.out.println("���ѧ���Դ���");
						number = hasExistNum(scanner);
						if(number.equals(000)){
							mainMenu(list, scanner);
							break;
						}
						if(!(number.equals(list.get(i).getNum()))){
							list.get(i).setNum(number);
							break;
						}
					}
				}
				break;
			}
			Continues("Modify", list, scanner);
			
		}else{
			System.out.println("�б�Ϊ�գ��������");
			Add(list, scanner);		
		}
	}
	
	//�ظ����뷽��
	public static Integer hasExistNum(Scanner scanner){		//���������Ǹ�����
		System.out.println("����������ѧ��,���˳������˵���ţ�000��");
		Integer num = scanner.nextInt();
		return num;
	}

	//��ʾ����
	public static void Showin(ArrayList<Student> list,Scanner scanner) throws IOException {
		if(!list.isEmpty()){
			for(Student li : list){
				System.out.println(li.toString());
			}
			Continues("Showin", list, scanner);
		}else{
			System.out.println("�б�Ϊ�գ��������");
			Add(list, scanner);
		}
	}

	//��ѯ���������ز�ѯ��Ϣ��list�е�index
	public static int Seed(ArrayList<Student> list,Scanner scanner) throws IOException {
		System.out.println("��������Ҫ�޸���Ա����Ϣ��1.������ѯ\t2.ѧ�Ų�ѯ\t3�����ص����˵�");
		int num = scanner.nextInt();
		switch(num){
		case 1:
			System.out.println("������������");
			String name = scanner.next();
			name = isRegulerInputString(name, scanner);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getName().equals(name)){
					System.out.println(list.get(i));
					index = i;
				}
			}
			break;
		case 2:
			System.out.println("������ѧ�ţ�");
			Integer digi = scanner.nextInt();
			digi = isRegulerInputNumber(digi, scanner);
			for(int i=0;i<list.size();i++){
				if(digi.equals(list.get(i).getNum())){
					System.out.println(list.get(i));
					index = i;
				}
			}
		case 3:
			mainMenu(list, scanner);
			break;
		default:
			System.out.println("��������ȷ�����,�������������롣");
			Seed(list, scanner);
			
		}
		return index;
	}
	
	//�ж��б����Ƿ��Ѿ����ڴ���Ϣ
	public static boolean isExist(ArrayList<Student> list,Student std){
		boolean flag = false;
			if(list.contains(std)){
				System.out.println("�б������д�Ԫ��");
				flag = true;
			}
			for(int i=0;i<list.size();i++){
				if(list.get(i).getNum()==std.getNum()){
					System.out.println(list.get(i).getNum()+":"+std.getNum());
					System.out.println("��Ԫ�ص�ѧ���Դ���");
					flag = true;
					break;
			}
		}
		return flag;
	}
	
	//�ж��Ƿ���������ķ���
	public static void Continues(String c,ArrayList<Student> list,Scanner scanner) throws IOException {
		System.out.println("���ʻ�Ҫ����������Y(����)/N(�������˵�)");
		String str = scanner.next();
		if(str.matches("[Yy]")){
			switch(c){
			case "Add":
				Add(list, scanner);
				break;
			case "Modify":
				modifyMenu(list, scanner);
				break;
			case "Delete":
				Delete(list, scanner);
				break;
			case "Seed":
				Seed(list, scanner);
			case "Showin":
				mainMenu(list, scanner);
			}
		}else if(str.matches("[Nn]")){
			mainMenu(list, scanner);
		}
	}

	//list��Ϣת�����ı����淽��
	public static void inputfile(List<Student> list)throws IOException{
		try(	
				BufferedWriter bufw = new BufferedWriter(new FileWriter("TheList.txt"))
				){
			for(Student li : list){
				bufw.write(li.toString());
				bufw.newLine();
			}
		}
	}
	
	//�ı�װ�ó�list��Ϣ�ķ���
	public static ArrayList<Student> outputList(ArrayList<Student> list) throws IOException{
		try( 	BufferedReader bufr = new BufferedReader(new FileReader("TheList.txt"))
				){
			String len = null;
			while((len=bufr.readLine())!=null){
				if(!(len.equals(null))){
				String[] spl = len.split(":");
				Integer n = new Integer(spl[2]);
				Student s = new Student();
				
				s.setName(spl[0]);
				s.setSex(spl[1]);
				s.setAge(n);
				n = new Integer(spl[3]);
				s.setNum(n);
				
				list.add(s);}
			}
			return list;
		}
	}
	
	//�淶�����ַ�������
	public static String isRegulerInputString(String str,Scanner scanner){
			while(!str.matches("\\w+")){
				System.out.println("���������룬��ʽ����ֻ��������(0~9)����ĸ(a~z)���»���(_)");
				str = scanner.next();
			}
			return str;

	}

	//�淶�������ַ���
	public static Integer isRegulerInputNumber(Integer age, Scanner scanner) {
		String a = age.toString();
		while(!a.matches("\\d{1,3}")){
			System.out.println("���������룬��ʽ�������3λ��ֻ��������(0~999)");
			a = scanner.next();
		}
		Integer ag = new Integer(a);
		return ag;		
	}
}
