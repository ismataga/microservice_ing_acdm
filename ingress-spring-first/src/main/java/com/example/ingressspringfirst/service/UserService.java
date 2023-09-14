package com.example.ingressspringfirst.service;

import static com.example.ingressspringfirst.mapper.UserMapper.mapEntityToResponse;
import static com.example.ingressspringfirst.mapper.UserMapper.mapRequestToEntity;
import static com.example.ingressspringfirst.mapper.UserMapper.mapToPageableResponse;
import static com.example.ingressspringfirst.model.constants.CriteriaConstants.COUNT_DEFAULT_VALUE;
import static com.example.ingressspringfirst.model.constants.CriteriaConstants.PAGE_DEFAULT_VALUE;
import static com.example.ingressspringfirst.model.enums.ExceptionMessage.USER_NOT_FOUND;

import com.example.ingressspringfirst.entity.UserEntity;
import com.example.ingressspringfirst.exception.NotFoundException;
import com.example.ingressspringfirst.model.criteria.PageCriteria;
import com.example.ingressspringfirst.model.criteria.UserCriteria;
import com.example.ingressspringfirst.model.request.UserRequest;
import com.example.ingressspringfirst.model.response.PageableUserResponse;
import com.example.ingressspringfirst.model.response.UserResponse;
import com.example.ingressspringfirst.repository.UserRepository;
import com.example.ingressspringfirst.service.specifiaction.UserSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //    private final UserMapper userMapper;
    public UserResponse getUser(Long id) {
        log.info("ActionLog.getUser.start id:{}", id);
        var user = fetchUserIfExist(id);
        log.info("ActionLog.getUser.info  id:{}", id);
        return mapEntityToResponse(user);

    }

    public List<UserResponse> getUsers() {
        return List.of(new UserResponse(1L, "mock_user", 23));
    }

    @SneakyThrows
    public void saveUser(UserRequest user) {
        Thread.sleep(1000);
        userRepository.save(mapRequestToEntity(user));
    }

    public void setBirthPlace(Long id, String birthPlace) {
        var user = fetchUserIfExist(id);
        user.setBirthPlace(birthPlace);
        userRepository.save(user);

    }

    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {
        var pageNumber = pageCriteria.getPage() == null ? PAGE_DEFAULT_VALUE : pageCriteria.getPage();
        var count = pageCriteria.getCount() == null ? COUNT_DEFAULT_VALUE : pageCriteria.getCount();

        var userPage = userRepository.findAll(new UserSpecification(userCriteria), PageRequest.of(pageNumber, count));
        return mapToPageableResponse(userPage);
    }


    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.getUser.error user not found by id:{}", id);
                    throw new NotFoundException(USER_NOT_FOUND.getMessage());
                });
    }

    public void test(){
        System.out.println("Scheduled is run");
    }

}
