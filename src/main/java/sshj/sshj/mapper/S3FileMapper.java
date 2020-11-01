package sshj.sshj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;
import sshj.sshj.dto.FileUploadDto;

/**
 * 파일 업로드 mapper
 * @author krims
 *
 */
@Mapper
@Repository
public interface S3FileMapper {
	
	int uploadProfile(@Param("userId") long userId, @Param("profileUrl") String profileUrl);
	
	int uploadContent(FileUploadDto fileUploadDto);
	
	List<String> getMeetingFiles(@Param("meetingId") long meetingId);

	String selectProfileImage(@Param("userId") long userId);
	
}
