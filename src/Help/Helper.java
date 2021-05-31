package Help;
import javax.swing.JOptionPane;

public class Helper {
	public static void showMessage(String message) {
		String phrase;
		switch (message) {
		case "fillUp":
			phrase = "Lütfen boþ alanlarý doldurunuz.";
			break;
		case "attention":
			phrase = "Bir hata oluþtu.";
			break;
		case "premium":
			phrase = "Sadece premium kullanýcýlarý takip edebilirsiniz.";
			break;
		case "needFollow":
			phrase = "Kullanýcýnýn çalma listesini görüntüleyebilmeniz için takip etmeniz gerekmektedir.";
			break;
		case "followPremium":
			phrase = "Yalnýzca premium kullanýcýlarýn çalma listelerini görüntüleyebilirsiniz.";
			break;
		case "alreadyFollowed":
			phrase = "Bu kullanýcýyý zaten takip ediyorsunuz.";
			break;
		case "selectedAddPlaylist":
			phrase = "Seçilen þarký çalma listenize eklendi.";
			break;
		case "allAddPlaylist":
			phrase = "Listedeki tüm þarkýlar çalma listenize eklendi.";
			break;
		case "alreadyAddToPlaylist":
			phrase = "Seçilen þarký çalma listenizde bulunduðu için eklenmedi.";
			break;
		case "addAllToPlaylist":
			phrase = "Listenizde bulunmayan tüm þarkýlar eklendi.";
			break;
		case "wrongPass":
			phrase = "Kullanýcý adý ya da þifre yanlýþ.";
			break;
		case "followSuccess":
			phrase = "Kullanýcý takip edildi.";
			break;
		case "alreadyAdded":
			phrase = "Eklemek istediðiniz þarký girilen albümde bulunmaktadýr.";
			break;
		case "differentType":
			phrase = "Eklemek istediðiniz þarký ile girilen albümün türü uyuþmamaktadýr.";
			break;
		case "musicAdded":
			phrase = "Þarký listeye eklendi.";
			break;
		case "followYourself":
			phrase = "Kendinizi takip edemezsiniz.";
			break;
		case "unfollowed":
			phrase = "Kullanýcýyý takip etmeyi býraktýnýz.";
			break;
		case "albumNotExcist":
			phrase = "Böyle bir album bulunmamaktadýr.";
			break;
		case "addToAlbum":
			phrase = "Þarký albüme eklendi.";
			break;
		case "albumUpdate":
			phrase = "Þarký ve albüm bilgisi güncellendi.";
			break;
		case "alreadyFollowedArtist":
			phrase = "Bu sanatçýyý zaten takip ediyorsunuz.";
			break;
		case "unfollowArtist":
			phrase = "Sanatçýyý takip etmeyi býraktýnýz.";
			break;
		case "followArtist":
			phrase = "Sanatçý takip edildi.";
			break;
		case "artistExcist":
			phrase = "Sanatçý zaten listede bulunmaktadýr.";
			break;
		case "artistAdded":
			phrase = "Sanatçý listeye eklendi.";
			break;
		case "musicInOneAlbum":
			phrase = "Bir þarký sadece bir albümde bulunabilir.";
			break;
		case "deleteAllAlbum":
			phrase = "Seçilen albümün tümü silindi.";
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
			phrase = "Þarký bütün listelerden silinecek. Seçiminizden emin misiniz?";
			break;
		case "deleteArtist": 
			phrase = "Sanatçý bütün listelerden silinecek. Seçiminizden emin misiniz?";
			break;
		case "deleteAlbum": 
			phrase = "Album bütün listelerden silinecek. Seçiminizden emin misiniz?";
			break;
		default:
			phrase = message;
			break;
		}
		int choice = JOptionPane.showConfirmDialog(null, phrase,"Uyarý", JOptionPane.YES_NO_OPTION);
		if(choice == 0) 
			return true;
		else 
			return false;
	}
}
