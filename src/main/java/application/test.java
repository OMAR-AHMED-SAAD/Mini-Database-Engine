package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

import exceptions.DBAppException;

public class test {
	public static void main(String[] args) throws DBAppException {
		DBApp db = new DBApp();
//		String input0 = "inserT into Student VALUES ('omar',20.2);";
//		String input="CREATE TABLE Club\n"
//				+ "( id INT(10),name VARCHAR(20),location VARCHAR(20) ,PRIMARY KEY(id));";
//		String input2="delete from club where name='madrid' and location='zamalek';";
//		String input3="update club Set name='madrid' where insert= '2022-02-13' ;";

//		StringBuffer create = new StringBuffer(
//				"CREATE TaBLE Club( id INT(10),name VARCHAR(20),location VARCHAR(20) ,PRIMARY KEY(id));");
//		db.parseSQL(create);
//
//		StringBuffer insert1 = new StringBuffer("Insert into Club Values(1,'Real Madrid','Madrid');");
//		StringBuffer insert2 = new StringBuffer("Insert into Club Values(2,'Liverpool','Liverpool')");
//		StringBuffer insert3 = new StringBuffer("Insert into Club Values(3,'Arsenal','London')");
//		StringBuffer insert4 = new StringBuffer("Insert into Club Values(4,'AC Milan','Milan')");
//		StringBuffer insert5 = new StringBuffer("Insert into Club Values(5,'Napoli','Napoli')");
//		db.parseSQL(insert1);
//		db.parseSQL(insert2);
//		db.parseSQL(insert3);
//		db.parseSQL(insert4);
//		db.parseSQL(insert5);
		
		
		
//		StringBuffer delete1=new StringBuffer("delete From Club");
//		StringBuffer delete2=new StringBuffer("delete From Club where name='Arsenal'");
//		StringBuffer delete3=new StringBuffer("delete From Club where location='Milan'");
//		StringBuffer delete4=new StringBuffer("delete From Club where id=1");
//		db.parseSQL(delete1);
//		db.parseSQL(delete2);
//		db.parseSQL(delete3);
//		db.parseSQL(delete4);
	
//		
//		StringBuffer update1=new StringBuffer("update Club Set location='London' Where id=2");
//		StringBuffer update2=new StringBuffer("update Club Set location='London' Where id=4");
//		db.parseSQL(update1);
//		db.parseSQL(update2);
		
		
		//StringBuffer delete5=new StringBuffer("delete From Club where location='London' AND id=2");
		//StringBuffer delete6=new StringBuffer("delete From Club");
		//db.parseSQL(delete5);
		//db.parseSQL(delete6);
		String [] strarrColName=new String [] {"AAAAA","FGGF","FFFGFG"};
		
		for (int i=0;i<strarrColName.length;i++)
			strarrColName[i]=strarrColName[i].toLowerCase();
		System.out.println(strarrColName[2]);
		
		
		Vector<Hashtable<String, Object>> lol=new Vector<>();
		Hashtable<String, Object> one=new Hashtable<>();
		one.put("lol", 2);
		one.put("lol", "22");
		lol.add(one);
		Hashtable<String, Object> two=new Hashtable<>();
		two.put("lol", 2);
		two.put("lol", "22");
		
		System.out.println(two.remove("jkhk"));
		//System.out.println(lol.contains(two));
		String [] ex= {"1","2"};
		ArrayList<String> a=new ArrayList<>(Arrays.asList(ex));
//		a.add("kkf");
//		a.add("kk-tjoif");
//		a.add("kktjrtf");
//		a.add(0, "Fkmlkf");
		//System.out.println(a.get(0));
//		Object o="kdjkl";
//		String x=o.toString();
//		System.out.println(o.getClass());
		//System.out.println(a);
	}
}
