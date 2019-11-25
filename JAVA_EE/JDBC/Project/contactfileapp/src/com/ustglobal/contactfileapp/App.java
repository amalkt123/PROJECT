package com.ustglobal.contactfileapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.ustglobal.contactfileapp.dao.ContactFileDao;
import com.ustglobal.contactfileapp.dto.ContactBean;
import com.ustglobal.contactfileapp.util.ContactManager;

public class App {
	static Scanner sc=new Scanner(System.in);
	static boolean mainOptionConditon=true;
	static boolean caseConditon2=true;
	//static boolean caseConditon3=true;
	public static void main(String[] args) {
		while(mainOptionConditon) {
			System.out.println("-----------------------------select option--------------------------------");
			System.out.println("Press 1 To Show All Conatcts");
			System.out.println("Press 2 To Search For Contact (using Name)");
			System.out.println("Press 3 To Operate On Contacts");
			System.out.println("---------------------------------------------------------------------------");
			int option=sc.nextInt();
			switch (option) {
			case 1:
				ContactFileDao daoCase1=ContactManager.getContactFileImpl();
				List<String> names=daoCase1.selectAllContact();
				if(names!=null) {
					for(String name:names) {
						System.out.println(name);
					}
				}
				else {
					System.out.println("No Contact Is Present");
				}

				break;
			case 2:
				
					System.out.println("Enter name");
					String name=sc.next();
					ContactFileDao daoCase2=ContactManager.getContactFileImpl();
					ContactBean beanContact=daoCase2.searchContact(name);
					if(beanContact!=null) {
						System.out.println("name : "+beanContact.getName());
						System.out.println("number : "+beanContact.getNumber());
						System.out.println("grounp : "+beanContact.getGroup());
						System.out.println("---------------------------------------------------------------------");
						while(caseConditon2) {
						System.out.println("Press 1 To Call");
						System.out.println("Press 2 To Message");
						System.out.println("Pree 3 to Go back To Main Menu");

						int caseOption2=sc.nextInt();
						switch (caseOption2) {
						case 1:
							System.out.println("calling "+beanContact.getName()+" ................!");
							boolean callStop=true;
							System.out.println("0: end call ");
							while(callStop) {
								int balance= sc.nextInt();
								if(balance==0) {
									callStop=false;
								}
							}
							System.out.println("Call Ended");



							break;
						case 2:
							System.out.println("------------------Message-------------------------");
							System.out.println("enter message ");
							String msg= sc.next();
							System.out.println("----------------sending Message---to--"+beanContact.getName()+"------------------------------------->");


							break;
						case 3:
							caseConditon2=false;
							break;


						default:
							System.out.println("Invalid Option");
							break;
						}
					}
					}
					else {
						System.out.println("No Matching Contact Found");
					
				}
				break;
			case 3:

				System.out.println("Press 1 To Add Contact");
				System.out.println("Press 2 To Delete Contact");
				System.out.println("Press 3 To Edit Contact");
				int caseOption3=sc.nextInt();
				switch (caseOption3) {
				case 1:
					ContactBean bean=new ContactBean();
					System.out.println("enter name");
					bean.setName(sc.next());
					System.out.println("enter number");
					bean.setNumber(sc.nextInt());
					System.out.println("enter group");
					bean.setGroup(sc.next());
					ContactFileDao daoCase31=ContactManager.getContactFileImpl();
					int addResult=daoCase31.addContact(bean);
					if(addResult>0) {
						System.out.println("sucess fully add "+addResult+" contact");
					}

					break;
				case 2: 
					
					System.out.println("enter the Name");
					String name1=sc.next();
					ContactFileDao daoCaseget=ContactManager.getContactFileImpl();
					int deleteResult=daoCaseget.deleteContact(name1);
					if(deleteResult>0) {
						System.out.println(deleteResult+" Contact Is Deleted");
					}
					else {
						System.out.println("Invalid contact");
					}

					break;

				case 3:


					ContactBean beanUpdate=new ContactBean();
					System.out.println("enter name");
					beanUpdate.setName(sc.next());
					System.out.println("enter number");
					beanUpdate.setNumber(sc.nextInt());
					System.out.println("enter group");
					beanUpdate.setGroup(sc.next());
					ContactFileDao daoUpdate=ContactManager.getContactFileImpl();
					int resultUpdate=daoUpdate.updateContact(beanUpdate);
					if(resultUpdate>0) {
						System.out.println(resultUpdate+" row updated sucess fully");
					}


					break;

				default:
					System.out.println("Invalid Option");
					break;
				}




				break;


			default:
				System.out.println("selected Invalid option ");
				mainOptionConditon=false;
				break;
			}









		}
	}

}
