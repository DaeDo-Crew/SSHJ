package sshj.sshj.mapper;

import org.apache.ibatis.annotations.Mapper;
import sshj.sshj.dto.MeetingDto;

import java.util.List;

@Mapper
public interface MeetingMapper {
    public void insertMeeting(MeetingDto dto) throws Exception;
    public MeetingDto selectMeeting(int meetingId) throws Exception;
    public void updateMeeting(MeetingDto dto) throws Exception;
    public void deleteMeeting(int meetingId) throws Exception;
    public List<MeetingDto> selectMeetingList() throws Exception;
    public List<MeetingDto> selectClubByMeetingList(int clubId) throws Exception;
    public List<MeetingDto> selectUserByMeetingList(int userId) throws Exception;
    void insertMeetingLike(int userId,int meetingId) throws Exception;
    void deleteMeetingLike(int userId,int meetingId) throws Exception;
}
