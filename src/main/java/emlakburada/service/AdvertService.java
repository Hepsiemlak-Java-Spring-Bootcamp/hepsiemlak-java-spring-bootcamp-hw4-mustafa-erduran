package emlakburada.service;

import java.util.*;

import emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.client.BannerClient;
import emlakburada.dto.request.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;

@Service
public class AdvertService {

	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
	private UserService userService;
	
	private static int advertNo = 38164784;
	
	@Autowired
	private BannerClient bannerClient;
	
	@Autowired
	private QueueService queueService;

	@Autowired
	private UserRepository userRepository;

	// @Autowired
//	public IlanService(IlanRepository ilanRepository) {
//		super();
//		this.ilanRepository = ilanRepository;
//	}

	public List<AdvertResponse> getAllAdverts() {
		// System.out.println("IlanService -> ilanRepository: " +
		// advertRepository.toString());
		// kullaniciService.getAllKullanici();
		List<AdvertResponse> advertList = new ArrayList<>();
		for (Advert advert : advertRepository.findAll()) {
			advertList.add(convertToAdvertResponse(advert));
		}
		return advertList;
	}


	/*public UUID setFavoriteAdvert(UUID userId){
		return advertRepository.setFavoriteAdvert(userId);
	}
	public List<AdvertResponse> getFavoriteAdvert(UUID userId){
		List<AdvertResponse> advertList = new ArrayList<>();
		for(Advert advert : advertRepository.getFavoriteAdvertsByUserId(userId)){
			advertList.add(convertToAdvertResponse(Optional.ofNullable(advert)));
		}
		return advertList;
	}*/



	public AdvertResponse saveAdvert(AdvertRequest request) {
		Optional<User> foundUser = userRepository.findById(request.getUserId());
		Advert savedAdvert = advertRepository.save(convertToAdvert(request,foundUser));
		//EmailMessage emailMessage = new EmailMessage("cemdrman@gmail.com");
		//queueService.sendMessage(emailMessage);
		bannerClient.saveBanner();
		return convertToAdvertResponse(savedAdvert);
	}

	private AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
		AdvertResponse response = new AdvertResponse();
		response.setBaslik(savedAdvert.getBaslik());
		response.setFiyat(savedAdvert.getFiyat());
		response.setAdvertNo(savedAdvert.getAdvertNo());
		response.setUser(savedAdvert.getCreatorUser());
		return response;
	}

	private Advert convertToAdvert(AdvertRequest request,Optional<User> foundUser) {

		Advert advert = null;

		if (foundUser.isPresent()) {
			advert = new Advert();
			advert.setCreatorUser(foundUser.get());
			advertNo++;

			advert.setAdvertNo(advertNo);
			advert.setBaslik(request.getBaslik());
			advert.setFiyat(request.getFiyat());
		} else {
			//log.info("Kullanıcı Bulunamadı!");
		}

		return advert;
	}

	public AdvertResponse getAdvertByAdvertId(int advertId) {
		Optional<Advert> advert = advertRepository.findById(advertId);
		return convertToAdvertResponse(advert.get());
	}

}
