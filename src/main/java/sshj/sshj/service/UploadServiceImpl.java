package sshj.sshj.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import sshj.sshj.component.S3Utils;
import sshj.sshj.dto.FileUploadDto;
import sshj.sshj.dto.enums.FileDirEnum;
import sshj.sshj.mapper.UploadMapper;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService{
	
	@Autowired
	private UploadMapper uploadMapper;
	
	@Autowired
	private S3Utils s3Utils;
	
	/**
	 * 프로필 파일 업로드
	 */
	@Override
	public String executeUploadProfile(MultipartFile multipartFile, long userId) {

		String newFilename = Long.toString(System.nanoTime());
		String imgUrl = "";
		try {
			
			String mimeType = new Tika().detect(multipartFile.getInputStream());
			
			/*
			 * image 파일인지 체크
			 */
			if(!"image".equals(mimeType.split("/")[0])) {
				log.error("File is not Image [{}]", multipartFile);
				return imgUrl;
			}
			
			imgUrl = s3Utils.upload(multipartFile, FileDirEnum.profile, newFilename);

			/*
			 * 유저 테이블에서 프로필 이미지 링크 update		
			 * TODO: do168 User 관련 로직 작성 후 추가 예정			
			 */

		} catch (IOException e) {
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		}
		return imgUrl;
	}
	
	/**
	 * 동아리 컨텐츠 업로드
	 */
	@Override
	public List<String> executeUploadClubContents(MultipartHttpServletRequest multipartHttpServletRequest, long userId,
			long meetingId) {

		Iterator<String> fileList = multipartHttpServletRequest.getFileNames();
		List<String> imgUrls = new ArrayList<String>();
		
		while(fileList.hasNext()) {
			String newFilename = Long.toString(System.nanoTime());
			String imgUrl = "";
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(fileList.next());
			for(MultipartFile file : list) {
				try {
					String mimeType = new Tika().detect(file.getInputStream());

					FileUploadDto fileUploadDto = new FileUploadDto();
					fileUploadDto.setUserId(userId);
					fileUploadDto.setOriginFileName(file.getOriginalFilename());
					fileUploadDto.setFileBaseUrl("/" + FileDirEnum.club +"/" + newFilename);
					fileUploadDto.setMimeType(mimeType);
					fileUploadDto.setMeetingId(meetingId);
					fileUploadDto.setSize(file.getSize());
					
					// 업로드할 파일 정보 DB에 저장
					int cnt = uploadMapper.uploadProfile(fileUploadDto);

					if (cnt != 1) {
						log.error("Save upload info failed!!");
						throw new RuntimeException();
					}
					
					// 파일 S3로 업로드
					imgUrl = s3Utils.upload(file, FileDirEnum.club, newFilename);

					imgUrls.add(imgUrl);

				} catch (IOException e) {
					log.error("", e);
				} catch (Exception e) {
					log.error("", e);
				}
			}
		}
		return imgUrls;
	}
}
