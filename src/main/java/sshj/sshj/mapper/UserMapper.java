package sshj.sshj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sshj.sshj.dto.CodeInfoModel;
import sshj.sshj.dto.UserDto;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

	/*
	 * 현재는 사용하지않으나 UserDetail 구현체를 위해 구현 
	 * email로 유저 정보 검색
	 */
    UserDto selectUserInfo(String email);
    
    /*
     * UID로 유저 정보 검색
     */
    UserDto selectUserInfoById(long userId);
    
//    int selectUserEmail(long userId);
    int selectUserId(long userId);
    UserDto selectUserEmail(String email);
    void insertUser(UserDto userDto);
    String selectCode(String email);
    CodeInfoModel selectCodeInfo(String code);
    void insertCodeEmail(String code, String email, String time);
    void updateCodeEmail(String code, String email, String time);
    int selectUserNicknameIsOk(String nickname);
    void updateUserNickname(long userId, String nickname);
    void updateUserPassword(String email, String password);
    void insertPushToken(long userId, String expoPushToken);
//    int updateDeviceToken(long userId, String expoPushToken);
    int selectUserPushToken(long userId, String expoPushToken);
    List<String> selectUserPushTokenList(long userId);
    String selectEmail(long userId);
    String selectUserNickname(long userId);

    void deleteUserId(long userId);

}
