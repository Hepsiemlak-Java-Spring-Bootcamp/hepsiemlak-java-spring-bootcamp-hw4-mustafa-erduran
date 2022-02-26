package emlakburada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Adverts1")
public class Advert {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private int advertNo;
	//private RealEstate gayrimenkul;
	private String baslik;
	@OneToOne
	private User creatorUser; // hem bireysel & kurumsal
	private String[] resimList = new String[5];
	private BigDecimal fiyat;
	private int suresi;
	private boolean oneCikarilsinMi = false;
	private boolean incelendiMi = false;
	private Date olusturulmaTarihi;
	private boolean aktifMi;

	public Advert(RealEstate gayrimenkul, User kullanici, String[] resimList) {

		this.resimList = resimList;
	}


}
