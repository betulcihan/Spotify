package Help;
import javax.swing.JOptionPane;

public class Helper {
	public static void showMessage(String message) {
		String phrase;
		switch (message) {
		case "fillUp":
			phrase = "L�tfen bo� alanlar� doldurunuz.";
			break;
		case "attention":
			phrase = "Bir hata olu�tu.";
			break;
		case "premium":
			phrase = "Sadece premium kullan�c�lar� takip edebilirsiniz.";
			break;
		case "needFollow":
			phrase = "Kullan�c�n�n �alma listesini g�r�nt�leyebilmeniz i�in takip etmeniz gerekmektedir.";
			break;
		case "followPremium":
			phrase = "Yaln�zca premium kullan�c�lar�n �alma listelerini g�r�nt�leyebilirsiniz.";
			break;
		case "alreadyFollowed":
			phrase = "Bu kullan�c�y� zaten takip ediyorsunuz.";
			break;
		case "selectedAddPlaylist":
			phrase = "Se�ilen �ark� �alma listenize eklendi.";
			break;
		case "allAddPlaylist":
			phrase = "Listedeki t�m �ark�lar �alma listenize eklendi.";
			break;
		case "alreadyAddToPlaylist":
			phrase = "Se�ilen �ark� �alma listenizde bulundu�u i�in eklenmedi.";
			break;
		case "addAllToPlaylist":
			phrase = "Listenizde bulunmayan t�m �ark�lar eklendi.";
			break;
		case "wrongPass":
			phrase = "Kullan�c� ad� ya da �ifre yanl��.";
			break;
		case "followSuccess":
			phrase = "Kullan�c� takip edildi.";
			break;
		case "alreadyAdded":
			phrase = "Eklemek istedi�iniz �ark� girilen alb�mde bulunmaktad�r.";
			break;
		case "differentType":
			phrase = "Eklemek istedi�iniz �ark� ile girilen alb�m�n t�r� uyu�mamaktad�r.";
			break;
		case "musicAdded":
			phrase = "�ark� listeye eklendi.";
			break;
		case "followYourself":
			phrase = "Kendinizi takip edemezsiniz.";
			break;
		case "unfollowed":
			phrase = "Kullan�c�y� takip etmeyi b�rakt�n�z.";
			break;
		case "albumNotExcist":
			phrase = "B�yle bir album bulunmamaktad�r.";
			break;
		case "addToAlbum":
			phrase = "�ark� alb�me eklendi.";
			break;
		case "albumUpdate":
			phrase = "�ark� ve alb�m bilgisi g�ncellendi.";
			break;
		case "alreadyFollowedArtist":
			phrase = "Bu sanat��y� zaten takip ediyorsunuz.";
			break;
		case "unfollowArtist":
			phrase = "Sanat��y� takip etmeyi b�rakt�n�z.";
			break;
		case "followArtist":
			phrase = "Sanat�� takip edildi.";
			break;
		case "artistExcist":
			phrase = "Sanat�� zaten listede bulunmaktad�r.";
			break;
		case "artistAdded":
			phrase = "Sanat�� listeye eklendi.";
			break;
		case "musicInOneAlbum":
			phrase = "Bir �ark� sadece bir alb�mde bulunabilir.";
			break;
		case "deleteAllAlbum":
			phrase = "Se�ilen alb�m�n t�m� silindi.";
			break;
		default:
			phrase = message;
		}
		JOptionPane.showMessageDialog(null, phrase);
	}
	
	public static boolean warning(String message) {
		String phrase;
		switch (message) {
		case "deleteMusic": 
			phrase = "�ark� b�t�n listelerden silinecek. Se�iminizden emin misiniz?";
			break;
		case "deleteArtist": 
			phrase = "Sanat�� b�t�n listelerden silinecek. Se�iminizden emin misiniz?";
			break;
		case "deleteAlbum": 
			phrase = "Album b�t�n listelerden silinecek. Se�iminizden emin misiniz?";
			break;
		default:
			phrase = message;
			break;
		}
		int choice = JOptionPane.showConfirmDialog(null, phrase,"Uyar�", JOptionPane.YES_NO_OPTION);
		if(choice == 0) 
			return true;
		else 
			return false;
	}
}
