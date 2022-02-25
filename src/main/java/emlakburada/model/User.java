package emlakburada.model;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	//private String kullaniciTipi; // bireysel & kurumsal & yeniTip
	private String isim;
	private String email;
	private String fotograf;
	private String biyografi;
	private String password;
	//private Set<Advert> favoriIlanlar = new HashSet<>();
	//private List<Advert> yayinladigiIlanlar = new ArrayList<>();
	//private List<Message> mesajKutusu;

	public User(String kullaniciTipi, String isim, String email) {
		super();

		this.isim = isim;
		this.email = email;
	}

	public UUID generateId(){
		return UUID.randomUUID();
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
