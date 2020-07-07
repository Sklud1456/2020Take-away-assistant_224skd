package wm_assistant.begin;

import wm_assistant.contorl.GMManager;
import wm_assistant.contorl.addressManager;
import wm_assistant.contorl.manjianManager;
import wm_assistant.contorl.merchatManager;
import wm_assistant.contorl.productManager;
import wm_assistant.contorl.productsortManager;
import wm_assistant.contorl.riderManager;
import wm_assistant.contorl.userManager;

public class wm_assistantUtil {
	public static addressManager addressmanager=new addressManager();//需要换成自行设计的实现类
	public static GMManager GMmanager=new GMManager();//需要换成自行设计的实现类
	public static manjianManager manjianmanager=new manjianManager();//需要换成自行设计的实现类
	public static merchatManager merchatmanager=new merchatManager();
	public static productsortManager productsortmanager=new productsortManager();
	public static productManager productmanager=new productManager();
	public static riderManager ridermanager=new riderManager();
	public static userManager usermanager=new userManager();
	public static boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
		System.out.println(str.charAt(i));
		if (!Character.isDigit(str.charAt(i))) {
		return false;
		}
		}
		return true;
		}

}
