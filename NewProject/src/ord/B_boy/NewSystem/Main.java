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
		System.out.println("欢迎进入学生管理系统!");
		Scanner scanner = new Scanner(System.in);
		ArrayList<Student> list = new ArrayList<>();
		list = outputList(list);  //从文件中读取信息到List中
		mainMenu(list,scanner);
		
	}

	public static void mainMenu(ArrayList<Student> list,Scanner scanner) throws IOException {
		System.out.println("请输入你需要操作的序号：1.增\t2.删\t3.改\t4.查\t5.显示\t6.结束");
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
				System.out.println("列表为空，请先添加");
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
			System.out.println("请输入正确的序号!");
			mainMenu(list,scanner);
		}
	}
	
	public static void Add(ArrayList<Student> list,Scanner scanner) throws IOException {
		Student std = new Student();
		System.out.println("请输入姓名");
		String name = scanner.next();
		name = isRegulerInputString(name, scanner);  //用于检测输入是否规范
		std.setName(name);
		
		System.out.println("请输入性别");
		String sex = scanner.next();
		sex = isRegulerInputString(sex, scanner);	//用于检测输入是否规范
		std.setSex(sex);
		
		System.out.println("请输入年龄");
		Integer age = scanner.nextInt();
		age = isRegulerInputNumber(age, scanner);	//用于检测输入是否规范
		std.setAge(age);
		
		System.out.println("请输入学号");
		Integer num = scanner.nextInt();
		num = isRegulerInputNumber(num, scanner);	//用于检测输入是否规范
		
		if(!list.isEmpty()){						//这个用于检测学号是否重复，不为空则遍历，为空则直接添加
			for(int i=0;i<list.size();i++){
			while(list.get(i).getNum() == num){
				System.out.println("这个学号以存在");
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
		
		if(!isExist(list, std)){				//检测这个学生信息是否已经存在
			list.add(std);
		}else{
			System.out.println("这个学生信息已经存在，请重新输入。");
			Add(list, scanner);
		}
		Continues("Add",list,scanner);			//判断是否继续操作的方法
	}
	
	//删除方法
	public static void Delete(ArrayList<Student> list,Scanner scanner) throws IOException {
		if(!list.isEmpty()){
			index = Seed(list, scanner);		//用于寻找所需的信息，返回在list中对应的index
			list.remove(list.get(index));						//想弄一个先显示信息再确定删除
			if(list.remove(list.get(index))){
				System.out.println("删除的人员详细信息"+list.get(index));
			}
			Continues("Delete", list, scanner);
		
		}else{
			System.out.println("列表为空请添加");
			mainMenu(list, scanner);
		}
	}
	
	//修改方法
	public static void modifyMenu(ArrayList<Student> list,Scanner scanner) throws IOException {
		if(!list.isEmpty()){
			index = Seed(list, scanner);
			System.out.println("请输入你要修改的信息：1.姓名\t2.性别\t3.年龄\t4.学号");
			int dig = scanner.nextInt();
			switch(dig){
			case 1:
				System.out.println("请输入姓名：");
				String name = scanner.next();
				name = isRegulerInputString(name, scanner);
				list.get(index).setName(name);
				break;
			case 2:
				System.out.println("请输入性别：");
				String sex = scanner.next();
				sex = isRegulerInputString(sex, scanner);
				list.get(index).setName(sex);
				break;
			case 3:
				System.out.println("请输入年龄：");
				Integer age = scanner.nextInt();
				age = isRegulerInputNumber(age, scanner);
				list.get(index).setAge(age);
				break;
			case 4:
				System.out.println("请输入学号");
				Integer number = scanner.nextInt();
				number = isRegulerInputNumber(number, scanner);
				System.out.println(list.size());
				for(int i=0;i<list.size();i++){
					while(number.equals(list.get(i).getNum())){
						System.out.println("这个学号以存在");
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
			System.out.println("列表为空，请先添加");
			Add(list, scanner);		
		}
	}
	
	//重复输入方法
	public static Integer hasExistNum(Scanner scanner){		//用于上面那个方法
		System.out.println("请重新输入学号,（退出到主菜单序号：000）");
		Integer num = scanner.nextInt();
		return num;
	}

	//显示方法
	public static void Showin(ArrayList<Student> list,Scanner scanner) throws IOException {
		if(!list.isEmpty()){
			for(Student li : list){
				System.out.println(li.toString());
			}
			Continues("Showin", list, scanner);
		}else{
			System.out.println("列表为空，请先添加");
			Add(list, scanner);
		}
	}

	//查询方法，返回查询信息再list中的index
	public static int Seed(ArrayList<Student> list,Scanner scanner) throws IOException {
		System.out.println("请输入你要修改人员的信息：1.姓名查询\t2.学号查询\t3结束回到主菜单");
		int num = scanner.nextInt();
		switch(num){
		case 1:
			System.out.println("请输入姓名：");
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
			System.out.println("请输入学号：");
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
			System.out.println("请输入正确是序号,现在请重新输入。");
			Seed(list, scanner);
			
		}
		return index;
	}
	
	//判断列表中是否已经存在此信息
	public static boolean isExist(ArrayList<Student> list,Student std){
		boolean flag = false;
			if(list.contains(std)){
				System.out.println("列表中已有此元素");
				flag = true;
			}
			for(int i=0;i<list.size();i++){
				if(list.get(i).getNum()==std.getNum()){
					System.out.println(list.get(i).getNum()+":"+std.getNum());
					System.out.println("该元素的学号以存在");
					flag = true;
					break;
			}
		}
		return flag;
	}
	
	//判断是否继续操作的方法
	public static void Continues(String c,ArrayList<Student> list,Scanner scanner) throws IOException {
		System.out.println("请问还要继续操作吗？Y(继续)/N(返回主菜单)");
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

	//list信息转换成文本保存方法
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
	
	//文本装好陈list信息的方法
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
	
	//规范输入字符串方法
	public static String isRegulerInputString(String str,Scanner scanner){
			while(!str.matches("\\w+")){
				System.out.println("请重新输入，格式错误，只能是数字(0~9)，字母(a~z)和下划线(_)");
				str = scanner.next();
			}
			return str;

	}

	//规范输入数字方法
	public static Integer isRegulerInputNumber(Integer age, Scanner scanner) {
		String a = age.toString();
		while(!a.matches("\\d{1,3}")){
			System.out.println("请重新输入，格式错误，最多3位且只能是数字(0~999)");
			a = scanner.next();
		}
		Integer ag = new Integer(a);
		return ag;		
	}
}
