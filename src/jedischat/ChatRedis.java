package jedischat;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class ChatRedis {

	static Jedis jedis;
	static int indice;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try{
			//initialisation de la connexion
			jedis = new Jedis("localhost");
			System.out.println("#Connection Successful");
			
			
			//création du user
			System.out.println("#\n#Entrez votre nom d'utilisateur : ");
			String user = "user";
			
			//récupération des anciens messages
			indice = 0;
			actualize_channel();
			
			//lancement du chat
			String mkey;
			String message;
			while (true) { //remplacer actualize_channel() par un trigger sur le set de redis
				message = sc.nextLine();
				actualize_channel();
				jedis.set("M"+String.valueOf(indice), user+" : "+message);
				actualize_channel();
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	public static void actualize_channel() {
		while(jedis.get("M"+indice)!=null) {
			System.out.println(jedis.get("M"+indice));
			indice++;
		}
	}

}
