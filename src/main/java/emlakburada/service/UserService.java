package emlakburada.service;

import emlakburada.dto.request.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.User;
import emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import emlakburada.repository.AdvertRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	private static int counter = 100;


	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
	private UserRepository userRepository;

	public List<UserResponse> getAllUsers() {
		List<UserResponse> userList = new ArrayList<>();

		for (User user : userRepository.findAll()) {
			userList.add(convertToUserResponse(user));
		}
		return userList;
	}

	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

	public void deleteUserById(Integer userId){
		userRepository.deleteById(userId);
	}

	public UserResponse findUserById(Integer userId){
		return convertToUserResponse(userRepository.findById(userId).get());
	}

	public UserResponse createUser(UserRequest request){
		User savedUser = userRepository.save(convertToUser(request));
		return convertToUserResponse(savedUser);
	}

	private UserResponse convertToUserResponse(User user){
		UserResponse response = new UserResponse();

		response.setUserId(user.getId());
		response.setBiyografi(user.getBiyografi());
		response.setIsim(user.getIsim());
		response.setEmail(user.getEmail());
		response.setFotograf(user.getFotograf());

		return response;
	}
	private User convertToUser(UserRequest request){
		User user = new User();
		user.setId(counter);
		user.setIsim(request.getIsim());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		//user.setKullaniciTipi(request.getKullaniciTipi());
		user.setBiyografi(request.getBiyografi());
		user.setFotograf(request.getFotograf());
		counter++;
		return user;

	}
}
